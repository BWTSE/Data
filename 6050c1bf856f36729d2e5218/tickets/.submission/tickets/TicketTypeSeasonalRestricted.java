package tickets;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonalRestricted implements TicketType {

    private final TicketTypeSeasonal inner;
    private final Set<User.Occupation> vo;

    public TicketTypeSeasonalRestricted(
    	TicketTypeSeasonal inner,
    	Set<User.Occupation> vo
    ) {
        this.inner = inner;
        this.vo = vo;
    }

	public TicketTypeSeasonalRestricted(
		String n,
		double p,
		Set<Zone> zs,
		Season vs,
		Set<User.Occupation> vo
	) {
        this(
        	new TicketTypeSeasonal(n, p, zs, vs),
        	vo
        );
    }

    /*
    Make sure that the trip is during the correct season.
    Also checks that the ticket is valid in the zones of the trip.
     */
    @Override
    public boolean isValidFor(Trip t, User u) {
        return this.inner.isValidFor(t, u)
            && this.getValidOccupations().contains(u.getOccupation());
    }

    @Override
    public String getName() {
        return this.inner.getName();
    }

    @Override
    public double getPrice() {
        return this.inner.getPrice();
    }

    @Override
    public Set<Zone> getValidZones() {
        return this.inner.getValidZones();
    }
    
    public Season getValidSeason() {
        return this.inner.getValidSeason();
    }
    
    public Set<User.Occupation> getValidOccupations() {
        return EnumSet.copyOf(this.vo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        TicketTypeSeasonalRestricted tt = (TicketTypeSeasonalRestricted) o;
        return this.inner.equals(tt.inner)
            && Objects.equals(this.getPrice(), tt.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.inner.hashCode(), this.getValidOccupations());
    }

    @Override
    public String toString() {
        return this.inner.toString();
    }
}
