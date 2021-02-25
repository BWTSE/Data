package booking;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class GroupRoom extends Room {

    private final boolean whiteboard;

    protected GroupRoom(String name, String description, boolean whiteboard) {
    	super(name, description);
        this.whiteboard = whiteboard;
    }
    
    public boolean hasWhiteboard() {
    	return this.whiteboard;
    }

    /*
    Due to the popularity of some specific group rooms,
     no user is allowed to have more than one upcoming booking for a group room.
    Also makes sure that the booking is valid and that the room is available.
     */
    @Override
    public Optional<Booking> book(Interval i, User u) {
        if (this.userHasBookingAlready(u)) {
            return Optional.empty();
        }
        return super.book(i, u);
    }

    private boolean userHasBookingAlready (User u) {
        for (Booking b : this.getBookings()) {
            if (b.getInterval().getEnd().isAfter(LocalDateTime.now()) && b.getBooker().equals(u)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
            && Objects.equals(this.hasWhiteboard(), ((GroupRoom)o).hasWhiteboard());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.hasWhiteboard());
    }
}
