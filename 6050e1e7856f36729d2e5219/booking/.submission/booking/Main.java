package booking;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final LocalDateTime now = LocalDateTime.now().plusMinutes(5);

    private static final User alice = new User("Alice", "alice@crypto");
    private static final User bob = new User("Bob", "bob@crypto");

    private static final ClassRoom classRoom = new ClassRoom("HB2", " at Hörsalsvägen 2", true);
    private static final ComputerClassRoom computerClassRoom = new ComputerClassRoom("J029", "in Jupiter", false, 7, 17);
    private static final GroupRoom groupRoom = new GroupRoom("J317", "in Jupiter", true);

    private static final Collection<Room> rooms = List.of(
            classRoom,
            computerClassRoom,
            groupRoom
    );

    // Runs some simple tests for the booking system
    public static void main(String[] args) {

        // Tests basic booking functionality
        for (Room room : rooms) {
            LocalDateTime time1 = LocalDateTime.of(2021, 6, 1, 12, 0, 0);
            Interval interval1 = new Interval(time1, time1.plusHours(3));
            LocalDateTime time2 = time1.plusDays(1);
            Interval interval2 = new Interval(time2, time2.plusHours(2));

            Optional<Booking> booking = room.book(interval1, alice);
            if (booking.isEmpty()) {
                System.out.println("Rooms should be bookable: " + room.toString());
            }

            booking = room.book(interval1, bob);
            if (booking.isPresent()) {
                System.out.println("Rooms should not be double booked: " + room.toString());
            }

            booking = room.book(interval2, bob);
            if (booking.isEmpty()) {
                System.out.println(
                        "Rooms should be bookable after previous booking: "
                                + room.toString()
                );
            }
        }

        // Test ComputerRoom booking
        LocalDateTime time = LocalDateTime.of(2021, 6, 1, 5, 0, 0);
        Optional<Booking> booking = computerClassRoom.book(new Interval(time, time.plusHours(3)), bob);
        if (booking.isPresent()) {
            System.out.println("A computer room was successfully booked outside of allowed hours, this should not be possible");
        }

        time = LocalDateTime.of(2021, 6, 8, 12, 0, 0);
        booking = computerClassRoom.book(new Interval(time, time.plusHours(3)), bob);
        if (!booking.isPresent()) {
            System.out.println("Failed to book computer room during allowed hours, this should be possible");
        }

        // Test Class Room booking
        booking = classRoom.book(
                new Interval(time, time.plusHours(1)),
                bob
        );
        if (booking.isPresent() && booking.get().getInterval().getStartTime().getMinute() != 0) {
            System.out.println("Class room bookings should always start at whole hours.");
        }

        booking = classRoom.book(
                new Interval(time, time.plusDays(1)),
                bob
        );
        if (booking.isPresent()) {
            System.out.println("Class room bookings not stretch over two days.");
        }

        // Test GroupRoom booking
        // Previous booking is made in the loop above
        Optional<Booking> groupRoomBooking = groupRoom.book(
                new Interval(now.plusDays(20), now.plusDays(25)),
                alice
        );
        if (groupRoomBooking.isPresent()) {
            System.out.println("Every user should only be able to have one upcoming groupRoom booking");
        }

        System.out.println("If there were no previous output all tests passed");
    }
}
