package tickets;

import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonalRestricted extends TicketTypeSeasonal {

    private final Set<User.Occupation> validOccupations;

    public TicketTypeSeasonalRestricted(
    	String name, 
    	double price, 
    	Set<Zone> validZones, 
    	Season validSeason, 
    	Set<User.Occupation> validOccupations
	) {
        super(name, price, validZones, validSeason);
        this.validOccupations = validOccupations;
    }

    /*
    Make sure that the trip is for a user with the right occupation.
     */
    @Override
    public boolean isValidFor(Trip trip, User user) {
        return super.isValidFor(trip, user)
            && this.getvalidOccupations().contains(user.getOccupation());
    }

    public Set<User.Occupation> getvalidOccupations() {
        return this.validOccupations;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
            && Objects.equals(
            	this.getvalidOccupations(), 
            	((TicketTypeSeasonalRestricted) o).getvalidOccupations()
        	);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(), this.getvalidOccupations()
        );
    }
}
