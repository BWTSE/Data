package booking;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.List;

public class ComputerRoom implements Room {

    private final int opensAt;
    private final int closesAt;
    private final Room inner;
    
    public ComputerRoom(Room inner, int opensAt, int closesAt) {
        this.inner = inner;
        this.opensAt = opensAt;
        this.closesAt = closesAt;
    }

    @Override
	public String getName() {
		return inner.getName();
	}

    @Override
	public String getDescription() {
		return inner.getDescription();
	}

    @Override
    public boolean hasWhiteboard() {
        return inner.hasWhiteboard();
    }

    @Override
	public boolean hasProjector() {
		return inner.hasProjector();
	}
	
    @Override
	public List<Booking> getBookings() {
		return inner.getBookings();
	}

    /*
    Is only available during a specified period of time each day
     */
    @Override
    public Optional<Booking> book(Interval interval, User customer) {
        int startHour = interval.getStart().getHour();
        int endHour = interval.getEnd().getHour();
        
        if (startHour < this.opensAt || endHour > this.closesAt) {
        	return Optional.empty();
        }
        
        return inner.book(interval, customer);
    }
}
