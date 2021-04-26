package beans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.RouteDAO;
import dto.Route;

@ManagedBean
@SessionScoped
public class RouteBean implements Serializable{

	private static final long serialVersionUID = 4224474089760876164L;
	private Route route = new Route();
	
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	public String add(){
		if(RouteDAO.insert(route)) {
			return "locations?faces-redirect=true";
		}else
			
			return null;
	}
	public ArrayList<Route> getAll() {
		return RouteDAO.selectAll();
	}
	public String edit(int id){ 
		route = RouteDAO.selectById(id);
		return "/editLocation.xhtml?faces-redirect=true";  
	}
	public String delete(int id){ 
		RouteDAO.delete(id);
		return "/locations.xhtml?faces-redirect=true";  
	}
	public String reset() {
		route.setOrigin_country(null);
		route.setOrigin_town(null);
		route.setDestination_country(null);
		route.setDestination_town(null);
		return "/createLocation.xhtml?faces-redirect=true";
	}
	public String update() {
		if(RouteDAO.update(route))
			return "locations?faces-redirec=true";
		else
			return null;
	}
}
