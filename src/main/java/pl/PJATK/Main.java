package pl.PJATK;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Main {

    private final CarStorage carStorage;
    private final RentalService rentalService;

    public Main(CarStorage carStorage, RentalService rentalService) {
        this.carStorage = carStorage;
        this.rentalService = rentalService;
    }

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }

    public void exec(){
        System.out.println("Car Rental");
        Car car1 = new Car("Ford", "Fiesta", "abc123", Type.STANDARD);
        Car car2 = new Car("Ford", "Mondeo", "def456", Type.STANDARD);
//        CarStorage carStorage = CarStorage.getInstance();
        carStorage.addCar(car1);
        carStorage.addCar(car2);

//        RentalStorage rentalStorage = RentalStorage.getInstance();


//        RentalService rentalService = new RentalService(carStorage, rentalStorage);

        Client client1 = new Client(1);
        Client client2 = new Client(2);
        Client client3 = new Client(3);

        Rental rental1 = rentalService.rent(client1, "abc123",
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(3));
        Rental rental2 = rentalService.rent(client1, "abc123",
                LocalDate.now().plusDays(8),
                LocalDate.now().plusDays(10));
//        Rental rental3 = rentalService.rent(client1, "abc123",
//                LocalDate.now().plusDays(2),
//                LocalDate.now().plusDays(5));
        Rental rental4 = rentalService.rent(client1, "abc123",
                LocalDate.now(),
                LocalDate.now().plusDays(11));


//        RentalStorage rentalStorage = RentalStorage.getInstance();
//        rentalStorage.addRental(rental1);
//        rentalStorage.addRental(rental2);
//
//        RentalService rentalService = RentalService.getInstance();
//
//        rentalService.rent(client2, "abc1234", LocalDate.of(123, 10, 16),
//                LocalDate.of(123, 10, 18));
//        System.out.println(rentalService.estimatedPrice(car1.getVin(), LocalDate.of(123,10,16),
//                LocalDate.of(123,10,18)));
//
//        System.out.println(rentalStorage.getAllRentals());
    }
}