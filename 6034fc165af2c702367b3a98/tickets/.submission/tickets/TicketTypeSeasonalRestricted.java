package tickets;

import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonalRestricted extends TicketTypeSeasonal {
	
	private final Set<User.Occupation> occupations;
	
	public TicketTypeSeasonalRestricted(String name, double price, Set<Zone> validZones, Season validSeason, Set<User.Occupation> occupations) {
		super(name, price, validZones, validSeason);
		this.occupations = occupations;
	}
	
	@Override
    public boolean isValidFor(Trip trip, User user) {
        return super.isValidFor(trip, user) && this.occupations.contains(user.getOccupation());
    }
	
}