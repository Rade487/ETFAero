package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Route;

public class RouteDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM route WHERE id=?";
	private static final String SQL_SELECT_BY_ORIGIN_COUNTRY = "SELECT * FROM route where origin_country=?";
	private static final String SQL_SELECT_BY_ORIGIN_COUNTRY_AND_TOWN = "SELECT * FROM route where origin_country=? and origin_town=?";
	private static final String SQL_SELECT_BY_ORIGIN_COUNTRY_AND_TOWN_AND_DC = "SELECT * FROM route where origin_country=? and origin_town=? and destination_country=?";
	private static final String SQL_SELECT_ROUTE_ID = "SELECT * FROM route where origin_country=? and origin_town=? and destination_country=? and destination_town=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM route";
	
	public static Route selectRoute(int id){
		Route route = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_ID, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				route = new Route(rs.getInt("id"), rs.getString("origin_country"),rs.getString("origin_town"),rs.getString("destination_country"),rs.getString("destination_town"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return route;
	}
	
	public static List<Route> selectAllRoutes() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Route> routes = new ArrayList<>();
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
	
	public static List<Route> getRoutesByOriginCountry(String originCountry){
		List<Route> routes = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {originCountry};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_ORIGIN_COUNTRY, false, values);
			
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
	
	public static List<Route> getRoutesByOriginCountryAndTown(String originCountry, String originTown){
		List<Route> routes = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {originCountry, originTown};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_ORIGIN_COUNTRY_AND_TOWN, false, values);
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
	public static List<Route> getRoutesByOriginCountryAndTownAndDC(String originCountry, String originTown, String destinationCountry){
		List<Route> routes = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {originCountry, originTown, destinationCountry};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_ORIGIN_COUNTRY_AND_TOWN_AND_DC, false, values);
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
	
	public static int getRouteIdAndAllFlight(String originCountry, String originTown, String destinationCountry, String destinationTown){
		List<Route> routes = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		int id = 0;
		Object values[] = {originCountry, originTown, destinationCountry, destinationTown};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ROUTE_ID, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				id = rs.getInt("id");
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
		return id;
	}
}
