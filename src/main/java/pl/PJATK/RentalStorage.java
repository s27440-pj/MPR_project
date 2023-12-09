package pl.PJATK;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentalStorage {
//    private static RentalStorage instance = null;
    private List<Rental> rentals = new ArrayList<>();

//    private RentalStorage() {
//    }

//    public static RentalStorage getInstance() {
//        if (instance == null) {
//            instance = new RentalStorage();
//        }
//        return instance;
//    }

    public List<Rental> getAllRentals() {
        return rentals;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public void removeRental(Rental rental) {
        rentals.remove(rental);
    }
}
