package booking;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class GroupRoom implements Room {

    private final String n;
    private final String d;
    private final boolean w;

    private final List<Booking> bs = new LinkedList<>();
    
	private final Filter filterChain;

    protected GroupRoom(String n, String d, boolean w) {
        this.n = n;
        this.d = d;
        this.w = w;
        
        this.filterChain = new AvailableFilter(this)
    				.setNext(new StartBeforeEndFilter(this))
    				.setNext(new UserAlreadyHasBookingFilter(this));
    }

    public String getName() {
        return this.n;
    }

    public String getDescription() {
        return this.d;
    }

    public boolean hasWhiteboard() {
        return this.w;
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
            && Objects.equals(this.hasWhiteboard(), ((GroupRoom) o).hasWhiteboard());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.hasWhiteboard());
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
