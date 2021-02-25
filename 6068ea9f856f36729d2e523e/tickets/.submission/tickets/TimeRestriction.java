
package tickets;

public class TimeRestriction implements Restriction {
	
	private final int startHour;
    private final int endHour;
    
    public TimeRestriction(int startHour, int endHour) {
    	this.startHour = startHour;
    	this.endHour = endHour;
    }
    
    public boolean isValidFor(Trip trip, User user) {
    	if (this.startHour < this.endHour) {
            return
                trip.getTripStartTime().getHour() >= this.startHour 
                && trip.getTripStartTime().getHour() < this.endHour;
        } else {
            return trip.getTripStartTime().getHour() >= this.startHour
                    || trip.getTripStartTime().getHour() < this.endHour;
        }
    }
}