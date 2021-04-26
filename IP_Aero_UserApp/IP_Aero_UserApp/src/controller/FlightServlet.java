package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.FlightDAO;
import dto.Flight;
/**
 * Servlet implementation class FlightServlet
 */
@WebServlet("/FlightServlet")
public class FlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		List<Flight> flights = null;
		List<Flight> fiveFlights = null;
		HttpSession session = request.getSession();
		String country = (String) session.getAttribute("country");
		if (action.equals("allflight")) {
			flights = FlightDAO.selectAllFlights();
			if (country != null) {
				
				flights = flights.stream().filter(f -> f.getRoute().getDestinationCountry().equals(country)).collect(Collectors.toList());
			}else {
				flights = flights.stream().filter(f -> f.getRoute().getDestinationCountry().equals("Serbia")).collect(Collectors.toList());
			}
			Collections.sort(flights);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			fiveFlights = flights.stream().filter(f -> f.getReturn_().compareTo(timestamp)> 0).limit(5).collect(Collectors.toList());
		}
		if (action.equals("allflight2")) {
			flights = FlightDAO.selectAllFlights();
			if(country !=null) {
				flights = flights.stream().filter(f -> f.getRoute().getOriginCountry().equals(country)).collect(Collectors.toList());
			}else {
				flights = flights.stream().filter(f -> f.getRoute().getOriginCountry().equals("Serbia")).collect(Collectors.toList());
			}
			Collections.sort(flights);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			fiveFlights = flights.stream().filter(f -> f.getReturn_().compareTo(timestamp)> 0).limit(5).collect(Collectors.toList());
		}
		if(action.equals("allflightdep")) {
			fiveFlights = FlightDAO.selectAllFlights();
		}
		
		
		String json = new Gson().toJson(fiveFlights);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
