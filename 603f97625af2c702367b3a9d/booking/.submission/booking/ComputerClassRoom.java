
package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ComputerClassRoom implements Room {

    private final String n;
    private final String d;


    private final List<Booking> bs = new LinkedList<>();

    private boolean p;

	private int start;
	private int end;

    public ComputerClassRoom(String n, String d, boolean p, int start, int end) {
        this.n = n;
        this.d = d;
        this.p = p;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return this.n;
    }

    public String getDescription() {
        return this.d;
    }

    public boolean hasProjector() {
        return this.p;
    }

    public List<Booking> getBookings() {
        return List.copyOf(this.bs);
    }

    private boolean available(Interval interval) {
    	
    	if(interval.getStart().getHour() < this.start || interval.getEnd().getHour() > this.end) {
    		return false;
    	}
    	
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
    public Optional<Booking> book(Interval i, User u) {
        if (
            i.getStart().with(ChronoField.HOUR_OF_DAY, 0).isAfter(i.getEnd())
            || !available(i)
            || !startAndEndAtHour(i)
            || !startBeforeEnd(i)
        ) {
            return Optional.empty();
        }

        Booking b = new Booking(i, u);
        bs.add(b);

        return Optional.of(b);
    }

    private static boolean startBeforeEnd(Interval i) {
        return !(i.getEnd().isBefore(i.getStart()) || i.getStart().isBefore(LocalDateTime.now()));
    }

    private static boolean startAndEndAtHour(Interval i) {
        LocalDateTime bst = i.getStart()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        LocalDateTime bed = i.getStart()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        return !(bed.getMinute() != 0|| bed.with(ChronoField.HOUR_OF_DAY, 0).isAfter(bst));
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
