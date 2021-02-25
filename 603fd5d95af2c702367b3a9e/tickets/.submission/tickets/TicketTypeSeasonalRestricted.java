package tickets;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonalRestricted extends TicketTypeSeasonal {
	
	private final Set<User.Occupation> vo;
	public TicketTypeSeasonalRestricted(String n, double p, Set<Zone> zs, Season vs, Set<User.Occupation> vo){
				super(n, p, zs, vs);
				this.vo = vo;
	}
	
	 @Override
    public boolean isValidFor(Trip t, User u) {
    	return super.isValidFor(t, u) && this.getValidOccupations().contains(u.getOccupation());
    }
	
	public Set<User.Occupation> getValidOccupations() {
        return this.vo;
    }
	
}
