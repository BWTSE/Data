
package tickets;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonalRestricted extends TicketTypeSeasonal {
	
	private final Set<User.Occupation> validOccupations;

    public TicketTypeSeasonalRestricted(String name, double price, Set<Zone> zones, Season validSeason, Set<User.Occupation> validOccupations) {
    	super(name,price,zones,validSeason);
    	this.validOccupations = validOccupations;
    }
    
    public Set<User.Occupation> getValidOccupations() {
    	return this.validOccupations;
    }

    @Override
    public boolean isValidFor(Trip t, User u) {
        return this.validOccupations.contains(u.getOccupation())
        && super.isValidFor(t,u);
    }

    @Override
    public boolean equals(Object o) {
        return this.validOccupations.equals(((TicketTypeSeasonalRestricted) o).getValidOccupations()) 
        && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), validOccupations);
    }
}
