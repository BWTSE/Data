package tickets;

import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonalRestricted extends TicketTypeSeasonal {
	Set<User.Occupation> validOccupations;
	public TicketTypeSeasonalRestricted(String name, double price, Set<Zone> validZones, Season validSeason, Set<User.Occupation> validOccupations){
		super(name, price, validZones, validSeason);
		this.validOccupations = validOccupations;
	}
	    @Override
    public boolean isValidFor(Trip trip, User user) {
        return super.isValidFor(trip, user)
            && validOccupations.contains(user.getOccupation());
    }
}