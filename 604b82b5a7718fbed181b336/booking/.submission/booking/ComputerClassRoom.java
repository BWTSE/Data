package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ComputerClassRoom extends ClassRoom{
	int start;
	int end;
	public ComputerClassRoom(String n, String d, boolean p, int start, int end){
		super(n,d,p);
		this.start = start;
		this.end = end;
	}
    public Optional<Booking> book(Interval i, User u) {
    if (
        i.getStart().with(ChronoField.HOUR_OF_DAY, 0).isAfter(i.getEnd())
        || !available(i)
        || !startAndEndAtHour(i)
        || !startBeforeEnd(i)
        || !isInOpenHours(i)
    ) {
        return Optional.empty();
    }

    Booking b = new Booking(i, u);
    bs.add(b);

    return Optional.of(b);
    }
    private boolean isInOpenHours(Interval i){
    	int s = i.getStart().getHour();
    	int e = i.getEnd().getHour();
    	return s>=start && e<= end;
    }
}