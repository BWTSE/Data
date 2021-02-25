
package tickets;

import java.util.EnumSet;
import java.util.Set;

public class OccupationRestriction implements Restriction {

	private final Set<User.Occupation> validOccupations;
	
	public OccupationRestriction(Set<User.Occupation> validOccupations) {
		this.validOccupations = EnumSet.copyOf(validOccupations);
	}
	
	public boolean isValidFor(Trip trip, User user) {
		return validOccupations.contains(user.getOccupation());
	}
	
}