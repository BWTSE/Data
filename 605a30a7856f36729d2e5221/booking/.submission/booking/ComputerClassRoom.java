
package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ComputerClassRoom extends ClassRoom {
	
	private int opens, closes;
	
	public ComputerClassRoom(
		String name, 
		String description, 
		boolean hasProjector, 
		int opens, 
		int closes
	) {
		super(name, description, hasProjector);
		this.opens = opens;
		this.closes = closes;
	}
	
    @Override
	public Optional<Booking> book(Interval interval, User user) {
        if (interval.getStart().getHour() < opens) {
        	return Optional.empty();
        }
        
        if (interval.getEnd().getHour() < opens) {
        	return Optional.empty();
        }

        return super.book(interval, user);
    }
}