	package beans;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import dao.CounterDAO;
import dao.UserDAO;
import dto.Counter;
import dto.User;

public class UserBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3752498833729915268L;
	private User user = new User();
	private boolean isLoggedIn = false;

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public boolean login(String username, String password) throws ParseException {
		if ((user = UserDAO.selectByUsernameAndPassword(username, password)) != null) {
			isLoggedIn = true;
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date(System.currentTimeMillis());
			String formatDate = formatter.format(date);
			
			Counter counter = CounterDAO.isDateExist(formatDate);
			if(counter == null) {
				CounterDAO.insert(formatDate);
			}else {
				CounterDAO.update(formatDate, counter.getNumberOfVisitor() + 1);
			}
			
			
			return true;
		}
		return false;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void logout() {
		user = new User();
		isLoggedIn = false;
	}

	public User getUser() {
		return user;
	}
	
	public boolean add(User user) {
		return UserDAO.insert(user);
	}
	
	public boolean isAdmin() {
		if (user.getUserGroup() != null) {
			if ((user.getUserGroup().equals("admin"))) {
					return true;
			}
		}
		return false;
	}
	public String checkDataValidation(String user_name, String user_pass, String user_pass1, String email, String name, String last_name) {
		String errors = "<ul>";
		
		if (!Pattern.matches("[a-zA-Z0-9]+", user_name)) {
			errors += "<li>" + "Korisnicko ime mozda da sadrzi samo karaktere i brojeve" + "</li>";
		}
		if (!user_pass.equals(user_pass1)) {
			errors += "<li>" + "Lozinke se ne podudaraju" + "</li>";
		}
		if (!Pattern.matches("^\\S+@\\S+$", email)) {
			errors += "<li>" + "Email adresa nije u dobrom formatu" + "</li>";
		}
		if (UserDAO.isEmailExist(email) != null) {
			errors += "<li>" + "Email adresa je vec registrovana" + "</li>";
		}
		if(!Pattern.matches("[A-Za-z]+", name)) {
			errors += "<li>" + "Ime moze sa sadrzi samo karaktere" + "</li>";
		}
		if(!Pattern.matches("[A-Za-z]+", last_name)) {
			errors += "<li>" + "Prezime moze sa sadrzi samo karaktere" + "</li>";
		}
		errors += "</ul>";
		
		return errors;
	}

}
