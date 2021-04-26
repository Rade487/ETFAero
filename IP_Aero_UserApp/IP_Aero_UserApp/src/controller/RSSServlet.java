package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.SyndFeedOutput;

import dao.FlightDAO;
import dto.Flight;
import rss.Feed;
import rss.RSSFeedWriter;


/**
 * Servlet implementation class RSSServlet
 */
@WebServlet("/rss")
public class RSSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RSSServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	// create the rss feed
        String copyright = "Copyright hold by IP2019";
        String title = "RSS test info";
        String description = "RSS test info";
        String language = "en";
        String link = "https://www.etf.unibl.org";
        Calendar cal = new GregorianCalendar();
        Date creationDate = cal.getTime();
        SimpleDateFormat date_format = new SimpleDateFormat(
                "EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US);
        String pubdate = date_format.format(creationDate);
        Feed rssFeeder = new Feed(title, link, description, language,
                copyright, pubdate);
        
        
        Flight flight = FlightDAO.selectById(1);
        rssFeeder.getMessages().add(flight);
        
        RSSFeedWriter writer = new RSSFeedWriter(rssFeeder, "C:\\Users\\radet\\eclipse-workspace\\IP_Aero_UserApp\\articles.rss");
        try {
            writer.write();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType("rss_2.0");
		feed.setTitle("IP Aero");
		feed.setDescription("Lista danasnjih letova");
		feed.setLink("http://localhost:8080/IP_Aero_UserApp/");
		List<SyndEntry> entries = new ArrayList<>();
		List<Flight> flights = FlightDAO.selectAllTodaysFlights();
	
		for (int i = 0; i < flights.size(); i++) {		
			SyndEntry item = new SyndEntryImpl();
			item.setTitle(flights.get(i).getDeparture().toString());
			item.setLink(flights.get(i).getRoute().getOriginCountry() + "(" + flights.get(i).getRoute().getOriginTown() + ")" + " - " + flights.get(i).getRoute().getDestinationCountry() + "(" + flights.get(i).getRoute().getDestinationTown() + ")" );
			SyndContent description_ = new SyndContentImpl();
			description_.setValue("Broj putnika:" + String.valueOf(flights.get(i).getPassengers()));
			item.setDescription(description_);
			entries.add(item);
	}
		feed.setEntries(entries); 
		try {
			response.getWriter().println(new SyndFeedOutput().outputString(feed));
		} catch (Exception ex) {
			System.out.println(ex.toString());
			response.sendError(500);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
