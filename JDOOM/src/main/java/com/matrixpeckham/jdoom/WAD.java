/*
 */
package com.matrixpeckham.jdoom;

import static java.util.regex.Pattern.compile;

import com.matrixpeckham.jdoom.doomdata.*;
import com.matrixpeckham.jdoom.p.BlockMap;
import com.matrixpeckham.jdoom.p.Reject;
import com.matrixpeckham.jdoom.r.*;
import com.matrixpeckham.jdoom.s.*;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author matri
 */
public class WAD {

    HashMap<DirectoryEntry, Object> lumpCache = new HashMap<>();

    RandomAccessFile file;

    boolean official = false;

    boolean good = false;

    public int lumps;

    long directoryOffset;

    ArrayList<DirectoryEntry> directory = new ArrayList<>();

    public WAD(File f) {
        try {
            file = new RandomAccessFile(f, "r");
            byte[] magic = new byte[4];
            file.read(magic);
            String iwad = new String(magic);
            if ("IWAD".equals(iwad)) {
                official = true;
                good = true;
            } else if ("PWAD".equals(iwad)) {
                official = false;
                good = true;
            } else {
                throw new IllegalArgumentException(
                        "Wad File has bad magic number");
            }
            lumps = readInt();
            directoryOffset = readInt();
            readDirectory();
        } catch (IOException e) {
            throw new IllegalArgumentException("Bad wad file");
        }
    }

    public ArrayList<DirectoryEntry> getDirectory() {
        return directory;
    }

    public Object getLump(int index) {
        return getLump(directory.get(index));
    }

    public Object getLump(DirectoryEntry entry) throws
            IllegalArgumentException {
        if (lumpCache.containsKey(entry)) {
            return lumpCache.get(entry);
        }
        byte[] bytes = new byte[entry.length];
        try {
            file.seek(entry.offset);
            file.read(bytes);
        } catch (IOException e) {
            throw new IllegalArgumentException("File problem");
        }
        Object ret = null;
        switch (entry.name) {
            case "PLAYPAL\0":
                ret = PlayPal.create(bytes);
                break;
            case "COLORMAP":
                ret = ColorMap.create(bytes);
                break;
            case "ENDOOM\0\0":
                ret = EndDoom.create(bytes);
                break;
            case "VERTEXES":
                ret = VertexList.create(bytes);
                break;
            case "LINEDEFS":
                ret = LineDefList.create(bytes);
                break;
            case "SIDEDEFS":
                ret = SideDefList.create(bytes);
                break;
            case "SECTORS\0":
                ret = SectorList.create(bytes);
                break;
            case "SSECTORS":
                ret = SubSectorList.create(bytes);
                break;
            case "SEGS\0\0\0\0":
                ret = SegList.create(bytes);
                break;
            case "THINGS\0\0":
                ret = ThingList.create(bytes);
                break;
            case "NODES\0\0\0":
                ret = NodeList.create(bytes);
                break;
            case "BLOCKMAP":
                ret = BlockMap.create(bytes);
                break;
            case "REJECT\0\0":
                ret = Reject.create(bytes, entry, this);
                break;
            case "PNAMES\0\0":
                ret = PNames.create(bytes);
                break;
            case String s when s.startsWith("DEMO"):
                //TODO: load demos
                break;
            case "DMXGUS\0\0":
            case "GENMIDI\0":
                //These aren't needed
                break;
            case String s when s.startsWith("DP"):
                ret = PCSoundEffect.create(bytes);
                break;
            case String s when s.startsWith("DS"):

                ret = SoundEffect.create(bytes);

                break;
            case String s when s.startsWith("D_"):
                ret = Song.create(bytes);
                break;
            case "S_START\0":
            case "S_END\0\0\0":
            case "P_START\0":
            case "P_END\0\0\0":
            case "P1_START":
            case "P1_END\0\0":
            case "P2_START":
            case "P2_END\0\0":
            case "F_START\0":
            case "F_END\0\0\0":
            case "F1_START":
            case "F1_END\0\0":
            case "F2_START":
            case "F2_END\0\0":
                break;
            case "TEXTURE1":
            case "TEXTURE2":
                ret = TextureList.create(bytes, this);
                break;
            case String s when s.startsWith("MAP"):
                //TODO: whole map
                break;
            default:
                String patternString = "E(\\d)M(\\d)\\x00*";
                Pattern pattern = compile(patternString);
                Matcher matcher = pattern.matcher(entry.name);
                if (matcher.matches()) {
                    //TODO: whole map
                    break;
                }

                DirectoryEntry s_start = getEntry("S_START\0");
                DirectoryEntry s_end = getEntry("S_END\0\0\0");
                DirectoryEntry p_start = getEntry("P_START\0");
                DirectoryEntry p_end = getEntry("P_END\0\0\0");
                DirectoryEntry p1_start = getEntry("P1_START");
                DirectoryEntry p1_end = getEntry("P1_END\0\0");
                DirectoryEntry p2_start = getEntry("P2_START");
                DirectoryEntry p2_end = getEntry("P2_END\0\0");
                DirectoryEntry f_start = getEntry("F_START\0");
                DirectoryEntry f_end = getEntry("F_END\0\0\0");
                DirectoryEntry f1_start = getEntry("F1_START");
                DirectoryEntry f1_end = getEntry("F1_END\0\0");
                DirectoryEntry f2_start = getEntry("F2_START");
                DirectoryEntry f2_end = getEntry("F2_END\0\0");
                if (entry.index > f_start.index && entry.index < f_end.index) {
                    ret = Flat.create(bytes);
                    break;
                }

                ret = Patch.create(bytes);

        }
        if (ret != null) {
            lumpCache.put(entry, ret);
        }
        return ret;
    }

