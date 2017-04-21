package AdvancedReplacement;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {

	String[] columnNames = { "Item Name", "Serial number", "Description", "Price" };
	ArrayList<Item> rows = new ArrayList();
	
	
	private Object[][] data = {{"","","",""} };

	// ??
	public final Object[] longValues = { "Jane", "Kathy", "None of the above", new Integer(20), Boolean.TRUE };

	// 컬럼갯수.
	public int getColumnCount() {
		return columnNames.length;
	}

	// row 갯수.
	public int getRowCount() {
		return data.length;
	}

	// 컬럼 네임.
	public String getColumnName(int col) {
		return columnNames[col];
	}

	// 데이터 반환.
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	// 이것도 잘 모르겠다.

	// API 설명
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
	// 데이블 데이터가 수정되지않으면 구현안해도됨.
	public void setValueAt(Object value, int row, int col) {

		

		data[row][col] = value;

		// Cell value가 수정되었음을 알림.
		fireTableCellUpdated(row, col);

	}

}
