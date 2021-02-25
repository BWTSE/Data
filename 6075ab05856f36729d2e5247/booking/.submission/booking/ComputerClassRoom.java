package booking;


import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ComputerClassRoom extends ClassRoom{
	
	int open,close;
	
	public ComputerClassRoom(String name, String desc, boolean hasProjector, int openingHr, int closingHr){
		super(name, desc, hasProjector);
		open = openingHr;
		close = closingHr;
	}
	


    @Override
    public Optional<Booking> book(Interval i, User u) {
        if (!isBookable(i,u)
            || i.getStart().getHour() < open
            || i.getEnd().getHour() > close
            
        ) {
            return Optional.empty();
        }

        Booking b = new Booking(i, u);
        addBooking(b);

        return Optional.of(b);
    }

}