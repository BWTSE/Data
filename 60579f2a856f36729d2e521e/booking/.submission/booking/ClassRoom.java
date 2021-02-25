package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ClassRoom implements Room {

    private final String name;
    private final String description;

    private final List<Booking> bookings = new LinkedList<>();

    private boolean hasProjector;

    public ClassRoom(String name, String description, boolean hasProjector) {
        this.name = name;
        this.description = description;
        this.hasProjector = hasProjector;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean hasProjector() {
        return this.hasProjector;
    }

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
    Only allows bookings that start on the hour. (Floors second and microsecond values).
    Also makes sure that the booking is valid and that the room is available.
    */
    public Optional<Booking> book(Interval interval, User user) {
        if (
            interval.getStart().with(ChronoField.HOUR_OF_DAY, 0).isAfter(interval.getEnd())
            || !available(interval)
            || !startAndEndAtHour(interval)
            || !startBeforeEnd(interval)
        ) {
            return Optional.empty();
        }

        Booking booking = new Booking(interval, user);
        bookings.add(booking);

        return Optional.of(booking);
    }

    private static boolean startBeforeEnd(Interval interval) {
        return !(interval.getEnd().isBefore(interval.getStart()) || interval.getStart().isBefore(LocalDateTime.now()));
    }

    private static boolean startAndEndAtHour(Interval interval) {
        LocalDateTime startAtHour = interval.getStart()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        LocalDateTime endAtHour = interval.getEnd()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        return !(endAtHour.getMinute() != 0|| endAtHour.with(ChronoField.HOUR_OF_DAY, 0).isAfter(startAtHour));
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
