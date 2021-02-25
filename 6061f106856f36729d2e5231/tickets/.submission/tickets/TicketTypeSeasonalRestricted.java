package tickets;

import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonalRestricted extends TicketTypeSeasonal
{
	private final Set<User.Occupation> o;
	
	public TicketTypeSeasonalRestricted(String n, double p, Set<Zone> z, Season vs, Set<User.Occupation> o) {
		super(n, p, z, vs);
		this.o = o;
	}
	
	/*
	Make sure the ticket is valid for the user occupation.
	*/
	@Override
	public boolean isValidFor(Trip t, User u) {
		if (!o.contains(u.getOccupation()))
			return false;
		
		return super.isValidFor(t, u);
	}
	
    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getPrice(), this.getValidSeason(), this.o);
    }
}