package booking;

public class ComputerClassRoomOpenFilter extends Filter {
	
	public ComputerClassRoomOpenFilter(Room room) {
		super(room);
		
		assert(room instanceof ComputerClassRoom);
	}
	
	@Override
	public boolean valid(Booking b) {
		ComputerClassRoom room = (ComputerClassRoom) super.room;		
		
		int hourOpening = room.getHourOpening();
		int hourClosing = room.getHourClosing();

		// Don't like the law-of-demeter here...
        return (b.getInterval().getStart().getHour() >= hourOpening && b.getInterval().getEnd().getHour() <= hourClosing) && super.next(b);
	}
	
}