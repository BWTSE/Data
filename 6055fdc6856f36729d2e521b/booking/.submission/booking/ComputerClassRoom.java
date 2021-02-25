package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Objects;
import java.util.Optional;

public class ComputerClassRoom extends ClassRoom{
	
	private int openingHour;
	private int closingHour;
	
	public ComputerClassRoom(String roomName, String roomDescription, boolean hasProjector, int openingHour, int closingHour){
		super(roomName, roomDescription, hasProjector);
		this.openingHour = openingHour;
		this.closingHour = closingHour;
	}
	
	@Override
    public Optional<Booking> book(Interval interval, User customer) {
        LocalDateTime bookingStart = interval.getStart()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        LocalDateTime bookingEnd = interval.getEnd()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        if (
            bookingStart.getMinute() != 0
            || bookingEnd.getMinute() != 0
            || bookingEnd.with(ChronoField.HOUR_OF_DAY, 0).isAfter(bookingStart)
        ) {
            return Optional.empty();
        }
        
        if(!(bookingStart.getHour() >= openingHour && 
        (bookingEnd.getHour() < closingHour || 
        bookingEnd.getHour() == closingHour && bookingEnd.getMinute() == 0)))
        {
        	return Optional.empty();
        }

        return super.book(new Interval(bookingStart, bookingEnd), customer);
    }
}
