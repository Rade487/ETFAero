package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Counter;

public class CounterDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ALL = "SELECT * FROM counter order by id desc limit 30";
	

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
}
