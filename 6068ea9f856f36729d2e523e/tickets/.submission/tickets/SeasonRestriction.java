
package tickets;

public class SeasonRestriction implements Restriction {
	
	private final Season validSeason;
	
	public SeasonRestriction(Season validSeason) {
		this.validSeason = validSeason;
	}
	
	public boolean isValidFor(Trip trip, User user) {
		return validSeason.isDateWithin(trip.getTripStartTime());
	}
}