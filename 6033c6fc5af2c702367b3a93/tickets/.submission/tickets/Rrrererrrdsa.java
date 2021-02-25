/*package tickets;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonalRestricted extends TicketTypeSeasonal {

    private final Set<User.Occupation> uo;
    private final TicketTypeSeasonal fuckJava;

    public TicketTypeSeasonalRestricted(String n, double p, Set<Zone> zs, Season vs, Set<User.Occupation> uo) {
    	this.fuckJava = new TicketTypeSeasonal(n, p, zs, vs);
        this.uo = uo;
    }
    

    public boolean isValidFor(Trip t, User u, User.Occupation uo) {
        return this.fuckJava.getValidZones().contains(t.getStartZone())
            && this.fuckJava.getValidZones().contains(t.getEndZone())
            && this.fuckJava.getValidSeason().isDateWithin(t.getTripStartTime())
            && this.validOccupation(uo);
    }
    
    public boolean validOccupation(User.Occupation userOccu){
        return EnumSet.copyOf(this.uo).contains(userOccu);
    }
    	
    
}*/

