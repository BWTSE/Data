package tickets;

import java.util.Set;

public class TicketTypeSeasonalRestricted extends TicketTypeSeasonal {
	private final Set<User.Occupation> validOccupations;

	TicketTypeSeasonalRestricted(String name, double price, Set<Zone> validZones, Season validSeason, Set<User.Occupation> validOccupations) {
		super(name, price, validZones, validSeason);
		this.validOccupations = validOccupations;
	}
	
	public Set<User.Occupation> getValidOccupations() {
		return this.validOccupations;
	}
	
	@Override
    public boolean isValidFor(Trip trip, User user) {
        return super.isValidFor(trip, user)
            && this.getValidOccupations().contains(user.getOccupation());
    }
}
