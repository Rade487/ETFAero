package controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBean;
import dto.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String address = "/index.jsp";
		String action = request.getParameter("action");
		String errors;
		String error;
		UserBean userBean = new UserBean();
		
		HttpSession session = request.getSession();
		if(action.equals("login")){		
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			try {
				if(userBean.login(username, password)){
					session.setAttribute("userBean", userBean);
					address = "/index.jsp";				
				}else {
					error = "Pogresno korisnicko ime ili lozinka.";
					request.setAttribute("error", error);
					address = "index.jsp";		
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (action.equals("logout")){
			session.invalidate();
			address = "index.jsp";
		}else if (action.equals("registration")) {
			String user_name = request.getParameter("username");
			String user_pass = request.getParameter("password");
			String user_pass1 = request.getParameter("password1");
			String email = request.getParameter("email");
			String name = request.getParameter("firstname");
			String last_name = request.getParameter("lastname");
			String address_ = request.getParameter("address");
			String country = request.getParameter("country");
			String type = request.getParameter("type");

			errors =userBean.checkDataValidation(user_name, user_pass, user_pass1, email, name, last_name);
			if (errors.length() > 10) {
				request.setAttribute("errors", errors);
				address = "registration.jsp";
			} else {				
				User user = new User(user_name, user_pass, email, name, last_name, address_, country, type, "user");
				userBean.setLoggedIn(true);
				userBean.setUser(user);
				userBean.add(user);
				session.setAttribute("userBean", userBean);
				address = "/index.jsp";
			}
				
		}else if(action.equals("reservation")) {
			address = "/WEB-INF/pages/reservation.jsp";
		}else if (action.equals("allreservations")){
			address = "/WEB-INF/pages/all_reservations.jsp";
		}
		else {
			address="/error.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
