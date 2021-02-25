package booking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Interval {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Interval(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public boolean isDuring(LocalDateTime time) {
        return !time.isBefore(this.getStartTime()) && !time.isAfter(this.getEndTime());
    }

    public boolean overlapsWith(Interval other) {
        return this.isDuring(other.getStartTime())
                || this.isDuring(other.getEndTime())
                || other.isDuring(this.getStartTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Interval i = (Interval) o;
        return Objects.equals(this.getStartTime(), i.getStartTime()) &&
                Objects.equals(this.getEndTime(), i.getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartTime(), getEndTime());
    }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        return String.format(
                "%s to %s",
                f.format(this.getStartTime()),
                f.format(this.getEndTime())
        );
    }
}
