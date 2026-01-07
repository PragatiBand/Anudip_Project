import java.sql.*;
import java.util.Scanner;

public class MakeMyTripSystem {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mmt";
        String user = "root";
        String pass = "M@nsi120";

        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("✨ Connected to MakeMyTrip Database");

            while (true) {
                System.out.println("\n=== MAKE MY TRIP MAIN MENU ===");
                System.out.println("1. Flight Booking");
                System.out.println("2. Hotel Booking");
                System.out.println("3. Bus Booking");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> flightMenu(con, sc);
                    case 2 -> hotelMenu(con, sc);
                    case 3 -> busMenu(con, sc);
                    case 4 -> {
                        System.out.println("Thank you for using MakeMyTrip 😊");
                        con.close();
                        sc.close();
                        System.exit(0);
                    }
                    default -> System.out.println("❌ Invalid choice!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================================================
    // FLIGHT BOOKING MENU
    // ================================================
    public static void flightMenu(Connection con, Scanner sc) throws Exception {
        while (true) {
            System.out.println("\n--- FLIGHT BOOKING MENU ---");
            System.out.println("1. Add Flight Booking");
            System.out.println("2. View Flight Bookings");
            System.out.println("3. Update Booking");
            System.out.println("4. Delete Booking");
            System.out.println("5. Search Booking");
            System.out.println("6. Back");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> addFlight(con, sc);
                case 2 -> viewFlights(con);
                case 3 -> updateFlight(con, sc);
                case 4 -> deleteFlight(con, sc);
                case 5 -> searchFlight(con, sc);
                case 6 -> {
                    return;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }

    // ADD FLIGHT BOOKING
    public static void addFlight(Connection con, Scanner sc) throws Exception {
        System.out.print("Booking ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Passenger Name: ");
        String name = sc.nextLine();
        System.out.print("Flight Name: ");
        String flight = sc.nextLine();
        System.out.print("Source: ");
        String src = sc.nextLine();
        System.out.print("Destination: ");
        String dest = sc.nextLine();
        System.out.print("Seat Class: ");
        String seat = sc.nextLine();
        System.out.print("Status: ");
        String status = sc.nextLine();

        String q = "INSERT INTO flight_booking VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, flight);
        ps.setString(4, src);
        ps.setString(5, dest);
        ps.setString(6, seat);
        ps.setString(7, status);
        ps.executeUpdate();
        System.out.println("✔ Flight booked successfully!");
    }

    // VIEW FLIGHT BOOKINGS
    public static void viewFlights(Connection con) throws Exception {
        String q = "SELECT * FROM flight_booking";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(q);

        System.out.println("ID | Passenger | Flight | Source | Destination | Class | Status");
        while (rs.next()) {
            System.out.println(
                    rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " +
                            rs.getString(4) + " | " + rs.getString(5) + " | " + rs.getString(6) + " | "
                            + rs.getString(7));
        }
    }

    // UPDATE FLIGHT
    public static void updateFlight(Connection con, Scanner sc) throws Exception {
        System.out.print("Enter Booking ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("New Status: ");
        String status = sc.nextLine();

        String q = "UPDATE flight_booking SET status=? WHERE booking_id=?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setString(1, status);
        ps.setInt(2, id);

        if (ps.executeUpdate() > 0)
            System.out.println("✔ Updated successfully!");
        else
            System.out.println("❌ Booking not found!");
    }

    // DELETE FLIGHT
    public static void deleteFlight(Connection con, Scanner sc) throws Exception {
        System.out.print("Enter Booking ID: ");
        int id = sc.nextInt();

        String q = "DELETE FROM flight_booking WHERE booking_id=?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1, id);

        if (ps.executeUpdate() > 0)
            System.out.println("✔ Deleted successfully!");
        else
            System.out.println("❌ Booking not found!");
    }

    // SEARCH FLIGHT
    public static void searchFlight(Connection con, Scanner sc) throws Exception {
        System.out.print("Enter Booking ID: ");
        int id = sc.nextInt();

        String q = "SELECT * FROM flight_booking WHERE booking_id=?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("\n📄 FLIGHT DETAILS");
            System.out.println("ID: " + rs.getInt(1));
            System.out.println("Passenger: " + rs.getString(2));
            System.out.println("Flight: " + rs.getString(3));
            System.out.println("Source: " + rs.getString(4));
            System.out.println("Destination: " + rs.getString(5));
            System.out.println("Seat Class: " + rs.getString(6));
            System.out.println("Status: " + rs.getString(7));
        } else {
            System.out.println("❌ No record found!");
        }
    }

    // ================================================
    // HOTEL BOOKING MENU
    // ================================================
    public static void hotelMenu(Connection con, Scanner sc) throws Exception {
        while (true) {
            System.out.println("\n--- HOTEL BOOKING MENU ---");
            System.out.println("1. Add Hotel Booking");
            System.out.println("2. View Hotel Bookings");
            System.out.println("3. Update Booking");
            System.out.println("4. Delete Booking");
            System.out.println("5. Search Booking");
            System.out.println("6. Back");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> addHotel(con, sc);
                case 2 -> viewHotels(con);
                case 3 -> updateHotel(con, sc);
                case 4 -> deleteHotel(con, sc);
                case 5 -> searchHotel(con, sc);
                case 6 -> {
                    return;
                }
            }
        }
    }

    // ADD HOTEL
    public static void addHotel(Connection con, Scanner sc) throws Exception {
        System.out.print("Booking ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Customer Name: ");
        String name = sc.nextLine();
        System.out.print("Hotel Name: ");
        String hotel = sc.nextLine();
        System.out.print("Room Type: ");
        String room = sc.nextLine();
        System.out.print("Check-in Date: ");
        String in = sc.nextLine();
        System.out.print("Check-out Date: ");
        String out = sc.nextLine();
        System.out.print("Status: ");
        String status = sc.nextLine();

        String q = "INSERT INTO hotel_booking VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, hotel);
        ps.setString(4, room);
        ps.setString(5, in);
        ps.setString(6, out);
        ps.setString(7, status);
        ps.executeUpdate();

        System.out.println("✔ Hotel booked successfully!");
    }

    // VIEW HOTELS
    public static void viewHotels(Connection con) throws Exception {
        String q = "SELECT * FROM hotel_booking";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(q);

        while (rs.next()) {
            System.out.println(
                    rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " +
                            rs.getString(4) + " | " + rs.getString(5) + " | " + rs.getString(6) + " | "
                            + rs.getString(7));
        }
    }

    // UPDATE HOTEL
    public static void updateHotel(Connection con, Scanner sc) throws Exception {
        System.out.print("Enter Booking ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("New Status: ");
        String status = sc.nextLine();

        String q = "UPDATE hotel_booking SET status=? WHERE booking_id=?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setString(1, status);
        ps.setInt(2, id);

        if (ps.executeUpdate() > 0)
            System.out.println("✔ Updated successfully!");
        else
            System.out.println("❌ Booking not found!");
    }

    // DELETE HOTEL
    public static void deleteHotel(Connection con, Scanner sc) throws Exception {
        System.out.print("Enter Booking ID: ");
        int id = sc.nextInt();

        String q = "DELETE FROM hotel_booking WHERE booking_id=?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1, id);

        if (ps.executeUpdate() > 0)
            System.out.println("✔ Deleted!");
        else
            System.out.println("❌ Booking not found!");
    }

    // SEARCH HOTEL
    public static void searchHotel(Connection con, Scanner sc) throws Exception {
        System.out.print("Booking ID: ");
        int id = sc.nextInt();

        String q = "SELECT * FROM hotel_booking WHERE booking_id=?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("📄 HOTEL DETAILS");
            System.out.println("ID: " + rs.getInt(1));
            System.out.println("Customer: " + rs.getString(2));
            System.out.println("Hotel: " + rs.getString(3));
            System.out.println("Room: " + rs.getString(4));
            System.out.println("Check-In: " + rs.getString(5));
            System.out.println("Check-Out: " + rs.getString(6));
            System.out.println("Status: " + rs.getString(7));
        } else {
            System.out.println("❌ No record found!");
        }
    }

    // ================================================
    // BUS BOOKING MENU
    // ================================================
    public static void busMenu(Connection con, Scanner sc) throws Exception {
        while (true) {
            System.out.println("\n--- BUS BOOKING MENU ---");
            System.out.println("1. Add Bus Booking");
            System.out.println("2. View Bus Bookings");
            System.out.println("3. Update Booking");
            System.out.println("4. Delete Booking");
            System.out.println("5. Search Booking");
            System.out.println("6. Back");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> addBus(con, sc);
                case 2 -> viewBus(con);
                case 3 -> updateBus(con, sc);
                case 4 -> deleteBus(con, sc);
                case 5 -> searchBus(con, sc);
                case 6 -> {
                    return;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }

    // ADD BUS BOOKING
    public static void addBus(Connection con, Scanner sc) throws Exception {
        System.out.print("Booking ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Passenger Name: ");
        String name = sc.nextLine();
        System.out.print("Bus Name: ");
        String bus = sc.nextLine();
        System.out.print("Source: ");
        String src = sc.nextLine();
        System.out.print("Destination: ");
        String dest = sc.nextLine();
        System.out.print("Seat No: ");
        String seat = sc.nextLine();
        System.out.print("Status: ");
        String status = sc.nextLine();

        String q = "INSERT INTO bus_booking VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, bus);
        ps.setString(4, src);
        ps.setString(5, dest);
        ps.setString(6, seat);
        ps.setString(7, status);
        ps.executeUpdate();

        System.out.println("✔ Bus booked successfully!");
    }

    // VIEW BUS BOOKINGS
    public static void viewBus(Connection con) throws Exception {
        String q = "SELECT * FROM bus_booking";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(q);

        while (rs.next()) {
            System.out.println(
                    rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " +
                            rs.getString(4) + " | " + rs.getString(5) + " | " + rs.getString(6) + " | "
                            + rs.getString(7));
        }
    }

    // UPDATE BUS
    public static void updateBus(Connection con, Scanner sc) throws Exception {
        System.out.print("Enter Booking ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("New Status: ");
        String status = sc.nextLine();

        String q = "UPDATE bus_booking SET status=? WHERE booking_id=?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setString(1, status);
        ps.setInt(2, id);

        if (ps.executeUpdate() > 0)
            System.out.println("✔ Updated!");
        else
            System.out.println("❌ Not found!");
    }

    // DELETE BUS
    public static void deleteBus(Connection con, Scanner sc) throws Exception {
        System.out.print("Enter Booking ID: ");
        int id = sc.nextInt();

        String q = "DELETE FROM bus_booking WHERE booking_id=?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1, id);

        if (ps.executeUpdate() > 0)
            System.out.println("✔ Deleted!");
        else
            System.out.println("❌ Not found!");
    }

    // SEARCH BUS
    public static void searchBus(Connection con, Scanner sc) throws Exception {
        System.out.print("Booking ID: ");
        int id = sc.nextInt();

        String q = "SELECT * FROM bus_booking WHERE booking_id=?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("📄 BUS BOOKING DETAILS");
            System.out.println("ID: " + rs.getInt(1));
            System.out.println("Passenger: " + rs.getString(2));
            System.out.println("Bus: " + rs.getString(3));
            System.out.println("Source: " + rs.getString(4));
            System.out.println("Destination: " + rs.getString(5));
            System.out.println("Seat: " + rs.getString(6));
            System.out.println("Status: " + rs.getString(7));
        } else {
            System.out.println("❌ No booking found!");
        }
    }
}
