package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Objects;
import java.util.Optional;

public class ClassRoom extends Room {

    private final boolean hasProjector;

    public ClassRoom(String name, String description, boolean hasProjector) {
        super(name, description);
        this.hasProjector = hasProjector;
    }

    public boolean hasProjector() {
        return this.hasProjector;
    }

    /*
    Only allows bookings that start on the hour. (Floors second and microsecond values).
    */
    @Override
    public Optional<Booking> book(Interval interval, User customer) {
        Interval adjustedInterval = adjustInterval(interval);

        if (
                adjustedInterval.getStart().getMinute() != 0
                        || adjustedInterval.getEnd().getMinute() != 0
                        || adjustedInterval.getEnd().with(ChronoField.HOUR_OF_DAY, 0).isAfter(adjustedInterval.getStart())
        ) {
            return Optional.empty();
        }

        return super.book(adjustedInterval, customer);
    }

    /**
     * Adjust a classroom booking interval
     * @param interval requested interval
     * @return adjusted interval
     */
    protected Interval adjustInterval(Interval interval) {
        LocalDateTime bookingStart = interval.getStart()
                .with(ChronoField.SECOND_OF_MINUTE, 0)
                .with(ChronoField.NANO_OF_SECOND, 0);

        LocalDateTime bookingEnd = interval.getEnd()
                .with(ChronoField.SECOND_OF_MINUTE, 0)
                .with(ChronoField.NANO_OF_SECOND, 0);

        return new Interval(bookingStart, bookingEnd);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
                && Objects.equals(this.hasProjector(), ((ClassRoom) o).hasProjector());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.hasProjector());
    }
}