    private void readDirectory() throws IOException {
        file.seek(directoryOffset);
        for (int i = 0; i < lumps; i++) {
            DirectoryEntry ent = readEntry(i);
            directory.add(ent);
        }
    }

    public DirectoryEntry getEntry(String name) {
        for (int i = directory.size() - 1; i >= 0; i--) {
            DirectoryEntry ent = directory.get(i);
            if (name.equals(ent.name)) {
                return ent;
            }
        }
        return null;
    }

    public Object getLump(String name) {
        DirectoryEntry entry = null;
        entry = getEntry(name);
        if (entry == null) {
            throw new IllegalArgumentException("Lump " + name + " not found");
        }
        return getLump(entry);
    }

    private DirectoryEntry readEntry(int index) throws IOException {
        int offset = readInt();
        int length = readInt();
        byte[] nameBytes = new byte[8];
        file.read(nameBytes);
        String name = new String(nameBytes).toUpperCase();
        return new DirectoryEntry(offset, length, name, index);
    }

    public int checkNumForName(String str) {
        return -1;
    }

    int readInt() throws IOException {
        int ret = 0;
        ret |= (file.read() & 0xFF);
        ret |= (file.read() & 0xFF) << 8;
        ret |= (file.read() & 0xFF) << 16;
        ret |= (file.read() & 0xFF) << 24;
        return ret;
    }

    short readShort() throws IOException {
        short ret = 0;
        ret |= (file.read() & 0xFF);
        ret |= (file.read() & 0xFF) << 8;
        return ret;
    }

    public static class DirectoryEntry {

        public final long offset;

        public final int length;

        public final String name;

        public final int index;

        public DirectoryEntry(int offset, int length, String name, int ind) {
            this.offset = offset;
            this.length = length;
            this.name = name;
            this.index = ind;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final DirectoryEntry other = (DirectoryEntry) obj;
            if (this.offset != other.offset) {
                return false;
            }
            if (this.length != other.length) {
                return false;
            }
            if (this.index != other.index) {
                return false;
            }
            return Objects.equals(this.name, other.name);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 23 * hash + (int) (this.offset ^ (this.offset >>> 32));
            hash = 23 * hash + this.length;
            hash = 23 * hash + Objects.hashCode(this.name);
            hash = 23 * hash + this.index;
            return hash;
        }

    }

}
