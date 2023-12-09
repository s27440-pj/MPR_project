package pl.PJATK;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CarStorage {
//    private static CarStorage instance = null;
    private List<Car> cars = new ArrayList<>();

//    private CarStorage() {
//    }
//
//    public static CarStorage getInstance() {
//        if (instance == null) {
//            instance = new CarStorage();
//        }
//        return instance;
//    }

    public List<Car> getAllCars() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }

    public void pureDatabase(){
        cars = new ArrayList<>();
    }

    public Optional<Car> findCarByVin(String vin){
        return cars.stream()
                .filter(car -> car.getVin().equals(vin))
                .findFirst();
    }
}
