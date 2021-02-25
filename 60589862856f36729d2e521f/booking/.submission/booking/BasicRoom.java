package booking;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BasicRoom implements Room {
    private final String name;
    private final String description;

    private final List<Booking> bookings = new LinkedList<>();

    public BasicRoom(String name, String description) {
        this.name = name;
        this.description = description;
    }


	@Override
    public String getName() {
        return this.name;
    }

	@Override
    public String getDescription() {
        return this.description;
    }
    
    @Override
    public boolean hasProjector() {
        return false;
    }
    
    @Override
    public boolean hasWhiteboard() {
        return false;
    }

	@Override
    public List<Booking> getBookings() {
        return List.copyOf(this.bookings);
    }

    private boolean available(Interval interval) {
        for (Booking booking : this.getBookings()) {
            if (booking.getInterval().overlapsWith(interval)) {
                return false;
            }
        }

        return true;
    }

    /*
    Makes sure that the booking is valid and that the room is available.
     */
    @Override
    public Optional<Booking> book(Interval interval, User customer) {
        if (
            !this.available(interval) 
            || interval.getEnd().isBefore(interval.getStart())
            || interval.getStart().isBefore(LocalDateTime.now())
        ) {
            return Optional.empty();
        }

        Booking booking = new Booking(interval, customer);
        bookings.add(booking);

        return Optional.of(booking);
    }
}

