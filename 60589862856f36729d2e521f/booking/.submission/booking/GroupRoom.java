package booking;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.List;

public class GroupRoom implements Room {

    private final boolean hasWhiteboard;
    private final Room inner;
    
    public GroupRoom(Room inner, boolean hasWhiteboard) {
        this.inner = inner;
        this.hasWhiteboard = hasWhiteboard;
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
        return this.hasWhiteboard || inner.hasWhiteboard();
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
    Due to the popularity of some specific group rooms,
     no user is allowed to have more than one upcoming booking for a group room.
     */
    @Override
    public Optional<Booking> book(Interval interval, User customer) {
        for (Booking booking : this.getBookings()) {
            if (booking.getInterval().getEnd().isAfter(LocalDateTime.now())
                    && booking.getBooker().equals(customer)
            ) {
                return Optional.empty();
            }
        }
        
        return inner.book(interval, customer);
    }
}
