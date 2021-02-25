
package tickets;

import java.util.EnumSet;
import java.util.Set;

public class ZoneRestriction implements Restriction {
	
	private Set<Zone> validZones;
	
	public ZoneRestriction(Set<Zone> validZones) {
		this.validZones = EnumSet.copyOf(validZones);
	}
	
	public boolean isValidFor(Trip trip, User user) {
		return validZones.contains(trip.getStartZone())
            && validZones.contains(trip.getEndZone());
	}
}