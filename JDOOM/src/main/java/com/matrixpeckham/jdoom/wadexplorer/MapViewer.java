/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer;

import static com.matrixpeckham.jdoom.util.Fixed.FRAC_BITS;

import com.matrixpeckham.jdoom.WAD;
import com.matrixpeckham.jdoom.doomdata.*;
import com.matrixpeckham.jdoom.p.BlockMap;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author matri
 */
public class MapViewer extends JFrame {

    WAD wad;

    private static MapViewer inst = null;

    public static MapViewer get() {
        if (inst == null) {
            throw new IllegalStateException(
                    "Cannot get instance before one is created");
        }
        return inst;
    }

    public static MapViewer get(WAD wad) {
        if (inst == null) {
            inst = new MapViewer();
            inst.wad = wad;
        }

        return inst;
    }

    private ViewPanel viewPanel = new ViewPanel();

    private MapViewer() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewPanel.setPreferredSize(new Dimension(1000, 1000));
        add(viewPanel);
        this.pack();
    }

    public void showLineDefs(LineDefList lines, WAD.DirectoryEntry entry) {
        VertexList verts = (VertexList) wad.getLump(entry.index + 2);
        viewPanel.draw.clear();
        viewPanel.draw.add(viewPanel.nePoint);
        viewPanel.draw.add(viewPanel.nwPoint);
        viewPanel.draw.add(viewPanel.sePoint);
        viewPanel.draw.add(viewPanel.swPoint);
        for (MapLineDef ld : lines) {
            MapVertex v1 = verts.get(ld.v1);
            MapVertex v2 = verts.get(ld.v2);
            viewPanel.draw.add(new Drawable.Line(v1.x, v1.y, v2.x, v2.y,
                    viewPanel));
        }
        repaint();
    }

    public void showBlock(BlockMap map, int index, WAD.DirectoryEntry entry) {
        LineDefList lines = (LineDefList) wad.getLump(entry.index - 8);
        VertexList verts = (VertexList) wad.getLump(entry.index - 6);
        int boxX = (map.orgX >> FRAC_BITS) + ((index % map.cols) * 128);
        int boxY = (map.orgY >> FRAC_BITS) + ((index / map.cols) * 128);
        Drawable line = new Drawable.Line(boxX, boxY, boxX, boxY + 128,
                viewPanel);
        line.c = Color.BLUE;
        viewPanel.draw.add(line);
        line = new Drawable.Line(boxX + 128, boxY, boxX + 128, boxY + 128,
                viewPanel);
        line.c = Color.BLUE;
        viewPanel.draw.add(line);
        line = new Drawable.Line(boxX, boxY + 128, boxX + 128, boxY + 128,
                viewPanel);
        line.c = Color.BLUE;
        viewPanel.draw.add(line);
        line = new Drawable.Line(boxX, boxY, boxX + 128, boxY, viewPanel);
        line.c = Color.BLUE;
        viewPanel.draw.add(line);
        for (int i = 0; i < map.blocklist[index].length; i++) {
            MapLineDef ld = lines.get(map.blocklist[index][i]);
            MapVertex v1 = verts.get(ld.v1);
            MapVertex v2 = verts.get(ld.v2);
            line = new Drawable.Line(v1.x, v1.y, v2.x, v2.y, viewPanel);
            line.c = Color.RED;
            viewPanel.draw.add(line);
        }
        repaint();
    }

    public void showVertices(VertexList vl) {
        viewPanel.draw.clear();
        viewPanel.draw.add(viewPanel.nePoint);
        viewPanel.draw.add(viewPanel.nwPoint);
        viewPanel.draw.add(viewPanel.sePoint);
        viewPanel.draw.add(viewPanel.swPoint);
        for (MapVertex v : vl) {
            viewPanel.draw.add(new Drawable.Point(v.x, v.y, viewPanel));
        }
        repaint();
    }

    public void showVertex(MapVertex v1) {
        viewPanel.draw.add(new Drawable.Point(v1.x, v1.y, viewPanel));
        repaint();
    }

    private static class ViewPanel extends JPanel implements MouseInputListener,
            MouseWheelListener,
            ComponentListener {

        int minDimension = Short.MIN_VALUE;

        int maxDimension = Short.MAX_VALUE;

        int centerX = 0;

        int centerY = 0;

        double pixToView = 10.0;

        int mousePressX = 0;

        int mousePressY = 0;

        ArrayList<Drawable> draw = new ArrayList();

        Drawable.Point nwPoint;

        Drawable.Point nePoint;

        Drawable.Point swPoint;

        Drawable.Point sePoint;

        public ViewPanel() {
            nwPoint = new Drawable.Point(minDimension, minDimension, this);
            nePoint = new Drawable.Point(maxDimension, minDimension, this);
            swPoint = new Drawable.Point(minDimension, maxDimension, this);
            sePoint = new Drawable.Point(maxDimension, maxDimension, this);
            draw.add(nePoint);
            draw.add(nwPoint);
            draw.add(sePoint);
            draw.add(swPoint);
            draw.add(new Drawable.Point(0, 0, this));
            draw.add(new Drawable.Point(100, 0, this));
            draw.add(new Drawable.Point(0, 100, this));
            addMouseListener(this);
            addMouseMotionListener(this);
            addMouseWheelListener(this);
        }

        @Override
        public void componentHidden(ComponentEvent e) {
        }

        @Override
        public void componentMoved(ComponentEvent e) {
        }

        @Override
        public void componentResized(ComponentEvent e) {
            repaint();
        }

        @Override
        public void componentShown(ComponentEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int dx = e.getX() - mousePressX;
            int dy = e.getY() - mousePressY;
            mousePressX = e.getX();
            mousePressY = e.getY();
            centerX -= (int) (pixToView * dx);
            centerY -= (int) (pixToView * dy);
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            mousePressX = e.getX();
            mousePressY = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            int scroll = (int) e.getPreciseWheelRotation();
            int maxWidth = maxDimension - minDimension;
            double maxPixToView = this.getWidth() / (double) maxWidth;
            if (scroll < 0) {
                pixToView *= 2;
                if (pixToView > 1000) {
                    pixToView = 1000;
                }
            } else {
                pixToView /= 2;
                if (pixToView < maxPixToView) {
                    pixToView = maxPixToView;
                }
            }
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
            for (Drawable d : draw) {
                d.draw(g2);
            }
        }

    }

    public static abstract class Drawable {

        ViewPanel view;

        Color c = Color.BLACK;

        short x;

        short y;

        public Drawable(int x, int y, ViewPanel p) {
            this.x = (short) x;
            this.y = (short) y;
            this.view = p;
        }

        public abstract void draw(Graphics2D g);

        public static class Line extends Drawable {

            int x2, y2;

            public Line(int x, int y, int x2, int y2, ViewPanel p) {
                super(x, y, p);
                this.x2 = x2;
                this.y2 = y2;
            }

            @Override
            public void draw(Graphics2D g) {
                int screenX = (int) ((x - view.centerX) / view.pixToView)
                        + view.getWidth() / 2;
                int screenY = (int) ((-y - view.centerY) / view.pixToView)
                        + view.getHeight() / 2;
                int screenX2 = (int) ((x2 - view.centerX) / view.pixToView)
                        + view.getWidth() / 2;
                int screenY2 = (int) ((-y2 - view.centerY) / view.pixToView)
                        + view.getHeight() / 2;
                g.setColor(c);
                g.drawLine(screenX, screenY, screenX2, screenY2);
            }

        }

        public static class Point extends Drawable {

            public int pixelSize = 2;

            public Point(int x, int y, ViewPanel p) {
                super(x, y, p);
            }

            @Override
            public void draw(Graphics2D g) {
                int screenX = (int) ((x - view.centerX) / view.pixToView)
                        + view.getWidth() / 2;
                int screenY = (int) ((-y - view.centerY) / view.pixToView)
                        + view.getHeight() / 2;
                g.setColor(c);
                g.fillOval(screenX - pixelSize, screenY - pixelSize, pixelSize
                        * 2,
                        pixelSize
                        * 2);
            }

        }

    }

}
