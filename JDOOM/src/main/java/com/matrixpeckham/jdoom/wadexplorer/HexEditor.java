/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.EventObject;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import javax.swing.text.JTextComponent;

/**
 *
 * @author matri
 */
public class HexEditor extends JFrame implements ActionListener {

    private MemoryTableModel memoryTableModel;

    private JTable memoryTable;

    private JTextField pageNumberTextField;

    private JButton previousPageButton;

    private JButton nextPageButton;

    private static final Dimension MINIMUM_SIZE = new Dimension(320, 600);

    // The width of column 0 (address), in pixels
    private static final int ADDR_COL_WIDTH = 100;

    private static final int HEX_COL_WIDTH = 32;

    // The width of the ASCII cells, in pixels
    private static final int ASCII_COL_WIDTH = 8;

    // The start/end columns of the ASCII view
    private static final int ASCII_COL_START = 17;

    private static final int ASCII_COL_END = 32;

    /**
     * Initialize a new MemoryWindow frame with the specified Bus.
     * The MemoryWindow frame will not be visible.
     *
     * @param bus The Bus the memory window will query for data.
     */
    public HexEditor(File file) {
        this.memoryTableModel = new MemoryTableModel(file);
        createUi();
    }

    /**
     * Initialize a new MemoryWindow frame with the specified Bus.
     * The MemoryWindow frame will not be visible.
     *
     * @param bus The Bus the memory window will query for data.
     */
    public HexEditor() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) {
            System.exit(0);
        }

        this.memoryTableModel = new MemoryTableModel(chooser.getSelectedFile());
        createUi();
    }

    /**
     * Set the current memory page to be inspected by the table.
     *
     * @param pageNumber The page number, from 0 to 255 (00 to FF hex)
     */
    public void setPageNumber(long pageNumber) {
        memoryTableModel.setPageNumber(pageNumber);
    }

    /**
     * Returns the current page number being inspected by the table.
     *
     * @return The page number being inspected, from 0 to 255 (00 to FF hex)
     */
    public long getPageNumber() {
        return memoryTableModel.getPageNumber();
    }

    public long getMaxPages() {
        return memoryTableModel.getMaxPages();
    }

    /**
     * Set the contents of the page number text field with the current
     * page number, in hex.
     */
    private void updateControls() {
        long pageNumber = getPageNumber();

        previousPageButton.setEnabled(pageNumber > 0x00);
        nextPageButton.setEnabled(pageNumber < getMaxPages());
        pageNumberTextField.setText(Utils.wordToHex(pageNumber));
    }

    /**
     * Set-up the UI.
     */
    private void createUi() {
        setTitle("Hex Contents");
        this.memoryTable = new MemoryTable(memoryTableModel);

        memoryTable.setDragEnabled(false);
        memoryTable.setCellSelectionEnabled(false);
        memoryTable.setIntercellSpacing(new Dimension(0, 0));
        memoryTable.getTableHeader().setReorderingAllowed(false);
        memoryTable.getTableHeader().setResizingAllowed(false);
        memoryTable.getTableHeader().setVisible(false);
        memoryTable.setShowGrid(false);

        memoryTable.getColumnModel().getColumn(0).
                setMaxWidth(ADDR_COL_WIDTH);

        for (int i = 1; i < ASCII_COL_START; i++) {
            memoryTable.getColumnModel().getColumn(i).setMaxWidth(
                    HEX_COL_WIDTH);
        }

        for (int i = ASCII_COL_START; i <= ASCII_COL_END; i++) {
            memoryTable.getColumnModel().getColumn(i).setMaxWidth(
                    ASCII_COL_WIDTH);
        }

        MemoryTableCellRenderer memoryTableCellRenderer = new MemoryTableCellRenderer();

        memoryTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        memoryTable.
                setDefaultRenderer(String.class, memoryTableCellRenderer);

        // Turn off tool-tips for the table.
        ToolTipManager.sharedInstance().unregisterComponent(memoryTable);
        ToolTipManager.sharedInstance().unregisterComponent(memoryTable.
                getTableHeader());

        JLabel pageNumberLabel = new JLabel("Page");
        pageNumberTextField = new JTextField(8);
        pageNumberTextField.addActionListener(this);

        nextPageButton = new JButton("Next >>");
        previousPageButton = new JButton("<< Prev");
        nextPageButton.addActionListener(this);
        JLabel maxLabel = new JLabel(" of " + Utils.wordToHex(getMaxPages()));
        previousPageButton.addActionListener(this);

        updateControls();
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        JPanel controlPanel2 = new JPanel();
        JPanel controlPanel3 = new JPanel();
        JPanel memoryPanel = new JPanel();
        memoryPanel.setLayout(new BorderLayout());
        memoryPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        controlPanel2.add(previousPageButton);
        controlPanel2.add(pageNumberLabel);
        controlPanel2.add(pageNumberTextField);
        controlPanel2.add(maxLabel);
        controlPanel2.add(nextPageButton);
        controlPanel.add(controlPanel2, BorderLayout.PAGE_START);

        controlPanel.add(controlPanel3, BorderLayout.PAGE_END);

        JScrollPane scrollPane = new JScrollPane(memoryTable);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        memoryPanel.add(scrollPane, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(memoryPanel, BorderLayout.CENTER);

        setMinimumSize(MINIMUM_SIZE);
        memoryPanel.setPreferredSize(memoryTable.getPreferredSize());
        setPreferredSize(memoryPanel.getPreferredSize());

        pack();
    }

    /**
     * Handle page numbers entered into the UI.
     *
     * @param e The action event
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == previousPageButton) {
            long currentPage = getPageNumber();
            if (currentPage > 0x00) {
                setPageNumber(currentPage - 1);
                updateControls();
                updateState();
            }
        } else if (e.getSource() == nextPageButton) {
            long currentPage = getPageNumber();
            if (currentPage < getMaxPages()) {
                setPageNumber(currentPage + 1);
                updateControls();
                updateState();
            }
        } else if (e.getSource() == pageNumberTextField) {
            String pageNumberInput = pageNumberTextField.getText();
            try {
                // Try to parse a hex value out of the pageNumber.
                int newPageNumber = Integer.parseInt(pageNumberInput, 16);
                setPageNumber(newPageNumber);
                updateState();
            } catch (NumberFormatException ex) {
                // An invalid number was entered. Log the error, but otherwise
                // take no action.
            }

            updateControls();
        }
    }

    /**
     * Refresh the view of memory
     */
    public void updateState() {
        memoryTable.updateUI();
    }

    /**
     * A JTable that will automatically select all text in a cell
     * being edited.
     */
    private class MemoryTable extends JTable {

        public MemoryTable(TableModel tableModel) {
            super(tableModel);
        }

        @Override
        public boolean editCellAt(int row, int col, EventObject e) {
            boolean result = super.editCellAt(row, col, e);

            final Component editor = getEditorComponent();

            if (editor != null && editor instanceof JTextComponent) {
                ((JTextComponent) editor).selectAll();
            }

            return result;
        }

    }

    private class MemoryTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value,
                boolean isSelected, boolean hasFocus,
                int row, int col) {
            final Component cell = super.
                    getTableCellRendererComponent(table,
                            value,
                            isSelected, hasFocus,
                            row, col);

            if (isSelected) {
                cell.setBackground(Color.LIGHT_GRAY);
                cell.setForeground(Color.BLACK);
            }

            if (col > 16) {
                cell.setBackground(Color.LIGHT_GRAY);
            } else {
                cell.setBackground(Color.WHITE);
            }

            return cell;
        }

    }

    /**
     * The model that backs the Memory Table.
     */
    private class MemoryTableModel extends AbstractTableModel {

        private RandomAccessFile file;

        private long fileSize;

        private long maxPages;

        private long pageNumber;

        private static final int COLUMN_COUNT = 33;

        private static final int ROW_COUNT = 32;

        private static final int PAGE_SIZE = (COLUMN_COUNT - 1) / 2 * ROW_COUNT;

        private byte[] bytes = new byte[PAGE_SIZE];

        public MemoryTableModel(File bus) {
            Arrays.fill(bytes, (byte) 0);
            try {
                this.file = new RandomAccessFile(bus, "r");
                fileSize = file.length();
                maxPages = fileSize / PAGE_SIZE;
                if (fileSize % PAGE_SIZE != 0) {
                    maxPages++;
                }
                this.file.seek(0);
                this.file.read(bytes);
            } catch (IOException e) {
                file = null;
            }
        }

        /**
         * Set the current memory page to be inspected by the table.
         *
         * @param pageNumber The page number, from 0 to 255 (00 to FF hex)
         */
        public void setPageNumber(long pageNumber) {
            this.pageNumber = pageNumber;
            try {
                if (this.file != null) {
                    this.file.seek(pageNumber * PAGE_SIZE);
                    this.file.read(bytes);
                }
            } catch (IOException e) {

            }
        }

        /**
         * Returns the current page number being inspected by the table.
         *
         * @return The page number being inspected, from 0 to 255 (00 to FF
         *         hex)
         */
        public long getPageNumber() {
            return this.pageNumber;
        }

        public long getMaxPages() {
            return this.maxPages;
        }

        public int getRowCount() {
            return ROW_COUNT;
        }

        public int getColumnCount() {
            return COLUMN_COUNT;
        }

        @Override
        public String getColumnName(int i) {
            return null;
        }

        @Override
        public Class<?> getColumnClass(int i) {
            return String.class;
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return (column > 0 && column < ASCII_COL_START) && false;
        }

        public Object getValueAt(int row, int column) {
            if (column == 0) {
                return Utils.wordToHex(fullAddress(row, 1));
            } else if (column < 17) {
                int index = ((row * (COLUMN_COUNT - 1) / 2) + (column - 1));
                if (index < bytes.length) // Display hex value of the data
                {
                    return Utils.byteToHex(bytes[index]);
                } else {
                    return "??";
                }
            } else {
                int index = ((row * (COLUMN_COUNT - 1) / 2) + (column - 17));
                if (index < bytes.length) // Display the ASCII equivalent (if printable)
                {
                    return Utils.byteToAscii(bytes[index]);
                } else {
                    return "??";
                }
            }
        }

        @Override
        public void setValueAt(Object o, int row, int column) {
            /*
             * if (column > 0) {
             * String hexValue = (String) o;
             * int fullAddress = fullAddress(row, column);
             * int newValue = Integer.parseInt(hexValue, 16) & 0xff;
             * bus.write(fullAddress, newValue);
             * fireTableCellUpdated(row, column);
             * } */
        }

        private long fullAddress(int row, int column) {
            int pageAddress = ((row * (COLUMN_COUNT - 1) / 2) + (column - 1));
            return (pageNumber * PAGE_SIZE) | pageAddress;
        }

    }

}
