/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrentalappnew;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.*;
import java.util.List; // Import this to avoid ambiguity
import java.text.SimpleDateFormat;


/**
 *
 * @author ASUS TUF
 */
public class WebManager {
    private final JFrame frame;            // Main frame for the application
    private final JPanel mainPanel;        // Container panel with CardLayout
    private final CardLayout cardLayout;   // Layout manager to switch between panels
    private BookingDetails bookingDetails; // Store booking details
    private final ArrayList<BookingDetails> bookings; // Store multiple bookings (max 4)
    private String currentCar;
    private String currentUser;
    private final Map<String, Integer> carInventory; // Track car counts
    private final Map<String, List<List<DateRange>>> carBookings; // Track bookings for multiple units
    private Map<String, String> userReceipts = new HashMap<>(); // To store user receipts
    
    
    // Save receipt for a user
public void saveReceipt(String username, String receipt) {
    userReceipts.put(username, receipt);
}

// Retrieve receipt for a user
public String getReceipt(String username) {
    return userReceipts.getOrDefault(username, null);
}
public void setCurrentUser(String currentUser) {
    this.currentUser = currentUser;
}
public String getCurrentUser() {
    return this.currentUser;
}


public String getCurrentCar() {
    return this.currentCar;
}
public void setCurrentCar(String carName) {
    this.currentCar = carName;
}




    public WebManager() {
        // Initialize inventory
        carInventory = new HashMap<>();
        carInventory.put("Toyota Alphard", 3);
        carInventory.put("Audi R8", 4);
        carInventory.put("BMW M4 Competition", 3);
        carInventory.put("Lamborghini Urus", 2);

        carBookings = new HashMap<>();
    for (String car : carInventory.keySet()) {
        List<List<DateRange>> units = new ArrayList<>();
        for (int i = 0; i < carInventory.get(car); i++) {
            units.add(new ArrayList<>()); // Create a list to track bookings for each unit
        }
        carBookings.put(car, units);
    }

    bookings = new ArrayList<>(); // Initialize the bookings list

        // Initialize the JFrame
        frame = new JFrame("Car Rental Application");
        cardLayout = new CardLayout(); // CardLayout to switch between panels
        mainPanel = new JPanel(cardLayout); // JPanel using CardLayout

        // Add panels to the mainPanel
        mainPanel.add(new LoginPanel(this), "Login");
        mainPanel.add(new SignupPanel(this), "Signup");
        mainPanel.add(new RentalDatePanel(this), "RentalDate");
        mainPanel.add(new ExploreCarsPanel(this), "ExploreCars");
        mainPanel.add(new RentalSummaryPanel(this), "RentalSummary");
        mainPanel.add(new PaymentMethodPanel(this), "PaymentMethod");


        // Configure the JFrame
        frame.add(mainPanel);               // Add mainPanel to the frame
        frame.setSize(800, 600);            // Set frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
        frame.setVisible(true);             // Make the frame visible

        // Show the "Login" panel initially
        cardLayout.show(mainPanel, "Login");
    }
    

    public void showPanel(String panelName) {
        
        // Reset fields for specific panels
    for (Component comp : mainPanel.getComponents()) {
        if (panelName.equals("Signup") && comp instanceof SignupPanel) {
            ((SignupPanel) comp).resetFields(); // Reset SignupPanel fields
        } else if (panelName.equals("RentalDate") && comp instanceof RentalDatePanel) {
            ((RentalDatePanel) comp).resetFields(); // Reset RentalDatePanel fields
        } else if (panelName.equals("ExploreCars") && comp instanceof ExploreCarsPanel) {
            // Optionally reset fields in ExploreCarsPanel (if needed)
        } else if (panelName.equals("PaymentMethod") && comp instanceof PaymentMethodPanel) {
            // Optionally reset fields in PaymentMethodPanel (if needed)
        }
    }
    // Check if switching to the RentalSummaryPanel
    if (panelName.equals("RentalSummary")) {
        for (Component comp : mainPanel.getComponents()) {
            if (comp instanceof RentalSummaryPanel) {
                ((RentalSummaryPanel) comp).updateSummary(); // Refresh the summary
            }
        }
    }

    // Check if switching to the RentalDatePanel
    if (panelName.equals("RentalDate")) {
    for (Component comp : mainPanel.getComponents()) {
        if (comp instanceof RentalDatePanel) {
            ((RentalDatePanel) comp).updateSelectedCar(); // Ensure the label is updated
            ((RentalDatePanel) comp).refreshCarAvailability(); // Refresh availability
            break;
        }
    }
}


    cardLayout.show(mainPanel, panelName); // Switch to the panel
}
    public int getCarInventory(String car) {
    return carInventory.getOrDefault(car, 0);
}


