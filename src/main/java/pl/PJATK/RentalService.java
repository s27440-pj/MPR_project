package pl.PJATK;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class RentalService {
    public static void rent(Client client, String vin, String rentalDate, String returnDate){
        RentalStorage rentalStorage = RentalStorage.getInstance();
        ListIterator<Rental> rentalsIterator = rentalStorage.rentals.listIterator();
        List<Rental> newRentals = new ArrayList<>();

        while (rentalsIterator.hasNext()) {
            Rental currentRental = rentalsIterator.next();
            if (vin.equals(currentRental.getCar().getVin())) {
                // Instant doesn't use < or > - there is a function "compareTo"
                // if date of considered rental is earlier than return date - we can't rent
                if (Instant.parse(rentalDate).compareTo(currentRental.getReturnDate()) <= 0) {
                    System.out.println("we cant rent you a car.");
                    newRentals = new ArrayList<>();
                } else {
                    System.out.println("we can rent you this car");
                    Car car = currentRental.getCar();
                    newRentals.add(new Rental(client, car, rentalDate, returnDate));
                }
            }
        }
        if (!newRentals.isEmpty()) {
            rentalStorage.addRental(newRentals.get(0));
        }
    }
}
