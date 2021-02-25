package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Room {

    private final String name;
    private final String description;

    private final List<Booking> bs = new LinkedList<>();

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public List<Booking> getBookings() {
        return List.copyOf(this.bs);
    }

    private boolean available(Interval i) {
        for (Booking b : this.getBookings()) {
            if (b.getInterval().overlapsWith(i)) {
                return false;
            }
        }
        return true;
    }

    public Optional<Booking> book(Interval i, User u) {
        if (!available(i) || !startBeforeEnd(i)) {
            return Optional.empty();
        }

        Booking b = new Booking(i, u);
        bs.add(b);

        return Optional.of(b);
    }

    private static boolean startBeforeEnd(Interval i) {
        return !(i.getEnd().isBefore(i.getStart()) || i.getStart().isBefore(LocalDateTime.now()));
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
            && Objects.equals(this.getName(), ((Room)o).getName())
            && Objects.equals(this.getDescription(), ((Room)o).getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        	super.hashCode(),
        	this.getName(),
        	this.getDescription()
        );
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
