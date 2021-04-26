package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.User;

public class UserDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD = "SELECT * FROM user WHERE user_name=? AND user_pass=? and user_group = 'admin'";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM user WHERE id=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM user where user_group=\"user\"";
	private static final String SQL_SELECT_ALL_EMPLOYEE = "SELECT * FROM user where user_group=\"employee\"";
	private static final String SQL_INSERT = "INSERT INTO user (user_name, user_pass, email, name, last_name, address, country, type, user_group) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM user where id = ?";
	private static final String SQL_UPDATE_USER = "UPDATE user set user_name = ?, user_pass = ?, email = ?, name = ?, last_name = ?, address=?, country = ?, type = ?, user_group = ? where id = ?;";
	
	
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
	public static ArrayList<User> selectAll() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		ArrayList<User> users = new ArrayList<>();
		Connection connection = null;
		ResultSet rs= null;
		// Step 1: Establishing a Connection
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,	SQL_SELECT_ALL, false);
			rs = pstmt.executeQuery();
			while (rs.next()){
				int id = rs.getInt("id");
				String user_name = rs.getString("user_name");
				String user_pass = rs.getString("user_pass");
				String email = rs.getString("email");
				String name = rs.getString("name");
				String last_name = rs.getString("last_name");
				String address = rs.getString("address");
				String country = rs.getString("country");
				String type = rs.getString("type");
				String user_group = rs.getString("user_group");
				users.add(new User(id, user_name, user_pass, email, name, last_name, address, country, type, user_group));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return users;
	}
	
	public static ArrayList<User> selectAllEmployee() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		ArrayList<User> users = new ArrayList<>();
		Connection connection = null;
		ResultSet rs= null;
		// Step 1: Establishing a Connection
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,	SQL_SELECT_ALL_EMPLOYEE, false);
			rs = pstmt.executeQuery();
			while (rs.next()){
				int id = rs.getInt("id");
				String user_name = rs.getString("user_name");
				String user_pass = rs.getString("user_pass");
				String email = rs.getString("email");
				String name = rs.getString("name");
				String last_name = rs.getString("last_name");
				String address = rs.getString("address");
				String country = rs.getString("country");
				String type = rs.getString("type");
				String user_group = rs.getString("user_group");
				users.add(new User(id, user_name, user_pass, email, name, last_name, address, country, type, user_group));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return users;
	}
	
	public static boolean insert(User user) {
		boolean retVal = false;
		Connection connection = null;
		//ResultSet generatedKeys = null;
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
//			generatedKeys = pstmt.getGeneratedKeys();
//			if (generatedKeys.next())
//				user.setId(generatedKeys.getInt(1));
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
	
	public static boolean update(User user) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = { user.getUsername(), user.getPassword(), user.getEmail(), user.getName(), user.getLastName(), user.getAddress(), user.getCountry(), user.getType(), user.getUserGroup(), user.getId()};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE_USER, true,
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
