
package booking;

import java.util.Objects;
import java.util.Optional;

public class ComputerClassRoom extends ClassRoom {

    private final int opening;
    private final int closing;

    public ComputerClassRoom(String name, String description, boolean hasProjector, int opening, int closing) {
        super(name, description, hasProjector);
        this.opening = opening;
        this.closing = closing;
    }

    public int getOpening() {
        return this.opening;
    }

    public int getClosing() {
        return this.closing;
    }

    /*
    Only allows bookings that start on the hour. (Floors second and microsecond values).
    */
    @Override
    public Optional<Booking> book(Interval interval, User customer) {
        if (interval.getStart().getHour() < this.getOpening() || interval.getStart().getHour() > this.getClosing()) {
        	return Optional.empty();
        }

        return super.book(interval, customer);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
            && Objects.equals(this.getOpening(), ((ComputerClassRoom) o).getOpening())
            && Objects.equals(this.getClosing(), ((ComputerClassRoom) o).getClosing());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getOpening(), this.getClosing());
    }
}
