package tickets;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class Main {
    
    private static final User alice = new User("Alice", "alice@crypto", 32, User.Occupation.ADULT);
    private static final User bob = new User("Bob", "bob@crypto", 75, User.Occupation.RETIREE);
    private static final User carl = new User("Carl", "carl@crypto", 21, User.Occupation.STUDENT);
    
    private static Set<Zone> centrumOnly = EnumSet.of(Zone.CENTRAL);
    private static Set<Zone> centrumPlus = EnumSet.of(Zone.CENTRAL, Zone.SUBURB);
    private static Set<Zone> allZones = EnumSet.of(Zone.CENTRAL, Zone.SUBURB, Zone.RURAL);
    
    private static final TicketType centralSingleDay =
        new TicketType("Single Central Day", 29, new ZoneRestriction(centrumOnly), new TimeRestriction(5, 22));
    private static final TicketType centralWinter =
        new TicketType("Central Winter Ticket", 149, new ZoneRestriction(centrumOnly), new SeasonRestriction(Season.WINTER));
    private static final TicketType centralSummer =
        new TicketType("Central Summer Ticket", 749, new ZoneRestriction(centrumOnly), new SeasonRestriction(Season.SUMMER));
    private static final TicketType centrumPlusSingle =
        new TicketType("Single Central + Suburbs Day", 49, new ZoneRestriction(centrumPlus), new TimeRestriction(4, 0));
    private static final TicketType retireeWinter =
        new TicketType("Retiree Winter Ticket", 199.0, new ZoneRestriction(centrumPlus), new SeasonRestriction(Season.WINTER), 
        new OccupationRestriction(EnumSet.of(User.Occupation.RETIREE)));
    private static final TicketType allSingleDay =
        new TicketType("Single All Regions Day", 69, new ZoneRestriction(allZones), new TimeRestriction(5, 22));
    private static final TicketType studentWinter =
        new TicketType( "Student Winter Ticket", 199.0, new ZoneRestriction(allZones), new SeasonRestriction(Season.WINTER),  
        new OccupationRestriction(EnumSet.of(User.Occupation.STUDENT)));

    public static void main(String[] args) {

        final Set<TicketType> ticketTypeList = new HashSet<>();

        ticketTypeList.add(centralSingleDay);
        ticketTypeList.add(centralWinter);
        ticketTypeList.add(centralSummer);
        ticketTypeList.add(centrumPlusSingle);
        ticketTypeList.add(allSingleDay);
        ticketTypeList.add(retireeWinter);
        ticketTypeList.add(studentWinter);


        TicketFinder ticketFinder = new TicketFinder(ticketTypeList);

        // Tests
        
        LocalDateTime time = LocalDateTime.of(2021, 12, 15, 5, 0);
        
        ZoneRestriction centrumRestriction = new ZoneRestriction(centrumOnly);
        if (centrumRestriction.isValidFor(new Trip(Zone.CENTRAL, Zone.SUBURB, time), alice) 
        && !centrumRestriction.isValidFor(new Trip(Zone.CENTRAL, Zone.CENTRAL, time), alice)) {
        	System.out.println("ZoneRestriction doesn't work");
        }
        
        SeasonRestriction summerRestriction = new SeasonRestriction(Season.SUMMER);
        if (summerRestriction.isValidFor(new Trip(Zone.CENTRAL, Zone.SUBURB, LocalDateTime.of(2021, 12, 15, 5, 0)), alice)
        && !summerRestriction.isValidFor(new Trip(Zone.CENTRAL, Zone.SUBURB, LocalDateTime.of(2021, 6, 15, 5, 0)), alice)) {
        	System.out.println("SeasonRestriction doesn't work");
        }
        
        TimeRestriction dayRestriction = new TimeRestriction(6, 18);
        if (dayRestriction.isValidFor(new Trip(Zone.CENTRAL, Zone.SUBURB, LocalDateTime.of(2021, 12, 15, 5, 0)), alice)
        && !dayRestriction.isValidFor(new Trip(Zone.CENTRAL, Zone.SUBURB, LocalDateTime.of(2021, 12, 15, 14, 0)), alice)) {
        	System.out.println("Day TimeRestriction doesn't work");
        }
        
        TimeRestriction nighRestriction = new TimeRestriction(18, 6);
        if (nighRestriction.isValidFor(new Trip(Zone.CENTRAL, Zone.SUBURB, LocalDateTime.of(2021, 12, 15, 14, 0)), alice)
        && !nighRestriction.isValidFor(new Trip(Zone.CENTRAL, Zone.SUBURB, LocalDateTime.of(2021, 12, 15, 3, 0)), alice)) {
        	System.out.println("Night TimeRestriction doesn't work");
        }
        
        OccupationRestriction studentRestriction = new OccupationRestriction(EnumSet.of(User.Occupation.STUDENT));
        if (studentRestriction.isValidFor(new Trip(Zone.CENTRAL, Zone.SUBURB, time), alice) 
        && !studentRestriction.isValidFor(new Trip(Zone.CENTRAL, Zone.CENTRAL, time), carl)) {
        	System.out.println("ZoneRestriction doesn't work");
        }
        

        Set<TicketType> aliceTickets =
            ticketFinder.find(alice, new Trip(Zone.CENTRAL, Zone.SUBURB, time), 2000);

        if (aliceTickets.containsAll(new HashSet<>(Arrays.asList(studentWinter, retireeWinter)))) {
            System.out.println("Alice (adult) found a retiree or student ticket");
        }
    
        Set<TicketType> bobTickets =
            ticketFinder.find(bob, new Trip(Zone.CENTRAL, Zone.CENTRAL, time), 2000);
        if (bobTickets.contains(studentWinter)) {
            System.out.println("Bob (retiree) found the student ticket.");
        }
        if (!bobTickets.contains(retireeWinter)) {
            System.out.println("Bob (retiree) didn't find the retiree ticket.");
        }

        Set<TicketType> carlTickets =
            ticketFinder.find(carl, new Trip(Zone.CENTRAL, Zone.CENTRAL, time), 2000);
        if (carlTickets.contains(retireeWinter)) {
            System.out.println("Carl (student) found the retiree ticket.");
        }
        if (!carlTickets.contains(studentWinter)) {
            System.out.println("Carl (student) didn't find the student ticket.");
        }


        System.out.println("If there were no previous output all tests passed");
    }
}
