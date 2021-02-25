package tickets;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonalRestricted extends TicketTypeSeasonal {

	private final Set<User.Occupation> occupationSet;
	
    public TicketTypeSeasonalRestricted(String n, double p, Set<Zone> zs, Season vs, Set<User.Occupation> occupationSet) {
    	super(n, p, zs, vs);
		this.occupationSet = EnumSet.copyOf(occupationSet);
    }
	
	@Override
    public boolean isValidFor(Trip t, User u) {
        return this.getValidZones().contains(t.getStartZone())
            && this.getValidZones().contains(t.getEndZone())
            && this.getValidSeason().isDateWithin(t.getTripStartTime())
            && this.getValidOccupation().contains(u.getOccupation());
    }
	
	public Set<User.Occupation> getValidOccupation() {
        return this.occupationSet;
    }

}
