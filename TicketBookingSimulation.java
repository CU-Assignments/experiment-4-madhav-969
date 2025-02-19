import java.util.*;
import java.util.concurrent.*;

class Seat {
    private int seatNumber;
    private boolean isBooked;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.isBooked = false;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void book() {
        isBooked = true;
    }
}

class TicketBookingSystem {
    private List<Seat> seats;

    public TicketBookingSystem(int totalSeats) {
        seats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            seats.add(new Seat(i));
        }
    }

    public synchronized boolean bookSeat(int seatNumber, String customerType) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber() == seatNumber && !seat.isBooked()) {
                seat.book();
                System.out.println(customerType + " booked seat " + seatNumber);
                return true;
            }
        }
        return false;
    }

    public void displayAvailableSeats() {
        System.out.println("Available seats:");
        for (Seat seat : seats) {
            if (!seat.isBooked()) {
                System.out.print(seat.getSeatNumber() + " ");
            }
        }
        System.out.println();
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem bookingSystem;
    private String customerType;
    private int numBookings;

    public BookingThread(TicketBookingSystem bookingSystem, String customerType, int numBookings, int priority) {
        this.bookingSystem = bookingSystem;
        this.customerType = customerType;
        this.numBookings = numBookings;
        setPriority(priority);
    }

    @Override
    public void run() {
        Random random = new Random();
        int successfulBookings = 0;

        while (successfulBookings < numBookings) {
            int seatNumber = random.nextInt(50) + 1; // Assuming 50 seats
            if (bookingSystem.bookSeat(seatNumber, customerType)) {
                successfulBookings++;
            }
            try {
                Thread.sleep(100); // Simulate booking process time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class TicketBookingSimulation {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem(50);

        BookingThread vipThread = new BookingThread(bookingSystem, "VIP", 10, Thread.MAX_PRIORITY);
        BookingThread regularThread = new BookingThread(bookingSystem, "Regular", 30, Thread.NORM_PRIORITY);

        vipThread.start();
        regularThread.start();

        try {
            vipThread.join();
            regularThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nFinal seat status:");
        bookingSystem.displayAvailableSeats();
    }
}
