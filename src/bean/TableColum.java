package bean;

import java.util.Objects;


public class TableColum {
	Long columId;
	String columnName;
	String tableName;


	public TableColum() {
	}

	public TableColum(Long columId, String columnName, String tableName) {
		this.columId = columId;
		this.columnName = columnName;
		this.tableName = tableName;
	}

	public Long getColumId() {
		return this.columId;
	}

	public void setColumId(Long columId) {
		this.columId = columId;
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public TableColum columId(Long columId) {
		this.columId = columId;
		return this;
	}

	public TableColum columnName(String columnName) {
		this.columnName = columnName;
		return this;
	}

	public TableColum tableName(String tableName) {
		this.tableName = tableName;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof TableColum)) {
			return false;
		}
		TableColum tableColum = (TableColum) o;
		return Objects.equals(columId, tableColum.columId) && Objects.equals(columnName, tableColum.columnName) && Objects.equals(tableName, tableColum.tableName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(columId, columnName, tableName);
	}

	@Override
	public String toString() {
		return "{" +
			" columId='" + getColumId() + "'" +
			", columnName='" + getColumnName() + "'" +
			", tableName='" + getTableName() + "'" +
			"}";
	}
	
}