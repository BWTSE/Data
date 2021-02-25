
package tickets;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonalRestricted implements TicketType {

    private final String name;
    private final double price;
    private final Set<Zone> zones;
    private final Season season;
    private final Set<User.Occupation> occupations;

    public TicketTypeSeasonalRestricted(
    	String name, 
    	double price, 
    	Set<Zone> zones, 
    	Season season, 
    	Set<User.Occupation> occupations
	) {
        this.name = name;
        this.price = price;
        this.zones = EnumSet.copyOf(zones);
        this.season = season;
        this.occupations = EnumSet.copyOf(occupations);
    }

    /*
    Make sure that the trip is during the correct season.
    Also checks that the ticket is valid in the zones of the trip
    and that the user has the right occupation.
     */
    @Override
    public boolean isValidFor(Trip t, User user) {
        return this.getValidZones().contains(t.getStartZone())
            && this.getValidZones().contains(t.getEndZone())
            && this.getValidSeason().isDateWithin(t.getTripStartTime())
            && this.getValidOccupations().contains(user.getOccupation());
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public Set<Zone> getValidZones() {
        return EnumSet.copyOf(this.zones);
    }
    
    public Season getValidSeason() {
        return this.season;
    }
    
    public Set<User.Occupation> getValidOccupations() {
    	return EnumSet.copyOf(this.occupations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        TicketTypeSeasonalRestricted tt = (TicketTypeSeasonalRestricted) o;
        return Objects.equals(this.getName(), tt.getName())
            && Objects.equals(this.getValidSeason(), tt.getValidSeason())
            && Objects.equals(this.getValidZones(), tt.getValidZones())
            && Objects.equals(this.getPrice(), tt.getPrice())
            && Objects.equals(this.getValidOccupations(), tt.getValidOccupations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        	this.getName(), 
        	this.getPrice(), 
        	this.getValidSeason(), 
        	this.getValidOccupations()
    	);
    }

    @Override
    public String toString() {
        return String.format("Ticket %s ", this.getName());
    }
}
