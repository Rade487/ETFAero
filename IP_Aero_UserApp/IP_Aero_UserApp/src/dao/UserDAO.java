package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User;

public class UserDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD = "SELECT * FROM user WHERE user_name=? AND user_pass=?";
	private static final String SQL_INSERT = "INSERT INTO user (user_name, user_pass, email, name, last_name, address, country, type, user_group) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM user WHERE id = ?";
	private static final String SQL_EMAIL_EXIST = "SELECT * FROM user WHERE email= ?";
	
	public static User selectByUsernameAndPassword(String username, String password){
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {username, password};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_SELECT_BY_USERNAME_AND_PASSWORD, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				user = new User(rs.getInt("id"), rs.getString("user_name"), rs.getString("user_pass"),rs.getString("email"),rs.getString("name"), rs.getString("last_name"),rs.getString("address"),rs.getString("country"), rs.getString("type"),rs.getString("user_group"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return user;
	}
	public static boolean insert(User user) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { user.getUsername(), user.getPassword(), user.getEmail(), user.getName(), user.getLastName(), user.getAddress(), user.getCountry(), user.getType(), user.getUserGroup()};
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
	public static User selectById(int id){
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_SELECT_BY_ID, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				user = new User(rs.getInt("id"), rs.getString("user_name"), rs.getString("user_pass"),rs.getString("email"),rs.getString("name"), rs.getString("last_name"),rs.getString("address"),rs.getString("country"), rs.getString("type"),rs.getString("user_group"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return user;
	}
	public static User isEmailExist(String email){
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {email};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_EMAIL_EXIST, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				user = new User(rs.getInt("id"), rs.getString("user_name"), rs.getString("user_pass"),rs.getString("email"),rs.getString("name"), rs.getString("last_name"),rs.getString("address"),rs.getString("country"), rs.getString("type"),rs.getString("user_group"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return user;
	}
}
