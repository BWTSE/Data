package booking;

import java.util.Objects;

public class Booking {
    private final Interval interval;
    private final User user;

    public Booking(Interval interval, User user) {
        this.interval = interval;
        this.user = user;
    }

    public Interval getInterval() {
        return this.interval;
    }

    public User getBooker() {
        return this.user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Booking b = (Booking) o;
        return Objects.equals(this.getInterval(), b.getInterval()) &&
                Objects.equals(this.getBooker(), b.getBooker());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.getInterval(),
                this.getBooker()
        );
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] by (%s)",
                this.getInterval().toString(),
                this.getBooker().toString()
        );
    }
}
