package AdvancedReplacement;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {

	String[] columnNames = { "Item Name", "Serial number", "Description", "Price" };
	ArrayList<Item> rows = new ArrayList();
	
	
	private Object[][] data = {{"","","",""} };

	// ??
	public final Object[] longValues = { "Jane", "Kathy", "None of the above", new Integer(20), Boolean.TRUE };

	// �÷�����.
	public int getColumnCount() {
		return columnNames.length;
	}

	// row ����.
	public int getRowCount() {
		return data.length;
	}

	// �÷� ����.
	public String getColumnName(int col) {
		return columnNames[col];
	}

	// ������ ��ȯ.
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	// �̰͵� �� �𸣰ڴ�.

	// API ����
	// Returns the most specific superclass for all the cell values in the
	// column. This is used by the JTable to set up a default renderer and
	// editor for the

	/*
	 * JTable uses this method to determine the default renderer/ editor for
	 * each cell.
	 * 
	 * If we didn't implement this method, then the last column would
	 * contain text ("true"/"false"), rather than a check box.
	 */
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/*
	 * Don't need to implement this method unless your table's editable.
	 */
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	/*
	 * Don't need to implement this method unless your table's data can
	 * change.
	 */
	// ���̺� �����Ͱ� �������������� �������ص���.
	public void setValueAt(Object value, int row, int col) {

		

		data[row][col] = value;

		// Cell value�� �����Ǿ����� �˸�.
		fireTableCellUpdated(row, col);

	}

}
