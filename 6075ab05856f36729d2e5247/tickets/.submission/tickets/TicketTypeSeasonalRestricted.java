
package tickets;

import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonalRestricted extends TicketTypeSeasonal {
	
	Set<User.Occupation> validUsers;
	
	public TicketTypeSeasonalRestricted(String name, double price, Set<Zone> validZones, Season validSeason, Set<User.Occupation> validUsers){
		//TicketTypeSeasonal(String name, double price, Set<Zone> validZones, Season validSeason)
		super(name, price, validZones,validSeason );
		this.validUsers = validUsers;
	}
	
	  @Override
    public boolean isValidFor(Trip trip, User user) {
        return (super.isValidFor(trip, user)
            && validUsers.contains(user.getOccupation())
            );
    }
	
}