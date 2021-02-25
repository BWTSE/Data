package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ComputerClassRoom implements Room{
	
	private final String n;
	private final String d;
	private final boolean p;
	private final int hO;
	private final int hC;
	private final List<Booking> bs = new LinkedList<>();
	
	public ComputerClassRoom(String n, String d, boolean p, int hO, int hC ){
		this.n = n;
		this.d = d;
		this.p = p;
		this.hO = hO;
		this.hC = hC;
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
    /*public Optional<Booking> book(Interval i, User u) {
        if (
            i.getStart().with(ChronoField.HOUR_OF_DAY, 0).isAfter(i.getEnd())
            || !available(i)
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
	// */
	public Optional<Booking> book(Interval i, User u) {
        if (this.userHasBookingAlready(u) || !this.available(i) || !startBeforeEnd(i)) {
            return Optional.empty();
        }

        Booking b = new Booking(i, u);
        bs.add(b);

        return Optional.of(b);
    }

    private boolean userHasBookingAlready (User u) {
        for (Booking b : this.getBookings()) {
            if (b.getInterval().getEnd().isAfter(LocalDateTime.now()) && b.getBooker().equals(u)) {
                return true;
            }
        }

        return false;
    }

    private static boolean startBeforeEnd(Interval i) {
        return !(i.getEnd().isBefore(i.getStart()) || i.getStart().isBefore(LocalDateTime.now()));
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