package booking;

public class ComputerClassRoom extends ClassRoom {

    private final int openingHour;
    private final int closingHour;

    public ComputerClassRoom(String name, String description, boolean hasProjector, int openingHour, int closingHour) {
        super(name, description, hasProjector);
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    /*
    Same restrictions as ClassRoom, but can only be booked between the opening and closing hours.
     */
    @Override
    protected boolean isValidBooking(Interval interval, User user) {
        return super.isValidBooking(interval, user)
                && interval.getStartTime().getHour() >= openingHour
                && interval.getEndTime().getHour() <= closingHour;
    }
}
