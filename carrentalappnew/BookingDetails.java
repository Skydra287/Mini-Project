/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrentalappnew;

/**
 *
 * @author ASUS TUF
 */
    class BookingDetails {
    String carName;
    String rentalDates;
    int rentalDays;
    int basePrice;  // Base car price
    int addOnPrice; // Price for add-ons
    boolean hasGPS;
    boolean hasInsurance;

    // Constructor
    public BookingDetails(String carName, String rentalDates, int rentalDays, int basePrice, int addOnPrice, boolean hasGPS, boolean hasInsurance) {
        this.carName = carName;
        this.rentalDates = rentalDates;
        this.rentalDays = rentalDays;
        this.basePrice = basePrice;
        this.addOnPrice = addOnPrice;
        this.hasGPS = hasGPS;
        this.hasInsurance = hasInsurance;
    }

    // Getters
    public int getBasePrice() {
        return basePrice;
    }

    public int getAddOnPrice() {
        return addOnPrice;
    }

    public String getCarName() {
        return carName;
    }

    public String getRentalDates() {
        return rentalDates;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public boolean isHasGPS() {
        return hasGPS;
    }

    public boolean isHasInsurance() {
        return hasInsurance;
    }

    // Update Booking Details
    public void updateBooking(String rentalDates, int rentalDays, int basePrice, int addOnPrice, boolean hasGPS, boolean hasInsurance) {
        this.rentalDates = rentalDates;
        this.rentalDays = rentalDays;
        this.basePrice = basePrice;
        this.addOnPrice = addOnPrice;
        this.hasGPS = hasGPS;
        this.hasInsurance = hasInsurance;
    }
}

    
