package pl.PJATK;

import java.util.Date;

public class Rental {
    private Client client;
    private Car car;
    private Date rentalDate;
    private Date returnDate;

    public Rental(Client client, Car car, Date rentalDate, Date returnDate) {
        this.client = client;
        this.car = car;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public Client getClient() {
        return this.client;
    }

    public Car getCar() {
        return this.car;
    }

    public Date getRentalDate() {
        return this.rentalDate;
    }

    public Date getReturnDate() {
        return this.returnDate;
    }

}
