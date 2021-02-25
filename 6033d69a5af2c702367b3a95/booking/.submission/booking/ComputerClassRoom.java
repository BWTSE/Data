
package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Objects;
import java.util.Optional;

public class ComputerClassRoom extends ClassRoom {

    private final int hourOpen;
    private final int hourClose;

    public ComputerClassRoom(String name, String description, boolean hasProjector, int hourOpen, int hourClose) {
        super(name, description, hasProjector);
        this.hourOpen = hourOpen;
        this.hourClose = hourClose;
    }
    
    public int getHourOpen() {
        return this.hourOpen;
    }
    
    public int getHourClose() {
        return this.hourClose;
    }

    /*
    Only allows for bookings during open hours.
    */
    @Override
    public Optional<Booking> book(Interval interval, User customer) {
        LocalDateTime bookingStart = interval.getStart()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        LocalDateTime bookingEnd = interval.getStart()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        if (
            bookingStart.getHour() < hourOpen
            || bookingEnd.getHour() > hourClose
        ) {
            return Optional.empty();
        }

        return super.book(new Interval(bookingStart, bookingEnd), customer);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
            && Objects.equals(this.hourOpen, ((ComputerClassRoom) o).getHourOpen())
            && Objects.equals(this.hourClose, ((ComputerClassRoom) o).getHourClose());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.hasProjector(), hourOpen, hourClose);
    }
}
