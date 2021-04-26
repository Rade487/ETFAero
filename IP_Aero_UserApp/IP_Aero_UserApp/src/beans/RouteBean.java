package beans;

import java.io.Serializable;
import java.util.List;

import dao.RouteDAO;
import dto.Route;

public class RouteBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3497762611515317873L;

	public List<Route> getAll() {
		return RouteDAO.selectAllRoutes();
	}
}
