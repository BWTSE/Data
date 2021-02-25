package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ClassRoom extends Room {

    private final boolean projector;

    public ClassRoom(String name, String description, boolean projector) {
        super(name, description);
        this.projector = projector;
    }

    public boolean hasProjector() {
        return this.projector;
    }

    /*
    Only allows bookings that start on the hour. (Floors second and microsecond values).
    Also makes sure that the booking is valid and that the room is available.
    */
	@Override
    public Optional<Booking> book(Interval i, User u) {
        if (
            i.getStart().with(ChronoField.HOUR_OF_DAY, 0).isAfter(i.getEnd())
            || !startAndEndAtHour(i)
        ) {
            return Optional.empty();
        }

        return super.book(i, u);
    }

    private static boolean startAndEndAtHour(Interval i) {
        LocalDateTime bst = i.getStart()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        LocalDateTime bed = i.getEnd()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        return !(bed.getMinute() != 0|| bed.with(ChronoField.HOUR_OF_DAY, 0).isAfter(bst));
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
            && Objects.equals(this.hasProjector(), ((ClassRoom)o).hasProjector());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.hasProjector());
    }
}
