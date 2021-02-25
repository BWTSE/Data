package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class BasicRoom implements Room {
    private final String name;
    private final String description;

    private final List<Booking> bookingList = new LinkedList<>();

    protected BasicRoom(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public Optional<Booking> book(Interval interval, User user) {
        if (isValidBooking(interval, user)) {
            Booking booking = new Booking(interval, user);
            bookingList.add(booking);

            return Optional.of(booking);
        }
        return Optional.empty();
    }

    protected abstract boolean isValidBooking(Interval interval, User user);

    boolean isAvailableDuring(Interval interval) {
        for (Booking booking : bookingList) {
            if (booking.getInterval().overlapsWith(interval)) {
                return false;
            }
        }
        return true;
    }

    boolean userHasBookingAlready (User user) {
        for (Booking b : this.getBookings()) {
            if (b.getInterval().getEndTime().isAfter(LocalDateTime.now()) && b.getBooker().equals(user)) {
                return true;
            }
        }

        return false;
    }

    static boolean startBeforeEnd(Interval interval) {
        return !(interval.getEndTime().isBefore(interval.getStartTime()) || interval.getStartTime().isBefore(LocalDateTime.now()));
    }

    static boolean startAndEndAtHour(Interval interval) {
        LocalDateTime bookingStartTime = interval.getStartTime()
                .with(ChronoField.SECOND_OF_MINUTE, 0)
                .with(ChronoField.NANO_OF_SECOND, 0);

        LocalDateTime bookingEndTime = interval.getEndTime()
                .with(ChronoField.SECOND_OF_MINUTE, 0)
                .with(ChronoField.NANO_OF_SECOND, 0);

        return !(bookingEndTime.getMinute() != 0|| bookingEndTime.with(ChronoField.HOUR_OF_DAY, 0).isAfter(bookingStartTime));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<Booking> getBookings() {
        return bookingList;
    }
}
