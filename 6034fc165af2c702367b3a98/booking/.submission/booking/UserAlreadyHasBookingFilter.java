package booking;

import java.time.LocalDateTime;

public class UserAlreadyHasBookingFilter extends Filter {
	
	public UserAlreadyHasBookingFilter(Room room) {
		super(room);
	}
	
	@Override
	public boolean valid(Booking newBooking) {
		User u = newBooking.getBooker();
		
		for (Booking b : super.room.getBookings()) {
            if (b.getInterval().getEnd().isAfter(LocalDateTime.now()) && b.getBooker().equals(u)) {
                return false;
            }
        }

        return super.next(newBooking);
	}
	
}