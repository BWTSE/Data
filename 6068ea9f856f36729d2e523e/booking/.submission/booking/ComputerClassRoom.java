
package booking;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Objects;

public class ComputerClassRoom implements RoomWithProjector {
	private final ClassRoom classRoom;
	private final LocalTime opening;
	private final LocalTime closing;
	
	public ComputerClassRoom(ClassRoom classRoom, int opening, int closing) {
		this.classRoom = classRoom;
		this.opening = LocalTime.of(opening, 0);
		this.closing = LocalTime.of(closing, 0);
	}
	
	@Override
	public String getName() {
		return classRoom.getName();
	}
    
    @Override
	public String getDescription() {
		return classRoom.getDescription();
	}
    @Override
	public List<Booking> getBookings() {
		return classRoom.getBookings();
	}
    @Override
	public Optional<Booking> book(Interval i, User u) {
		if (opening.isAfter(i.getStart().toLocalTime()) 
		|| closing.isBefore(i.getEnd().toLocalTime())) {
			return Optional.empty();
		}
		
		return classRoom.book(i,u);
	}
    @Override
	public boolean hasProjector() {
		return classRoom.hasProjector();
	}
	
	@Override
    public boolean equals(Object o) {
        return classRoom.equals(o)
            && Objects.equals(opening, ((ComputerClassRoom) o).opening)
            && Objects.equals(closing, ((ComputerClassRoom) o).closing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classRoom.hashCode(), opening, closing);
    }

    @Override
    public String toString() {
        return classRoom.toString();
    }
}