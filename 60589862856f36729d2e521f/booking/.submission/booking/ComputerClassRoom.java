package booking;

import java.util.Optional;
import java.util.List;

public class ComputerClassRoom implements Room {

    private final Room inner;

    public ComputerClassRoom(
    	String name, 
    	String description, 
    	boolean hasProjector, 
    	int opensAt, 
    	int closesAt
	) {
        this.inner = new ClassRoom(
        	new ComputerRoom(
        		new BasicRoom(name, description),
        		opensAt,
        		closesAt
    		),
    		hasProjector
		);
    }
    
    @Override
	public String getName() {
		return inner.getName();
	}

    @Override
	public String getDescription() {
		return inner.getDescription();
	}

	@Override
    public boolean hasProjector() {
        return inner.hasProjector();
    }
    

    @Override
    public boolean hasWhiteboard() {
        return inner.hasWhiteboard();
    }

    @Override
	public List<Booking> getBookings() {
		return inner.getBookings();
	}

    /*
    Only allows bookings that start on the hour between the opening hours. (Floors second and microsecond values).
    */
    @Override
    public Optional<Booking> book(Interval interval, User customer) {
        return inner.book(interval, customer);
    }
}
