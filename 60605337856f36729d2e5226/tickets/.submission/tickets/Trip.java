package tickets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Trip {

    private final Zone startingZone;
    private final Zone endingZone;
    private final LocalDateTime tripStartTime;

    public Trip(Zone startingZone, Zone endingZone, LocalDateTime tripStartTime) {
        this.startingZone = startingZone;
        this.endingZone = endingZone;
        this.tripStartTime = tripStartTime;
    }

    public Zone getStartZone() {
        return this.startingZone;
    }

    public Zone getEndZone() {
        return this.endingZone;
    }
    
    public LocalDateTime getTripStartTime() {
        return this.tripStartTime;
    }

    @Override 
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Trip trip = (Trip) o;
        return Objects.equals(this.getStartZone(), trip.getStartZone())
            && Objects.equals(this.getEndZone(), trip.getEndZone())
            && Objects.equals(this.getTripStartTime(), trip.getTripStartTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getStartZone(), this.getEndZone(), this.getTripStartTime());
    }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        return String.format(
            "Trip from %s to %s starting at %s", 
            this.getStartZone(), 
            this.getEndZone(),
            f.format(this.getTripStartTime())
        );
    }
}
