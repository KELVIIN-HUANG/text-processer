package bean;

import java.util.Objects;

public class TableInfo {
	Long tableID ;
	String tableName;

	public TableInfo() {
	}

	public TableInfo(Long tableID, String tableName) {
		this.tableID = tableID;
		this.tableName = tableName;
	}

	public Long getTableID() {
		return this.tableID;
	}

	public void setTableID(Long tableID) {
		this.tableID = tableID;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public TableInfo tableID(Long tableID) {
		this.tableID = tableID;
		return this;
	}

	public TableInfo tableName(String tableName) {
		this.tableName = tableName;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof TableInfo)) {
			return false;
		}
		TableInfo tableInfo = (TableInfo) o;
		return Objects.equals(tableID, tableInfo.tableID) && Objects.equals(tableName, tableInfo.tableName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tableID, tableName);
	}


	@Override
	public String toString() {
		return  getTableName() ;
	}
	


}