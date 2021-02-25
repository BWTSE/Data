package booking;

import java.time.LocalTime;
import java.util.Optional;

public class ComputerClassRoom extends ClassRoom {
    private final LocalTime startBookable;
    private final LocalTime endBookable;

    public ComputerClassRoom(String name, String description, boolean hasProjector, int firstBookableHour, int lastBookableHour) {
        super(name, description, hasProjector);
        this.startBookable = LocalTime.of(firstBookableHour, 0);
        this.endBookable = LocalTime.of(lastBookableHour, 0);
    }

    /*
    Only allows bookings within the bookable interval.
    */
    @Override
    public Optional<Booking> book(Interval interval, User customer) {
        Interval adjustedInterval = adjustInterval(interval);
        if (adjustedInterval.getStart().toLocalTime().isBefore(startBookable)
            || adjustedInterval.getEnd().toLocalTime().isAfter(endBookable)) {
            return Optional.empty();
        }

        return super.book(adjustedInterval, customer);
    }
}
