package pl.PJATK;

import java.time.LocalDate;

public class Rental {
    private Client client;
    private Car car;
    private LocalDate rentalDate;
    private LocalDate returnDate;


    public Rental(Client client, Car car, LocalDate rentalDate, LocalDate returnDate) {
        this.client = client;
        this.car = car;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

//    // method to transform String into Date
//    private Date parseDate(String dateString) throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        return dateFormat.parse(dateString);
//    }

    public Client getClient() {
        return this.client;
    }

    public Car getCar() {
        return this.car;
    }

    public LocalDate getRentalDate() {
        return this.rentalDate;
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    @Override
    public String toString() {
        return "Rental: {" +
                "Client: " + getClient().getClientID() + " " +
                "Car: " + getCar().getVin() + " " +
                "Rental Date: " + getRentalDate() + " " +
                "Return Date: " + getReturnDate() + "}";
    }

}
