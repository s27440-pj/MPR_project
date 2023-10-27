package pl.PJATK;

import java.time.Instant;
import java.util.ListIterator;

public class RentalService {

    public static boolean isAvailable( String vin, String rentalDate, String returnDate){
        RentalStorage rentalStorage = RentalStorage.getInstance();
        ListIterator<Rental> rentalsIterator = rentalStorage.getAllRentals().listIterator();
        while (rentalsIterator.hasNext()) {
            Rental currentRental = rentalsIterator.next();
            if (vin.equals(currentRental.getCar().getVin())) {
                /*
                checking if rental day isn't between some other rental, and if return date isn't
                between some other rental either.
                */
                if ( (!Instant.parse(rentalDate).isBefore(currentRental.getRentalDate()) &&
                        !Instant.parse(rentalDate).isAfter(currentRental.getReturnDate())) ||
                        (!Instant.parse(returnDate).isBefore(currentRental.getRentalDate()) &&
                                !Instant.parse(returnDate).isAfter(currentRental.getReturnDate())) ){
                    return false;
                }
            }
        }
        return true;
    }

    public static void rent(Client client, String vin, String rentalDate, String returnDate){
        if (isAvailable(vin, rentalDate, returnDate)){
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
