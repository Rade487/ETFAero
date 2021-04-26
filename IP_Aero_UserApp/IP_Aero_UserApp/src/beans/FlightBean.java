package beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import dao.FlightDAO;
import dto.Flight;

public class FlightBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 907087427686724751L;
	private Flight flight = new Flight();
	
	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public List<Flight> getAll() {
		List<Flight> flights = FlightDAO.selectAllFlights();
		Collections.sort(flights);
		return flights;
	}
	
	public List<Flight> getAllSerbia() {
		List<Flight> flights = FlightDAO.selectAllFlights();
		flights = flights.stream().filter(f -> f.getRoute().getDestinationCountry().equals("Serbia")).collect(Collectors.toList());
	
		Collections.sort(flights);
		return flights;
	}
	
	public List<Flight> getAllSerbia2() {
		List<Flight> flights = FlightDAO.selectAllFlights();
		flights = flights.stream().filter(f -> f.getRoute().getOriginCountry().equals("Serbia")).collect(Collectors.toList());
		Collections.sort(flights);
		return flights;
	}
	public List<Flight> getAll(String day){
		List<Flight> flights =null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date= new Date();
		if(day.equals("today")) {	
			flights = FlightDAO.selectAllFlightsByReturn(dateFormat.format(date));	
		}else if(day.equals("todayD")) {
			flights = FlightDAO.selectAllFlightsByDeparture(dateFormat.format(date));
		}else if(day.equals("yesterday")) {
			flights = FlightDAO.selectAllFlightsByReturn(dateFormat.format(yesterday()));	
		}else if(day.equals("yesterdayD")) {
			flights = FlightDAO.selectAllFlightsByDeparture(dateFormat.format(yesterday()));
		}else if (day.equals("tomorrow")){
			flights = FlightDAO.selectAllFlightsByReturn(dateFormat.format(tomorrow()));
		}else {
			flights = FlightDAO.selectAllFlightsByDeparture(dateFormat.format(tomorrow()));
		}
		Collections.sort(flights);
		return flights;
	}
	
	private Date yesterday() {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
	    return cal.getTime();
	}
	private Date tomorrow() {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, 1);
	    return cal.getTime();
	}

}
