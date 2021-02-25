package tickets;

import java.util.Set;

public class TicketTypeSeasonalRestricted extends TicketTypeSeasonal{
	private Set<User.Occupation> validOccupation;
	
	TicketTypeSeasonalRestricted(
		String name, 
		double price,
		Set<Zone> 
		validZones, 
		Season validSeason, 
		Set<User.Occupation> validOccupation){
		super(name, price, validZones, validSeason);
		this.validOccupation = validOccupation;
	}
	
	@Override
	public boolean isValidFor(Trip trip, User user) {
		if(!validOccupation.contains(user.getOccupation())){
			return false;
		}
		return super.isValidFor(trip, user);
	}
}