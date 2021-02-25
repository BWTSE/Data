package booking;

import java.util.Optional;
import java.util.List;

public interface Room {
	String getName();
	String getDescription();
	
	boolean hasProjector();
	boolean hasWhiteboard();
	
	Optional<Booking> book(Interval interval, User customer);
	List<Booking> getBookings();
}