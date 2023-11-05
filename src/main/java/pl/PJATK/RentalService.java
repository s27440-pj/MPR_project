package pl.PJATK;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

public class RentalService {
    private static RentalService instance = null;

    private RentalService(){
    }

    public static RentalService getInstance() {
        if (instance == null) {
            instance = new RentalService();
        }
        return instance;
    }

    public Car carExist(String vin){
        CarStorage carStorage = CarStorage.getInstance();
        for (Car currentCar : carStorage.getAllCars()){
            if (vin.equals(currentCar.getVin())) {
                return currentCar;
            }
        }
        throw new NoSuchElementException("There is no such car.");
    }

    public boolean isAvailable(String vin, LocalDate rentalDate, LocalDate returnDate) {
        RentalService rentalService = RentalService.getInstance();
        RentalStorage rentalStorage = RentalStorage.getInstance();
        rentalService.carExist(vin);
        for (Rental currentRental : rentalStorage.getAllRentals()) {
            if (vin.equals(currentRental.getCar().getVin())) {
                // Checking if car isn't already busy between potential renting dates
                if (isBetween(currentRental.getRentalDate(), currentRental.getReturnDate(), rentalDate)
                || isBetween(currentRental.getRentalDate(), currentRental.getReturnDate(), returnDate)) {
                    return false;
                }
                if (isBetween(rentalDate, returnDate, currentRental.getRentalDate())
                || isBetween(rentalDate, returnDate, currentRental.getReturnDate())){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isBetween(LocalDate periodStart, LocalDate periodEnd, LocalDate checkingDate){
        return checkingDate.isAfter(periodStart) && checkingDate.isBefore(periodEnd);
    }

    public Rental rent(Client client, String vin, LocalDate rentalDate, LocalDate returnDate) {
        if (isAvailable(vin, rentalDate, returnDate)) {
            RentalStorage rentalStorage = RentalStorage.getInstance();
            RentalService rentalService = RentalService.getInstance();
            Car car = rentalService.carExist(vin);
            Rental rental = new Rental(client, car, rentalDate, returnDate);
            rentalStorage.addRental(rental);
            return rental;
        } else {
            throw new IllegalStateException("This car is not available during those dates.");
        }
    }

    public double estimatedPrice(String vin, LocalDate rentalDate, LocalDate returnDate){
        RentalService rentalService = RentalService.getInstance();
        Car car = rentalService.carExist(vin);
        long daysOfRent = ChronoUnit.DAYS.between(rentalDate, returnDate);
        double pricePerDay = 500;
        return pricePerDay * daysOfRent * car.getType().getMultiplyer();
    }
}
