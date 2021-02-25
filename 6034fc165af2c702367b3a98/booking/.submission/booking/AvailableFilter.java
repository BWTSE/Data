package booking;

public class AvailableFilter extends Filter {
	
	public AvailableFilter(Room room) {
		super(room);
	}
	
	@Override
	public boolean valid(Booking b) {
		Interval i = b.getInterval();
		
        for (Booking booking : super.room.getBookings()) {
            if (booking.getInterval().overlapsWith(i)) {
                return false;
            }
        }

        return super.next(b);
	}
	
}