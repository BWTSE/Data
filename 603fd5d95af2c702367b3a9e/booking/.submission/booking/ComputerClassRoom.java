package booking;

import java.time.LocalDateTime;
import java.util.Objects;

public class ComputerClassRoom extends ClassRoom {
	private final int openingHour;
	private final int closingHour;
	
	public ComputerClassRoom(String name, String description, boolean hasProjector, int openingHour, int closingHour){
		super(name, description, hasProjector);
		this.openingHour = openingHour;
		this.closingHour = closingHour;
	}
	
	@Override
	public boolean bookingIsNotValid(LocalDateTime bookingStart, LocalDateTime bookingEnd){
		return super.bookingIsNotValid(bookingStart, bookingEnd) ||  this.getOpeningHour() > bookingStart.getHour() || this.getClosingHour() < bookingEnd.getHour();
	}
	
	
	public int getOpeningHour(){
		return this.openingHour;
	}
	
	public int getClosingHour(){
		return this.closingHour;
	}
	
	@Override
    public boolean equals(Object o) {
        return super.equals(o) && this.getOpeningHour() == ((ComputerClassRoom)o).getOpeningHour()  && this.getClosingHour() == ((ComputerClassRoom)o).getClosingHour();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getOpeningHour(), this.getClosingHour());
    }
}
