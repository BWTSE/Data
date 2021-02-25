package tickets;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class TicketTypeSingle implements TicketType {

    private final String name;
    private final double price;
    private final Set<Zone> validZones;
    private final int startHour;
    private final int endHour;

    public TicketTypeSingle(String name, double price, Set<Zone> validZones,
    int startHour, int endHour) {
        this.name = name;
        this.price = price;
        this.validZones = EnumSet.copyOf(validZones);
        this.startHour = startHour;
        this.endHour = endHour;
    }

    /*
    Makes sure that the trip is during the right time of the day.
    Also checks that the ticket is valid in the zones of the trip.
     */
    @Override
    public boolean isValidFor(Trip t, User u) {
        if (this.startHour < this.endHour) {
            return
                t.getTripStartTime().getHour() >= this.startHour 
                && t.getTripStartTime().getHour() < this.endHour
                && this.getValidZones().contains(t.getStartZone()) 
                && this.getValidZones().contains(t.getEndZone());
        } else {
            return (t.getTripStartTime().getHour() >= this.startHour
                    || t.getTripStartTime().getHour() > this.endHour) 
                && this.getValidZones().contains(t.getStartZone()) 
                && this.getValidZones().contains(t.getEndZone());
        }
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public Set<Zone> getValidZones() {
        return EnumSet.copyOf(this.validZones);
    }

    public int getStartHour() {
        return this.startHour;
    }

    public int getEndHour() {
        return this.endHour;
    } 

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        TicketTypeSingle tt = (TicketTypeSingle) o;
        return Objects.equals(this.getName(), tt.getName())
            && Objects.equals(this.getValidZones(), tt.getValidZones())
            && Objects.equals(this.getStartHour(), tt.getStartHour())
            && Objects.equals(this.getEndHour(), tt.getEndHour())
            && Objects.equals(this.getPrice(), tt.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.getName(),
            this.getPrice(),
            this.getStartHour(),
            this.getEndHour()
        );
    }

    @Override
    public String toString() {
        return String.format("Ticket %s ", this.getName());
    }
}
