package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ComputerClassRoom extends ClassRoom {
	
	private final int openingHour;
	private final int closingHour;
	
    public ComputerClassRoom(String name, String description, boolean projector, int openingHour, int closingHour) {
        super(name, description, projector);
        this.openingHour = openingHour;
		this.closingHour = closingHour;
    }
    
    public int getOpeningHour() {
    	return this.openingHour;
    }
    
    public int getClosingHour() {
    	return this.closingHour;
    }

    /*
    Only allows bookings during the specified period of a day.
    */
	@Override
    public Optional<Booking> book(Interval i, User u) {
        if (!this.isDuringOpenTime(i)) {
            return Optional.empty();
        }

        return super.book(i, u);
    }

    private boolean isDuringOpenTime(Interval i) {
    	return this.openingHour <= i.getStart().getHour() &&
    		i.getEnd().getHour() <= this.closingHour;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
            && Objects.equals(this.getOpeningHour(), ((ComputerClassRoom)o).getOpeningHour())
            && Objects.equals(this.getClosingHour(), ((ComputerClassRoom)o).getClosingHour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        	super.hashCode(),
        	this.getOpeningHour(),
			this.getClosingHour()
        );
    }
}
