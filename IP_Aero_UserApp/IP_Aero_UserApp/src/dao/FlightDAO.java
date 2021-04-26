package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dto.Flight;
import dto.Route;

public class FlightDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ALL = "SELECT * FROM flight";
	
	private static final String SQL_SELECT_TODAYS_FLIGHT = "SELECT * FROM flight where departure like ?";
	private static final String SQL_SELECT_ALL_BY_RETURN = "SELECT * FROM flight where `return` like ?";
	private static final String SQL_SELECT_ALL_BY_DEPARTURE = "SELECT * FROM flight where departure like ?";
	private static final String SQL_SELECT_ALL_BY_ROUTE_ID = "SELECT * FROM flight where route_id = ?";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM flight where id = ?";
	private static final String SQL_INSERT = "INSERT INTO flight (route_id, departure, return, passengers, cargo, file) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_FLIGHT_ID = "SELECT * FROM flight where departure like ? and route_id = ?";
	private static final String SQL_SELECT_FLIGHT_RETURN_ID = "SELECT * FROM flight where `return` like ? and route_id = ?";
	
	public static List<Flight> selectAllFlights() {
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Flight> flights = new ArrayList<>();
		Connection connection = null;
		ResultSet rs= null;
		Route route = null;
		// Step 1: Establishing a Connection
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,	SQL_SELECT_ALL, false);
			rs = pstmt.executeQuery();
			while (rs.next()){
				int id = rs.getInt("id");
				int route_id = rs.getInt("route_id");
				Timestamp departure = rs.getTimestamp("departure");
				Timestamp return_ = rs.getTimestamp("return");
				int passengers = rs.getInt("passengers");
				String cargo = rs.getString("cargo");
				String file = rs.getString("file");
				route = RouteDAO.selectRoute(route_id);
				flights.add(new Flight(id, route, departure, return_, passengers, cargo, file));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return flights;
	}
	
	public static List<Flight> selectAllTodaysFlights() {
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Flight> flights = new ArrayList<>();
		Connection connection = null;
		ResultSet rs= null;
		Route route = null;
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		String formatDate = formatter.format(date);
		formatDate = formatDate + "%";
		Object values[] = { formatDate };
		// Step 1: Establishing a Connection
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,	SQL_SELECT_TODAYS_FLIGHT, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				int id = rs.getInt("id");
				int route_id = rs.getInt("route_id");
				Timestamp departure = rs.getTimestamp("departure");
				Timestamp return_ = rs.getTimestamp("return");
				int passengers = rs.getInt("passengers");
				String cargo = rs.getString("cargo");
				String file = rs.getString("file");
				route = RouteDAO.selectRoute(route_id);
				flights.add(new Flight(id, route, departure, return_, passengers, cargo, file));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return flights;
	}
	
	public static boolean insert(Flight flight) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { flight.getRoute().getId(), flight.getDeparture(), flight.getReturn_(), flight.getPassengers(), flight.getCargo(), flight.getFile()};
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
	
	public static List<Flight> selectAllFlightsByRouteId(int id_) {
		List<Flight> flights = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Route route = null;
		Object values[] = {id_};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL_BY_ROUTE_ID, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				int id = rs.getInt("id");
				int route_id = rs.getInt("route_id");
				Timestamp departure = rs.getTimestamp("departure");
				Timestamp return_ = rs.getTimestamp("return");
				int passengers = rs.getInt("passengers");
				String cargo = rs.getString("cargo");
				String file = rs.getString("file");
				route = RouteDAO.selectRoute(route_id);
				flights.add(new Flight(id, route, departure, return_, passengers, cargo, file));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return flights;
	}
	
	public static int selectFlightId(String date, int routeId) {
		List<Flight> flights = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Route route = null;
		date += "%";
		Object values[] = {date, routeId};
		int id =0;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_FLIGHT_ID, false, values);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()){
				id = rs.getInt("id");
				int route_id = rs.getInt("route_id");
				Timestamp departure = rs.getTimestamp("departure");
				Timestamp return_ = rs.getTimestamp("return");
				int passengers = rs.getInt("passengers");
				String cargo = rs.getString("cargo");
				String file = rs.getString("file");
				route = RouteDAO.selectRoute(route_id);
				flights.add(new Flight(id, route, departure, return_, passengers, cargo, file));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return id;
	}
	
	public static int selectFlightReturnId(String date, int routeId) {
		List<Flight> flights = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Route route = null;
		date += "%";
		Object values[] = {date, routeId};
		int id =0;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_FLIGHT_RETURN_ID, false, values);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()){
				id = rs.getInt("id");
				int route_id = rs.getInt("route_id");
				Timestamp departure = rs.getTimestamp("departure");
				Timestamp return_ = rs.getTimestamp("return");
				int passengers = rs.getInt("passengers");
				String cargo = rs.getString("cargo");
				String file = rs.getString("file");
				route = RouteDAO.selectRoute(route_id);
				flights.add(new Flight(id, route, departure, return_, passengers, cargo, file));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return id;
	}
	
	public static Flight selectById(int id_){
		Flight flight = null;
		Connection connection = null;
		ResultSet rs = null;
		Route route = null;
		Object values[] = {id_};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_SELECT_BY_ID, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				int id = rs.getInt("id");
				int route_id = rs.getInt("route_id");
				Timestamp departure = rs.getTimestamp("departure");
				Timestamp return_ = rs.getTimestamp("return");
				int passengers = rs.getInt("passengers");
				String cargo = rs.getString("cargo");
				String file = rs.getString("file");
				route = RouteDAO.selectRoute(route_id);
				flight = new Flight(id, route, departure, return_, passengers, cargo, file);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return flight;
	}
	
	public static List<Flight> selectAllFlightsByReturn(String date) {
		List<Flight> flights = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Route route = null;
		date += "%";
		Object values[] = {date};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL_BY_RETURN, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				int id = rs.getInt("id");
				int route_id = rs.getInt("route_id");
				Timestamp departure = rs.getTimestamp("departure");
				Timestamp return_ = rs.getTimestamp("return");
				int passengers = rs.getInt("passengers");
				String cargo = rs.getString("cargo");
				String file = rs.getString("file");
				route = RouteDAO.selectRoute(route_id);
				flights.add(new Flight(id, route, departure, return_, passengers, cargo, file));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return flights;
	}
	public static List<Flight> selectAllFlightsByDeparture(String date) {
		List<Flight> flights = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Route route = null;
		date += "%";
		Object values[] = {date};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL_BY_DEPARTURE, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				int id = rs.getInt("id");
				int route_id = rs.getInt("route_id");
				Timestamp departure = rs.getTimestamp("departure");
				Timestamp return_ = rs.getTimestamp("return");
				int passengers = rs.getInt("passengers");
				String cargo = rs.getString("cargo");
				String file = rs.getString("file");
				route = RouteDAO.selectRoute(route_id);
				flights.add(new Flight(id, route, departure, return_, passengers, cargo, file));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return flights;
	}
}
