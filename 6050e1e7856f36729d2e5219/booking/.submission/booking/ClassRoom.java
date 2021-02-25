package booking;

import java.time.temporal.ChronoField;
import java.util.Objects;

public class ClassRoom extends BasicRoom {

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
    Also makes sure that the booking is valid and that the room is available.
    */
    @Override
    protected boolean isValidBooking(Interval interval, User user) {
        return !interval.getStartTime().with(ChronoField.HOUR_OF_DAY, 0).isAfter(interval.getEndTime())
                && isAvailableDuring(interval)
                && startAndEndAtHour(interval)
                && startBeforeEnd(interval);
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

    @Override
    public String toString() {
        return String.format(
                "Room %s \"%s\" #bookings: %s",
                this.getName(),
                this.getDescription(),
                this.getBookings().size()
        );
    }
}
