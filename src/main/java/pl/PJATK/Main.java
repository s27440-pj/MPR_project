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

        Client client1 = new Client(1);
        Client client2 = new Client(2);
        Client client3 = new Client(3);

        Rental rental1 = new Rental(client1, car1,"2023-10-10T10:00:00.000+01:00",
                "2023-10-15T21:00:00.000+01:00");
        Rental rental2 = new Rental(client2, car2,"2023-10-12T10:00:00.000+01:00",
                "2023-10-17T21:00:00.000+01:00");
        RentalStorage rentalStorage = RentalStorage.getInstance();
        rentalStorage.addRental(rental1);
        rentalStorage.addRental(rental2);

        RentalService.rent(client2, "abc123", "2023-10-16T10:00:00.000+01:00",
                "2023-10-19T21:00:00.000+01:00");

        System.out.println(rentalStorage.getAllRentals());
    }
}