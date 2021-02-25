package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ClassRoom implements Room {

    private final String n;
    private final String d;
    private final List<Booking> bs = new LinkedList<>();

	private final Filter filterChain;

    private boolean p;

    public ClassRoom(String n, String d, boolean p) {
        this.n = n;
        this.d = d;
        this.p = p;
        
        this.filterChain = new AvailableFilter(this)
    				.setNext(new StartBeforeEndFilter(this))
    				.setNext(new StartAndEndAtHourFilter(this));
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

	@Override
    public boolean book(Booking b) {
        if (!this.filterChain.valid(b)) {
            return false;
        }

        return true;
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
