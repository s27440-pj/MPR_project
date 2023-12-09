package pl.PJATK;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RentalServiceTest {
    CarStorage carStorage = new CarStorage();
    RentalStorage rentalStorage = new RentalStorage();
    private RentalService rentalService = new RentalService(carStorage, rentalStorage);


    @Test
    void estimatedPriceShouldBeGood() {
        //GIVEN
        Car car1 = new Car("Ford", "Fiesta", "abc123", Type.STANDARD);
        carStorage.addCar(car1);
        LocalDate rentalDate = LocalDate.of(2023, 10, 16);
        LocalDate returnDate = LocalDate.of(2023, 10, 19);

        //WHEN
        double estimatedPrice = rentalService.estimatedPrice(car1.getVin(), rentalDate, returnDate);

        //THEN
        assertThat(estimatedPrice).isEqualTo(2250);
    }

    @Test
    void shouldThrowExceptionsWhenDatesAreIncorrectInEstimatedPrice() {
        //GIVEN
        Car car1 = new Car("Ford", "Fiesta", "abc123", Type.STANDARD);
        carStorage.addCar(car1);
        LocalDate rentalDate = LocalDate.of(2023, 10, 19);
        LocalDate returnDate = LocalDate.of(2023, 10, 16);

        //WHEN
        //THEN
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(
                () -> rentalService.estimatedPrice(car1.getVin(), rentalDate, returnDate)
        );
    }

    @ParameterizedTest
    @MethodSource("availableData")
    void shouldBeAvailable(LocalDate rentalDate, LocalDate returnDate) {
        //GIVEN
        Car car1 = new Car("Ford", "Fiesta", "abc123", Type.STANDARD);
        carStorage.addCar(car1);
        Client client1 = new Client(123);
        rentalService.rent(client1, car1.getVin(), LocalDate.of(2023,12,1) ,
                LocalDate.of(2023,12,6));

        //WHEN
        boolean isCarAvailable = rentalService.isAvailable(car1.getVin(), rentalDate, returnDate);

        //THEN
        assertThat(isCarAvailable).isTrue();
    }

    public static Stream<Arguments> availableData() {
        return Stream.of(
                Arguments.of(LocalDate.of(2023, 11, 28), LocalDate.of(2023, 12, 1)),
                Arguments.of(LocalDate.of(2023, 12, 6), LocalDate.of(2023, 12, 10)),
                Arguments.of(LocalDate.of(2023, 12, 11), LocalDate.of(2023, 12, 13))
        );
    }

    @ParameterizedTest
    @MethodSource("notAvailableData")
    void shouldNotBeAvailable(LocalDate rentalDate, LocalDate returnDate) {
        //GIVEN
        Car car1 = new Car("Ford", "Fiesta", "abc123", Type.STANDARD);
        carStorage.addCar(car1);
        Client client1 = new Client(123);
        rentalService.rent(client1, car1.getVin(), LocalDate.of(2023,12,1) ,
                LocalDate.of(2023,12,6));

        //WHEN
        boolean isCarAvailable = rentalService.isAvailable(car1.getVin(), rentalDate, returnDate);

        //THEN
        assertThat(isCarAvailable).isFalse();
    }

    public static Stream<Arguments> notAvailableData() {
        return Stream.of(
                Arguments.of(LocalDate.of(2023, 11, 28), LocalDate.of(2023, 12, 8)),
                Arguments.of(LocalDate.of(2023, 11, 29), LocalDate.of(2023, 12, 5)),
                Arguments.of(LocalDate.of(2023, 12, 2), LocalDate.of(2023, 12, 9)),
                Arguments.of(LocalDate.of(2023, 12, 3), LocalDate.of(2023, 12, 5))
        );
    }

    @ParameterizedTest
    @MethodSource("incorrectData")
    void shouldThrowExceptionsWhenDatesAreIncorrectInIsAvailable(LocalDate rentalDate, LocalDate returnDate) {
        //GIVEN
        Car car1 = new Car("Ford", "Fiesta", "abc123", Type.STANDARD);
        carStorage.addCar(car1);

        //WHEN
        //THEN
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(
                () -> rentalService.isAvailable(car1.getVin(), rentalDate, returnDate)
        );
    }

    public static Stream<Arguments> incorrectData() {
        return Stream.of(
                Arguments.of(LocalDate.of(2023, 11, 22), LocalDate.of(2023, 11, 25)),
                Arguments.of(LocalDate.of(2023, 12, 15), LocalDate.of(2023, 12, 12))
        );
    }
//    @Test
//    void shouldCreateNewRental(){
//        //GIVEN
//        Car car2 = new Car("Ford", "Fiesta", "def456", Type.STANDARD);
//        carStorage.addCar(car2);
//        Client client1 = new Client(123);
//
//        //WHEN
//        Rental rental = rentalService.rent(client1,car2.getVin(), LocalDate.of(2023, 12, 1),
//                LocalDate.of(2023, 12, 6));
//
//        //THEN
//        assertThat(rental).isEqualTo(new Rental(client1, car2, LocalDate.of(2023, 12, 1),
//                LocalDate.of(2023, 12, 6)));
//    }

}
