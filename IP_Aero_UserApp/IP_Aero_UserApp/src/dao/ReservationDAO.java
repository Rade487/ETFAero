package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dto.Flight;
import dto.Reservation;
import dto.User;

public class ReservationDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT = "INSERT INTO reservation (user_id, flight_id, status, comment, num_of_pass, weight, file_name, flight_return_id, created_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_ALL_BY_USER_ID= "SELECT * FROM reservation where user_id = ?";
	private static final String SQL_UPDATE_RESERVATION = "UPDATE reservation set status = ? where created_date = ?";

	public static boolean insert(Reservation reservation) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { reservation.getUser().getId(), reservation.getFlight().getId(), reservation.getStatus(), reservation.getComment(), reservation.getNumOfPass(), reservation.getWeight(), reservation.getFileName(), reservation.getFlightReturnId(), reservation.getCreatedDate()};
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
	public static List<Reservation> selectAllReservationByUserId(int id_) {
		List<Reservation> reservations = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		User user = null;
		Flight flight = null;
		Object values[] = {id_};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL_BY_USER_ID, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				int user_id = rs.getInt("user_id");
				int flight_id = rs.getInt("flight_id");
				String status = rs.getString("status");
				String comment = rs.getString("comment");
				int num_of_pass = rs.getInt("num_of_pass");
				String weight = rs.getString("weight");
				String file_name = rs.getString("file_name");
				int flight_return_id = rs.getInt("flight_return_id");
				Timestamp createdDate = rs.getTimestamp("created_date");
				user = UserDAO.selectById(user_id);
				flight = FlightDAO.selectById(flight_id);	
				reservations.add(new Reservation(user, flight, status, comment, num_of_pass, weight, file_name, flight_return_id, createdDate));
				
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return reservations;
	}
	public static boolean update(String status, String createdDate) {
		boolean retVal = false;
		Connection connection = null;
		
		Object values[] = {status, createdDate };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE_RESERVATION, true, values);
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
