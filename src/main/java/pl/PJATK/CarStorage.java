package pl.PJATK;

import java.util.ArrayList;
import java.util.List;

public class CarStorage {
    private static CarStorage instance = null;
    private List<Car> cars = new ArrayList<>();

    private CarStorage() {
    }
    public static CarStorage getInstance(){
        if (instance == null){
            instance = new CarStorage();
        }
        return instance;
    }

    public List<Car> getAllCars(){
        return cars;
    }
    public void addCar(Car car){
        cars.add(car);
    }
    public void removeCar(Car car){
        cars.remove(car);
    }
}
