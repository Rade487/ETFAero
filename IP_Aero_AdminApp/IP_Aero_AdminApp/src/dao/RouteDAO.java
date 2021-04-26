package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Route;

public class RouteDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ALL = "SELECT * FROM route";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM route WHERE id=?";
	private static final String SQL_INSERT = "INSERT INTO route (origin_country, origin_town, destination_country, destination_town) VALUES (?, ?, ?, ?)";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM route where id = ?";
	private static final String SQL_UPDATE_ROUTE = "UPDATE route set origin_country = ?, origin_town = ?, destination_country = ?, destination_town = ? where id = ?;";

	public static ArrayList<Route> selectAll() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		ArrayList<Route> routes = new ArrayList<>();
		Connection connection = null;
		ResultSet rs= null;
		// Step 1: Establishing a Connection
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,	SQL_SELECT_ALL, false);
			rs = pstmt.executeQuery();
			while (rs.next()){
				int id = rs.getInt("id");
				String origin_country = rs.getString("origin_country");
				String origin_town = rs.getString("origin_town");
				String destination_country = rs.getString("destination_country");
				String destination_town = rs.getString("destination_town");
				routes.add(new Route(id, origin_country, origin_town, destination_country, destination_town));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return routes;
	}
	
	public static Route selectById(int id){
		Route route = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_SELECT_BY_ID, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				route = new Route(rs.getInt("id"), rs.getString("origin_country"), rs.getString("origin_town"), rs.getString("destination_country"), rs.getString("destination_town"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return route;
	}
	
	public static boolean insert(Route route) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { route.getOrigin_country(), route.getOrigin_town(), route.getDestination_country(), route.getDestination_town()};
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
	
	public static boolean delete(int id) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_DELETE_BY_ID, true,
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
	
	public static boolean update(Route route) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { route.getOrigin_country(), route.getOrigin_town(), route.getDestination_country(), route.getDestination_town(), route.getId()};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE_ROUTE, true,
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
