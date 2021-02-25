package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

public class StartAndEndAtHourFilter extends Filter {
	
	public StartAndEndAtHourFilter(Room room) {
		super(room);
	}
	
	@Override
	public boolean valid(Booking b) {
		Interval i = b.getInterval();
		
		LocalDateTime bst = i.getStart()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        LocalDateTime bed = i.getStart()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        return (!(bed.getMinute() != 0|| bed.with(ChronoField.HOUR_OF_DAY, 0).isAfter(bst))) && super.next(b);
	}
	
}