    // Add a booking to the list
    public boolean addBooking(BookingDetails bookingDetails) {
        if (bookings.size() < 4) {
            bookings.add(bookingDetails);
            return true; // Successfully added
        } else {
            return false; // Booking limit reached
        }
    }

    // Get all bookings
    public ArrayList<BookingDetails> getBookings() {
        return bookings;
    }
  public boolean isAvailable(String car, Date startDate, int rentalDays) {
    List<List<DateRange>> unitsBookings = carBookings.get(car);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(startDate);

    // Calculate the end date
    calendar.add(Calendar.DAY_OF_YEAR, rentalDays - 1);
    Date endDate = calendar.getTime();
    DateRange newRange = new DateRange(startDate, endDate);

    // Check each unit for availability
    for (List<DateRange> unitBookings : unitsBookings) {
        boolean available = true;
        for (DateRange existingRange : unitBookings) {
            if (existingRange.overlaps(newRange)) {
                available = false;
                break; // Stop checking this unit if there's an overlap
            }
        }
        if (available) {
            return true; // At least one unit is available for the requested dates
        }
    }

    return false; // No units available for the requested dates
}


    // Method to cancel a booking and increment inventory
public void cancelBooking(BookingDetails bookingDetails) {
    String carName = bookingDetails.getCarName();
    String rentalDates = bookingDetails.getRentalDates();
    String[] parts = rentalDates.split(" for ");
    String startDateStr = parts[0];

    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse(startDateStr);
        int rentalDays = bookingDetails.getRentalDays();

        // Calculate the end date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, rentalDays - 1);
        Date endDate = calendar.getTime();
        DateRange toCancelRange = new DateRange(startDate, endDate);

        // Find and remove the booking from the correct unit
        List<List<DateRange>> unitsBookings = carBookings.get(carName);
        for (List<DateRange> unitBookings : unitsBookings) {
            if (unitBookings.removeIf(range -> range.overlaps(toCancelRange))) {
                System.out.println("Canceled booking: " + carName + " from " + startDate + " to " + endDate);
                return; // Booking canceled successfully
            }
        }

    } catch (Exception e) {
        System.out.println("Error canceling booking: " + e.getMessage());
    }
}






   public void bookCar(String car, Date startDate, int rentalDays) {
    if (!isAvailable(car, startDate, rentalDays)) {
        throw new IllegalStateException("Car not available for selected dates.");
    }

    System.out.println("Booking car: " + car);

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(startDate);
    calendar.add(Calendar.DAY_OF_YEAR, rentalDays - 1);
    Date endDate = calendar.getTime();
    DateRange newBooking = new DateRange(startDate, endDate);

    // Find an available unit and book it
    List<List<DateRange>> unitsBookings = carBookings.get(car);
    for (List<DateRange> unitBookings : unitsBookings) {
        boolean available = true;
        for (DateRange existingRange : unitBookings) {
            if (existingRange.overlaps(newBooking)) {
                available = false;
                break;
            }
        }

        if (available) {
            unitBookings.add(newBooking); // Add booking to the available unit
            System.out.println("Booking added: " + newBooking);
            return; // Successfully booked a unit
        }
    }
}



}