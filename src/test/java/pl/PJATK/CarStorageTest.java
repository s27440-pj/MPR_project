package pl.PJATK;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarStorageTest {
    private CarStorage carStorage = CarStorage.getInstance();

    @Test
    void shouldHaveEntriesInStorage(){
        //GIVEN
        carStorage.addCar(new Car("toyota", "corolla", "123abc", Type.STANDARD));

        //WHEN
        List<Car> allCars = carStorage.getAllCars();

        //THEN
        assertThat(allCars).isNotEmpty();
    }

}