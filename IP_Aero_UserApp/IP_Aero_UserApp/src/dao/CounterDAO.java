package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dto.Counter;
public class CounterDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ALL = "SELECT * FROM counter order by id desc limit 30";
	private static final String SQL_COUNTER_EXIST = "SELECT * FROM counter where date_ = ?";
	private static final String SQL_UPDATE_COUNTER = "UPDATE counter set number_of_visitor = ? where date_ = ?";
	private static final String SQL_INSERT = "INSERT INTO counter (date_, number_of_visitor) VALUES (?, ?)";
	
	public static ArrayList<Counter> selectAll() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		ArrayList<Counter> counters = new ArrayList<>();
		Connection connection = null;
		ResultSet rs= null;
		// Step 1: Establishing a Connection
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,	SQL_SELECT_ALL, false);
			rs = pstmt.executeQuery();
			while (rs.next()){
				int id = rs.getInt("id");
				Date date_ = rs.getDate("date_");
				int numberOfVisitor = rs.getInt("number_of_visitor");
				counters.add(new Counter(id, date_, numberOfVisitor));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return counters;
	}
	
	public static Counter isDateExist(String date){
		Counter counter = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {date};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_COUNTER_EXIST, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				int id = rs.getInt("id");
				Date date_ = rs.getDate("date_");
				int number_of_visitor = rs.getInt("number_of_visitor");
				counter = new Counter(id,date_, number_of_visitor);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return counter;
	}
	public static boolean update(String date_, int numberOfVisitors) {
		boolean retVal = false;
		Connection connection = null;
		
		Object values[] = {numberOfVisitors, date_ };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE_COUNTER, true, values);
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows == 0)
				retVal = false;
			else
				retVal = true;
			pstmt.close();
		} catch (SQLException e) {
			retVal = false;
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	public static boolean insert(String date_) throws ParseException {
		boolean retVal = false;
		Connection connection = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsed =format.parse(date_);
	    Date sql = new java.sql.Date(parsed.getTime());
		Object values[] = {sql, 1};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT, true,
					values);
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows == 0)
				retVal = false;
			else
				retVal = true;
			pstmt.close();
		} catch (SQLException e) {
			retVal = false;
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
}
