package pl.PJATK;

import java.time.LocalDate;
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
}
