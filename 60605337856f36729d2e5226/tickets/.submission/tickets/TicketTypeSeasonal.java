package tickets;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonal implements TicketType {

    private final String name;
    private final double price;
    private final Set<Zone> validZones;
    private final Season validSeason;
    private final Set<User.Occupation> validOccupations;

    public TicketTypeSeasonal(String name, double price, Set<Zone> validZones, 
    Season validSeason) {
        this.name = name;
        this.price = price;
        this.validZones = EnumSet.copyOf(validZones);
        this.validSeason = validSeason;
        this.validOccupations = EnumSet.allOf(User.Occupation.class);
    }
    
    public TicketTypeSeasonal(String name, double price, Set<Zone> validZones, 
    Season validSeason, Set<User.Occupation> validOccupations) {
        this.name = name;
        this.price = price;
        this.validZones = EnumSet.copyOf(validZones);
        this.validSeason = validSeason;
        this.validOccupations = EnumSet.copyOf(validOccupations);
    }    

    /*
    Make sure that the trip is during the correct season.
    Also checks that the ticket is valid in the zones of the trip and for the occupation of the user.
     */
    @Override
    public boolean isValidFor(Trip t, User u) {
        return this.getValidZones().contains(t.getStartZone())
            && this.getValidZones().contains(t.getEndZone())
            && this.getValidSeason().isDateWithin(t.getTripStartTime())
            && this.getValidOccupations().contains(u.getOccupation());
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
    
    public Season getValidSeason() {
        return this.validSeason;
    }
    
    public Set<User.Occupation> getValidOccupations() {
        return EnumSet.copyOf(this.validOccupations);
    }    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        TicketTypeSeasonal tt = (TicketTypeSeasonal) o;
        return Objects.equals(this.getName(), tt.getName())
            && Objects.equals(this.getValidSeason(), tt.getValidSeason())
            && Objects.equals(this.getValidZones(), tt.getValidZones())
            && Objects.equals(this.getPrice(), tt.getPrice())
            && Objects.equals(this.getValidOccupations(), tt.getValidOccupations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getPrice(), this.getValidSeason() 
        , this.getValidOccupations());
    }

    @Override
    public String toString() {
        return String.format("Ticket %s ", this.getName());
    }
}
