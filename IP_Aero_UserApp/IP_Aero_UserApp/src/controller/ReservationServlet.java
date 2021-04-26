package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

import beans.ReservationBean;
import beans.UserBean;
import dao.FlightDAO;
import dao.ReservationDAO;
import dao.RouteDAO;
import dao.UserDAO;
import dto.Flight;
import dto.Reservation;
import dto.Route;
import dto.User;

/**
 * Servlet implementation class ReservationServlet
 */
@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String OLD_FORMAT = "dd-MM-yyyy";
	final String NEW_FORMAT = "yyyy-MM-dd";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReservationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String address = "/index.jsp";
		String action = request.getParameter("action");
		String parameter = request.getParameter("cb");
		HttpSession session = request.getSession();
		String[] parts = null;
		List<Route> routes = null;
		List<Flight> flights = null;
		List<Reservation> reservations = null;
		
		if (parameter == null) {
			parameter = "test";
		}
		if (action != null) {
			if (action.equals("reservation")) {
				System.out.println("Rezervacija");
				String origin_country = "", origin_town="", destination_country="", destination_town="", date="", returnDate="", num_of_pass="0", weight="";
				String status = "nova";
				String comment = null;
				UserBean userBean = (UserBean) session.getAttribute("userBean");
				String file_name = null;
			    String file_name2="";
			    FileItem fileItems = null;
			    FileItemFactory factory = new DiskFileItemFactory();
			        ServletFileUpload upload = new ServletFileUpload(factory);
			        try {
			            List < FileItem > fields = upload.parseRequest(request);
			            fileItems = fields.get(0);
			            origin_country = fileItems.getString();
			            fileItems = fields.get(1);
			            origin_town = fileItems.getString();
			            fileItems = fields.get(2);
			            destination_country = fileItems.getString();
			            fileItems = fields.get(3);
			            destination_town = fileItems.getString();
			            fileItems = fields.get(4);
			            date = fileItems.getString();
			            fileItems = fields.get(5);
			            returnDate = fileItems.getString();
			            fileItems = fields.get(6);
			            if (fileItems.getFieldName().equals("description")) {
			            	weight = fileItems.getString();
			            	Iterator < FileItem > it = fields.iterator();
				            if (!it.hasNext()) {
				                return;
				            }
				            
				            while (it.hasNext()) {
				                FileItem fileItem = it.next();
				                boolean isFormField = fileItem.isFormField();
				                if (isFormField) {
				                    if (file_name == null) {
				                        if (fileItem.getFieldName().equals("file_name")) {
				                        	file_name = fileItem.getString();
				                        }
				                    }
				                } else {
				                    if (fileItem.getSize() > 0) {
				                    	file_name2=fileItem.getName();
				                        fileItem.write(new File("C:\\Users\\radet\\Downloads\\apache-tomcat-9.0.27-windows-x64\\apache-tomcat-9.0.27\\webapps\\specifications\\" + file_name2));
				                     }
				                }
				            }
			            }else {
			            	num_of_pass= fileItems.getString();
			            }
			            
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			        date = convertDate(date);
					returnDate = convertDate(returnDate);
					int routeId = RouteDAO.getRouteIdAndAllFlight(origin_country, origin_town, destination_country, destination_town);
					int flight_id = FlightDAO.selectFlightId(date, routeId);
					int flight_return_id = FlightDAO.selectFlightReturnId(returnDate, routeId);
					int user_id = userBean.getUser().getId();

				User user = UserDAO.selectById(user_id);
				Flight flight = FlightDAO.selectById(flight_id);
				Timestamp createdDate = new Timestamp(System.currentTimeMillis() + 3600);
				Reservation reservation = new Reservation(user, flight, status, comment, Integer.parseInt(num_of_pass), weight, file_name, flight_return_id, createdDate);
				ReservationDAO.insert(reservation);

				address = "/WEB-INF/pages/all_reservations.jsp";
				reservations = ReservationDAO.selectAllReservationByUserId(user_id);
				session.setAttribute("reservation", reservations);
				RequestDispatcher dispatcher = request.getRequestDispatcher(address);
				dispatcher.forward(request, response);
			} else if(action.equals("cancel")){
				String createdDate = request.getParameter("id");			
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date=null;
				try {
					date = dateFormat.parse(createdDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				int x = -1;

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.HOUR, x);
				ReservationBean reservationBean = new ReservationBean();		
				reservationBean.update(dateFormat.format(calendar.getTime()));
				address = "/WEB-INF/pages/all_reservations.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(address);
				dispatcher.forward(request, response);
			}else if (action.equals("reset")){
				address = "/WEB-INF/pages/reservation.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(address);
				dispatcher.forward(request, response);
			}  else {
				address= "/error.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(address);
				dispatcher.forward(request, response);
			}
		} else {

			if (parameter.equals("origin_town")) {
				routes = RouteDAO.getRoutesByOriginCountry(request.getParameter("p"));
			} else if (parameter.equals("destination_country")) {
				parts = request.getParameter("p").split("-");
				routes = RouteDAO.getRoutesByOriginCountryAndTown(parts[0], parts[1]);
			} else if (parameter.equals("destination_town")) {
				parts = request.getParameter("p").split("-");
				routes = RouteDAO.getRoutesByOriginCountryAndTownAndDC(parts[0], parts[1], parts[2]);
			} else {
				parts = request.getParameter("p").split("-");
				int id = RouteDAO.getRouteIdAndAllFlight(parts[0], parts[1], parts[2], parts[3]);
				flights = FlightDAO.selectAllFlightsByRouteId(id);
			}

		}
		if (parameter.equals("days")) {
			String json = new Gson().toJson(flights);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} else {
			String json = new Gson().toJson(routes);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String convertDate(String date) {
		String newDateString;

		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		Date d=null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sdf.applyPattern(NEW_FORMAT);
		newDateString = sdf.format(d);
		return newDateString;
		
	}

}
