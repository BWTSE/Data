package booking;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final LocalDateTime n = LocalDateTime.now().plusMinutes(5);

    private static final User u1 = new User("Alice", "alice@crypto");
    private static final User u2 = new User("Bob", "bob@crypto");

    private static final ClassRoom r1 = new ClassRoom("HB2", " at Hörsalsvägen 2", true);
    private static final ComputerClassRoom r2 = new ComputerClassRoom("J029", "in Jupiter", false, 7, 17);
    private static final GroupRoom r3 = new GroupRoom("J317", "in Jupiter", true);

    private static final Collection<Room> rs = List.of(
        r1,
        r2,
        r3
    );

    // Runs some simple tests for the booking system
    public static void main(String[] args) {

        // Tests basic booking functionality
        for (Room r : rs) {
            LocalDateTime t1 = LocalDateTime.of(2021, 6, 1, 12, 0, 0);
            Interval i1 = new Interval(t1, t1.plusHours(3));
            LocalDateTime t2 = t1.plusDays(1);
            Interval i2 = new Interval(t2, t2.plusHours(2));

            boolean b1 = r.book(new Booking(i1, u1));
            if (!b1) {
                System.out.println("Rooms should be bookable: " + r.toString());
            }

            boolean b2 = r.book(new Booking(i1, u2));
            if (!b2) {
                System.out.println("Rooms should not be double booked: " + r.toString());
            }

            boolean b3 = r.book(new Booking(i2, u2));
            if (!b3) {
                System.out.println(
                    "Rooms should be bookable after previous booking: "
                    + r.toString()
                );
            }
        }

        // Test ComputerRoom booking
        LocalDateTime t = LocalDateTime.of(2021, 6, 1, 5, 0, 0);
        boolean b = r2.book(new Booking(new Interval(t, t.plusHours(3)), u2));
        if (b) {
            System.out.println("A computer room was successfully booked outside of allowed hours, this should not be possible");
        }

        t = LocalDateTime.of(2021, 6, 8, 12, 0, 0);
        b = r2.book(new Booking(new Interval(t, t.plusHours(3)), u2));
        if (!b) {
            System.out.println("Failed to book computer room during allowed hours, this should be possible");
        }

        // Test Class Room booking
        Booking booking = new Booking(new Interval(t, t.plusHours(1)), u2);
        b = r1.book(booking);
       
        if (b && booking.getInterval().getStart().getMinute() != 0) {
            System.out.println("Class room bookings should always start at whole hours.");
        }

		booking = new Booking(new Interval(t, t.plusDays(1)), u2);
        b = r1.book(booking);
        if (!b) {
            System.out.println("Class room bookings not stretch over two days.");
        }

        // Test GroupRoom booking
        // Previous booking is made in the loop above
        boolean groupRoomBooking = r3.book(
        	new Booking(
        	new Interval(n.plusDays(20), n.plusDays(25)),
            u1
        	)
            );
        if (!groupRoomBooking) {
            System.out.println("Every user should only be able to have one upcoming groupRoom booking");
        }

        System.out.println("If there were no previous output all tests passed");
    }
}
