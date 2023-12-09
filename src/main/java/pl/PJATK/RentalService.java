package pl.PJATK;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RentalService {
    private final CarStorage carStorage;
    private final RentalStorage rentalStorage;

    public RentalService(CarStorage carStorage, RentalStorage rentalStorage) {
        this.carStorage = carStorage;
        this.rentalStorage = rentalStorage;
    }


    public Car carExist(String vin){
        for (Car currentCar : carStorage.getAllCars()){
            if (vin.equals(currentCar.getVin())) {
                return currentCar;
            }
        }
        throw new NoSuchElementException("There is no such car.");
    }

    public boolean isAvailable(String vin, LocalDate rentalDate, LocalDate returnDate) {
        if (returnDate.isBefore(rentalDate) || returnDate == rentalDate ||
                rentalDate.isBefore(LocalDate.of(2023,11,26))){
            throw new RuntimeException("Incorrect dates of rental");
        }
//        carExist(vin);
//        Optional<Car> carByVin = carStorage.findCarByVin(vin);
//        Car car = carByVin.orElseThrow();
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
            Car car = carExist(vin);
            Rental rental = new Rental(client, car, rentalDate, returnDate);
            rentalStorage.addRental(rental);
            return rental;
        } else {
            throw new IllegalStateException("This car is not available during those dates.");
        }
    }

    public double estimatedPrice(String vin, LocalDate rentalDate, LocalDate returnDate){
        Optional<Car> carByVin = carStorage.findCarByVin(vin);
        Car car = carByVin.orElseThrow();
        long daysOfRent = ChronoUnit.DAYS.between(rentalDate, returnDate);
        if (daysOfRent < 1){
            throw new RuntimeException("You can rent car for 1 day minimum, incorrect dates of rent");
        }
        double pricePerDay = 500;
        return pricePerDay * daysOfRent * car.getType().getMultiplyer();
    }
}
