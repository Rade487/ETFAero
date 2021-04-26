package beans;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import dao.ReservationDAO;
import dto.Reservation;

public class ReservationBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3689291964415760303L;
	Reservation reservation = new Reservation();
	
	public List<Reservation> getAll(int id) {

		List<Reservation> reservations = ReservationDAO.selectAllReservationByUserId(id);
		Collections.sort(reservations);
		return reservations;
	}
	
	public boolean update(String createdDate) {
		return ReservationDAO.update("ponistena", createdDate);
	}
	
}
