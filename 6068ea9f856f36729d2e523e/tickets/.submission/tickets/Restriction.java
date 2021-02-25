
package tickets;

public interface Restriction {
	
	boolean isValidFor(Trip trip, User user);
	
}