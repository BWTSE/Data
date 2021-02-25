package booking;

import java.time.LocalDateTime;

public class StartBeforeEndFilter extends Filter {
	
	public StartBeforeEndFilter(Room room) {
		super(room);
	}
	
	@Override
	public boolean valid(Booking b) {
		Interval i = b.getInterval();
		
		return (!(i.getEnd().isBefore(i.getStart()) || i.getStart().isBefore(LocalDateTime.now()))) && super.next(b);
	}
	
}