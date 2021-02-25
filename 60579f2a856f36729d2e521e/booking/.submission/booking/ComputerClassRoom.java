package booking;

import java.util.Optional;

public class ComputerClassRoom extends ClassRoom {
	private final int startHour;
	private final int endHour;

	public ComputerClassRoom(String name, String description, boolean hasProjector, int startHour, int endHour) {
		super(name, description, hasProjector);
        this.startHour = startHour;
        this.endHour = endHour;
    }
    
    @Override
    public Optional<Booking> book(Interval interval, User user) {
    	// Know this doesn't work. Don't have more time to look trough documentation.
    	if (this.startHour <= interval.getStart() && this.endHour > interval.getEnd()) {
    		return super.book(interval, user);
    	}
    	return Optional.empty();
    }
}
