package pl.PJATK;

public class Main {
    public static void main(String[] args) {
        System.out.println("Car Rental");
        Car car1 = new Car("Ford", "Fiesta", "abc123", Type.STANDARD);
        Car car2 = new Car("Ford", "Mondeo", "def456", Type.STANDARD);

//    CarStorage carStorage = new CarStorage();

        CarStorage carStorage = CarStorage.getInstance();
        carStorage.addCar(car1);
        CarStorage carStorage2 = CarStorage.getInstance();
        carStorage2.addCar(car2);

        System.out.println(carStorage.getAllCars());
        System.out.println(carStorage2.getAllCars());

        //RentalService.rent(userId, vin, rentalDate, returnDane) - tworzy wynajem jeśli jest możliwy
        //RentalService.isAvailable(vin, rentalDate, returnDate)
        //RentalService.estimatePrice(vin, rentalDate, returnDate)
    }
}