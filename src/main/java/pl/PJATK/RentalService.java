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

    public boolean carExist(String vin){
        CarStorage carStorage = CarStorage.getInstance();
        for (Car currentCar : carStorage.getAllCars()){
            if (vin.equals(currentCar.getVin())) {
                return true;
            }
        }
        return false;
    }

    public boolean isAvailable(String vin, LocalDate rentalDate, LocalDate returnDate) {
        RentalService rentalService = RentalService.getInstance();
        if (!rentalService.carExist(vin)){
            throw new NoSuchElementException("We don't have a car with vin number " + vin);
        }
        RentalStorage rentalStorage = RentalStorage.getInstance();
        for (Rental currentRental : rentalStorage.getAllRentals()) {
            if (vin.equals(currentRental.getCar().getVin())) {
                // Checking if car isn't already busy between potential renting dates
                if ((!rentalDate.isBefore(currentRental.getRentalDate()) &&
                        !rentalDate.isAfter(currentRental.getReturnDate())) ||
                        (!returnDate.isBefore(currentRental.getRentalDate()) &&
                                !returnDate.isAfter(currentRental.getReturnDate()))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void rent(Client client, String vin, LocalDate rentalDate, LocalDate returnDate) {
        if (isAvailable(vin, rentalDate, returnDate)) {
            RentalStorage rentalStorage = RentalStorage.getInstance();
            CarStorage carstorage = CarStorage.getInstance();

            for (Car currentCar : carstorage.getAllCars()) {
                if (vin.equals(currentCar.getVin())) {
                    rentalStorage.addRental(new Rental(client, currentCar, rentalDate, returnDate));
                    return;
                }
            }
        }
    }

    public double estimatedPrice(String vin, LocalDate rentalDate, LocalDate returnDate){
        RentalService rentalService = RentalService.getInstance();
        if (!rentalService.carExist(vin)){
            throw new NoSuchElementException("We don't have a car with vin number " + vin);
        }
        CarStorage carStorage = CarStorage.getInstance();
        long daysOfRent = ChronoUnit.DAYS.between(rentalDate, returnDate);
        double pricePerDay = 100.0;
        for (Car currentCar : carStorage.getAllCars()){
            if (vin.equals(currentCar.getVin())){
                if (currentCar.getType() == Type.ECONOMY){
                    pricePerDay = 80.0;
                    return daysOfRent*pricePerDay;
                } else if (currentCar.getType() == Type.PREMIUM){
                    pricePerDay = 120.0;
                    return daysOfRent*pricePerDay;
                }
            }
        }
        return daysOfRent*pricePerDay;
    }
}
