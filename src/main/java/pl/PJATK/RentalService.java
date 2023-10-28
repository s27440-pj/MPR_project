package pl.PJATK;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ListIterator;

public class RentalService {

    public static boolean isAvailable(String vin, LocalDate rentalDate, LocalDate returnDate) {
        RentalStorage rentalStorage = RentalStorage.getInstance();
        ListIterator<Rental> rentalsIterator = rentalStorage.getAllRentals().listIterator();
        while (rentalsIterator.hasNext()) {
            Rental currentRental = rentalsIterator.next();
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

    public static void rent(Client client, String vin, LocalDate rentalDate, LocalDate returnDate) {
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

    public static double estimatedPrice(String vin, LocalDate rentalDate, LocalDate returnDate){
        CarStorage carstorage = CarStorage.getInstance();
        double daysOfRent = ChronoUnit.DAYS.between(rentalDate, returnDate);
        double pricePerDay = 100.0;
        for (Car currentCar : carstorage.getAllCars()){
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
