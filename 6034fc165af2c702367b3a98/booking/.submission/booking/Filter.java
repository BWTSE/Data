package booking;

public abstract class Filter {
	
	protected final Room room;
	private Filter nextFilter;
	
	public Filter(Room room) {
		this.room = room;
	}
	
	public abstract boolean valid(Booking b);

	protected final boolean next(Booking b) {
		if(this.nextFilter == null) {
			return true;
		} else {
			return this.nextFilter.valid(b);
		}
	}
	
	public final Filter setNext(Filter nextFilter) {
		this.nextFilter = nextFilter;
		return this;
	}
	
}