package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Objects;
import java.util.Optional;
import java.util.List;

public class ClassRoom implements Room {

    private final boolean hasProjector;
    private final Room inner;

    public ClassRoom(Room inner, boolean hasProjector) {
        this.inner = inner;
        this.hasProjector = hasProjector;
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
    public boolean hasProjector() {
        return this.hasProjector || inner.hasProjector();
    }
    

    @Override
    public boolean hasWhiteboard() {
        return inner.hasWhiteboard();
    }

    @Override
	public List<Booking> getBookings() {
		return inner.getBookings();
	}

    /*
    Only allows bookings that start on the hour. (Floors second and microsecond values).
    */
    @Override
    public Optional<Booking> book(Interval interval, User customer) {
        LocalDateTime bookingStart = interval.getStart()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        LocalDateTime bookingEnd = interval.getEnd()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        if (
            bookingStart.getMinute() != 0
            || bookingEnd.getMinute() != 0
            || bookingEnd.with(ChronoField.HOUR_OF_DAY, 0).isAfter(bookingStart)
        ) {
            return Optional.empty();
        }

        return inner.book(new Interval(bookingStart, bookingEnd), customer);
    }
}
