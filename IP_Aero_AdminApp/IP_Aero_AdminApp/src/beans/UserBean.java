package beans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dto.User;
import utils.SessionUtils;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 7138503128150191735L;
	private User user = new User();
	private boolean isLoggedIn = false;
	//private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public User getUser() {
		return user;
	}

	public String logout() {
		user = new User();
		isLoggedIn = false;
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login?faces-redirect=true";
	}

	public boolean isAdmin() {
		if (user.getUserGroup() != null) {
			if ((user.getUserGroup().equals("admin"))) {
				return true;
			}
		}
		return false;
	}

	public String login() {
		if ((user = UserDAO.selectByUsernameAndPassword(user.getUsername(), user.getPassword())) != null) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			isLoggedIn = true;
			return "index?faces-redirect=true";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
				"Incorrect Username and Passowrd", "Please enter correct username and Password"));
 		FacesContext.getCurrentInstance().addMessage("loginForm:password", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Pogresno korisnicko ime ili lozinka"));
		user = new User();
		isLoggedIn = false;
		return null;
	}

	public ArrayList<User> getAll() {
		return UserDAO.selectAll();
	}
	
	public ArrayList<User> getAllEmployee(){
		return UserDAO.selectAllEmployee();
	}
	
	public String edit(int id){ 
		user = UserDAO.selectById(id);
		//sessionMap.put("editUser", user); 
		return "/edit.xhtml?faces-redirect=true";  
	}
	public String editEmployee(int id){ 
		user = UserDAO.selectById(id);
		//sessionMap.put("editUser", user); 
		return "/editEmployee.xhtml?faces-redirect=true";  
	}
	public String delete(int id){ 
		UserDAO.delete(id);
		return "/users.xhtml?faces-redirect=true";  
	}
	public String deleteEmployee(int id) {
		UserDAO.delete(id);
		return "/employee.xhtml?faces-redirect=true";  
	}
	public String add(){
		user.setUserGroup("user");
		if(UserDAO.insert(user))
			return "users?faces-redirect=true";
		else
			return null;
	}
	public String addEmployee() {
		user.setUserGroup("employee");
		if(UserDAO.insert(user))
			return "employee?faces-redirect=true";
		else
			return null;
	}
	public String update() {
		if(UserDAO.update(user))
			return "users?faces-redirec=true";
		else
			return null;
	}
	public String updateEmployee() {
		if(UserDAO.update(user))
			return "employee?faces-redirec=true";
		else
			return null;
	}
	public String reset() {
		user.setUsername(null);
		user.setPassword(null);
		user.setEmail(null);
		user.setCountry(null);
		user.setType(null);
		user.setLastName(null);
		user.setUserGroup(null);
		user.setName(null);
		user.setAddress(null);
		return "/create.xhtml?faces-redirect=true";
	}
	public String resetEmployee() {
		user.setUsername(null);
		user.setPassword(null);
		user.setEmail(null);
		user.setCountry(null);
		user.setType(null);
		user.setLastName(null);
		user.setUserGroup(null);
		user.setName(null);
		user.setAddress(null);
		return "/createEmployee.xhtml?faces-redirect=true";
	}
	
}
