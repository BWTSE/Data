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
	private final boolean p;
	private final int hourOpening, hourClosing;
	private final Filter filterChain;
	
	public ComputerClassRoom(String n, String d, boolean p, int hourOpening, int hourClosing) {
		this.n = n;
		this.d = d;
		this.p = p;
		this.hourOpening = hourOpening;
		this.hourClosing = hourClosing;
		this.filterChain = new AvailableFilter(this)
    				.setNext(new StartBeforeEndFilter(this))
    				.setNext(new StartAndEndAtHourFilter(this))
    				.setNext(new ComputerClassRoomOpenFilter(this));
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
    
    public int getHourOpening() {
    	return this.hourOpening;
    }
    
    public int getHourClosing() {
    	return this.hourClosing;
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