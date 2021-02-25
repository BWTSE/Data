package tickets;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonalRestricted extends TicketTypeSeasonal {

    private final Set<User.Occupation> validOccupations;

    public TicketTypeSeasonalRestricted(String name, double price, Set<Zone> validZones, Season validSeason, Set<User.Occupation> validOccupations) {
        super(name, price, validZones, validSeason);
        this.validOccupations = EnumSet.copyOf(validOccupations);
    }

    /*
    Make sure that the trip is during the correct season.
     */
    @Override
    public boolean isValidFor(Trip trip, User user) {
        return super.isValidFor(trip, user)
                && this.getValidOccupations().contains(user.getOccupation());
    }

    public Set<User.Occupation> getValidOccupations() {
        return this.validOccupations;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
                && Objects.equals(this.getValidOccupations(), ((TicketTypeSeasonalRestricted) o).getValidOccupations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(), this.getValidOccupations()
        );
    }
}

