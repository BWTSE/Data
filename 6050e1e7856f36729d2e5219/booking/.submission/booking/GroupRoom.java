package booking;

import java.util.Objects;

public class GroupRoom extends BasicRoom {

    private final boolean hasWhiteboard;

    protected GroupRoom(String name, String description, boolean hasWhiteboard) {
        super(name, description);
        this.hasWhiteboard = hasWhiteboard;
    }

    public boolean hasWhiteboard() {
        return this.hasWhiteboard;
    }

    /*
    Due to the popularity of some specific group rooms,
     no user is allowed to have more than one upcoming booking for a group room.
    Also makes sure that the booking is valid and that the room is available.
     */
    @Override
    protected boolean isValidBooking(Interval interval, User user) {
        return !this.userHasBookingAlready(user) && this.isAvailableDuring(interval) && startBeforeEnd(interval);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
                && Objects.equals(this.hasWhiteboard(), ((GroupRoom) o).hasWhiteboard());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.hasWhiteboard());
    }

    @Override
    public String toString() {
        return String.format(
                "Room %s \"%s\" #bookings: %s",
                this.getName(),
                this.getDescription(),
                this.getBookings().size()
        );
    }
}
