package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.TableColum;
import bean.TableInfo;
import util.OracleUtils;

public class tableDao {

	public static Long queryTableId(String name) {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "SELECT TABLE_INFO.TABLE_ID FROM TABLE_INFO  WHERE TABLE_INFO.TABLE_NAME = ?";
		ResultSetHandler<TableInfo> resultSetHandler = new BeanHandler<TableInfo>(TableInfo.class,
				new BasicRowProcessor(new GenerousBeanProcessor()));
		Object[] params = new Object[] { name };
		try {
			TableInfo tableInfo = queryRunner.query(OracleUtils.getConnection(), sql, resultSetHandler, params);
			return tableInfo.getTableID();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<TableColum> getTableColumsByTableId(Long id) {
		QueryRunner queryRunner = new QueryRunner();
	String sql = "SELECT * FROM TABLE_COLUM WHERE TABLE_ID = ?";
	ResultSetHandler<List<TableColum>> resultSetHandler = new BeanListHandler<TableColum>(TableColum.class, new BasicRowProcessor(new GenerousBeanProcessor()));
	Object[] params = new Object[] {id};
	try {
	List<TableColum> colums	 = queryRunner.query(OracleUtils.getConnection(), sql, resultSetHandler, params);
	return colums;
	} catch (SQLException e) {
	e.printStackTrace();
	}
	return null;
	}

	public static List<TableInfo> getTables() {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "SELECT * FROM TABLE_INFO";
		ResultSetHandler<List<TableInfo>> resultSetHandler = new BeanListHandler<TableInfo>(TableInfo.class, new BasicRowProcessor(new GenerousBeanProcessor()));
		Object[] params = new Object[] {};
		try {
		List<TableInfo> tableInfos = queryRunner.query(OracleUtils.getConnection(), sql, resultSetHandler, params);
		return tableInfos;
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return null;
	}

	public static int addTable(String name) {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "INSERT INTO TABLE_INFO VALUES (SEQ_TABLE_INFO.NEXTVAL,?)";
		Object[] params = new Object[] {name};
		try {
		return queryRunner.update(OracleUtils.getConnection(), sql, params);
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return 0;
	}

	public static int addColum(Long id, String name) {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "INSERT INTO TABLE_COLUM VALUES(SEQ_TABLE_COLUM.NEXTVAL,?,?)";
		Object[] params = new Object[] {name,id};
		try {
		return queryRunner.update(OracleUtils.getConnection(), sql, params);
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return 0;
	}

	public static List<TableInfo> gettablenams() {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "select table_name from user_tables where TABLESPACE_NAME is not null order by table_name ";
		ResultSetHandler<List<TableInfo>> resultSetHandler = new BeanListHandler<TableInfo>(TableInfo.class, new BasicRowProcessor(new GenerousBeanProcessor()));
		Object[] params = new Object[] {};
		try {
		List<TableInfo> tableInfos = queryRunner.query(OracleUtils.getConnection(), sql, resultSetHandler, params);
		return tableInfos;
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return null;
	}

	public static List<TableColum> gTablecolums(String tableName) {
		QueryRunner queryRunner = new QueryRunner();
								
		String sql = "SELECT T.COLUMN_NAME FROM USER_TAB_COLUMNS T WHERE T.TABLE_NAME= ?";
		ResultSetHandler<List<TableColum>> resultSetHandler = new BeanListHandler<TableColum>(TableColum.class, new BasicRowProcessor(new GenerousBeanProcessor()));
		Object[] params = new Object[] {tableName};
		try {
		List<TableColum> tableColums = queryRunner.query(OracleUtils.getConnection(), sql, resultSetHandler, params);
		return tableColums;
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return null;
	}
	// public static void main(String[] args) {
	// 	List<TableColum> tableInfos = gTablecolums("ADMIN");
	// 	// for (TableColum tableInfo : tableInfos) {
	// 	// 	System.out.println(tableInfo.getTableName());
	// 	// 	System.out.println(tableInfo.getColumName());
	// 	// }
	// 	System.out.println(tableInfos);

	// }
	
}
