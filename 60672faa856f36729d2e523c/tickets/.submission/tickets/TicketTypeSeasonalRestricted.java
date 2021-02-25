package tickets;

import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonalRestricted extends TicketTypeSeasonal {
	
	private Set<User.Occupation> validOccupations;
	
	TicketTypeSeasonalRestricted(String name, double price, Set<Zone> validZones, Season validSeason, Set<User.Occupation> validOccupations){
		super(name, price, validZones, validSeason);
		this.validOccupations = validOccupations;
	}
	
	@Override
    public boolean isValidFor(Trip trip, User user) {
        return super.isValidFor(trip, user)
            && this.getValidOccupation(user);
    }

    public boolean getValidOccupation(User user) {
    	return validOccupations.contains(user.getOccupation());
    }
}