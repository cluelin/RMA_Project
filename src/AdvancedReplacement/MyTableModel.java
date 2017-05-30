package AdvancedReplacement;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {

	final public int initialTableRowCount = 5;

	String[] columnNames = { "Item Name", "Serial number", "Description", "Price", "Receive" };

	// private Object[][] data = { { "", "", "", "", false } };
	static private ArrayList<Object[]> dataArray;
	private int rowCount = 1;

	public MyTableModel() {

		dataArray = new ArrayList<Object[]>();

		for (int i = 0; i < initialTableRowCount; i++) {

			dataArray.add(new Object[] { "", "", "", "", false });

		}
	}

	public void addRow() {

		dataArray.add(new Object[] { "", "", "", "", false });
		fireTableRowsInserted(dataArray.size() - 1, dataArray.size() - 1);

		
	}

	// 컬럼갯수.
	public int getColumnCount() {
		return columnNames.length;
	}

	// row 갯수.
	public int getRowCount() {

		return dataArray.size();
	}

	public void clearData() {
		dataArray = new ArrayList<Object[]>();

		for (int i = 0; i < initialTableRowCount; i++) {

			dataArray.add(new Object[] { "", "", "", "", false });

		}
	}

	// 컬럼 네임.
	public String getColumnName(int col) {
		return columnNames[col];
	}

	// 데이터 반환.
	public Object getValueAt(int row, int col) {
		// return data[row][col];
		Object[] dataRow = dataArray.get(row);

		return dataRow[col];
	}

	// 해당 열에있는 데이터형으로 해당 컬럼 클래스를 지정해주는거지 뭐.

	// API 설명
	// Returns the most specific superclass for all the cell values in the
	// column. This is used by the JTable to set up a default renderer and
	// editor for the

	/*
	 * JTable uses this method to determine the default renderer/ editor for
	 * each cell.
	 * 
	 * If we didn't implement this method, then the last column would contain
	 * text ("true"/"false"), rather than a check box.
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
	 * Don't need to implement this method unless your table's data can change.
	 */
	// 데이블 데이터가 수정되지않으면 구현안해도됨.
	public void setValueAt(Object value, int row, int col) {

		if (row > dataArray.size() - 1) {

			dataArray.add(new Object[] { "", "", "", "", false });

		}

		// data[row][col] = value;
		Object[] rowData = dataArray.get(row);
		rowData[col] = value;

		// Cell value가 수정되었음을 알림.
		fireTableCellUpdated(row, col);

	}

}
