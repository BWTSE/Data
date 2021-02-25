package tickets;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class TicketFinder {

    private final Set<TicketType> ticketTypes;

    public TicketFinder(Collection<TicketType> ticketTypes) {
        this.ticketTypes = new HashSet<>(ticketTypes);
    }

    public Set<TicketType> find(User user, Trip trip, double price) {
        Set<TicketType> foundTicketTypes = find(user, trip);

        for (Iterator<TicketType> it = foundTicketTypes.iterator(); it.hasNext();) {
            TicketType foundTicketType = it.next();
            if (foundTicketType.getPrice() >= price) {
                it.remove();
            }
        }

        return foundTicketTypes;
    }

    public Set<TicketType> find(User user, Trip trip) {
        Set<TicketType> foundTicketTypes = new HashSet<>();

        for (TicketType TicketType : ticketTypes) {
            if (TicketType.isValidFor(trip, user)) {
                foundTicketTypes.add(TicketType);
            }
        }

        return foundTicketTypes;
    }

    public Optional<Ticket> purchaseTicket(User user, TicketType ticketType, Trip trip) {
        if(find(user, trip).contains(ticketType)) {
            return Optional.of(new Ticket(ticketType, user));
        } else {
            return Optional.empty();
        }
    }

    public Set<TicketType> getticketTypes() {
        return new HashSet<>(this.ticketTypes);
    }
}
