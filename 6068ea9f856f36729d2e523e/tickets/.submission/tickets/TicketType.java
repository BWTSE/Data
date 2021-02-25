package tickets;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class TicketType {
    private final String name;
    private final double price;
    private final Restriction[] restrictions;

    protected TicketType(String name, double price, Restriction...restrictions) {
        this.name = name;
        this.price = price;
        this.restrictions = restrictions;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    /*
    Checks that the ticket is valid in the zones of the trip.
     */
    public boolean isValidFor(Trip trip, User user) {
        for(Restriction restriction : restrictions) {
        	if (!restriction.isValidFor(trip, user)) {
        		return false;
        	}
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        TicketType ticketType = (TicketType) o;
        return Objects.equals(this.getName(), ticketType.getName())
            && Objects.equals(this.getPrice(), ticketType.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getPrice());
    }

    @Override
    public String toString() {
        return String.format("Ticket %s ", this.getName());
    }
}
