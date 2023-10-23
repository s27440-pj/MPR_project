package pl.PJATK;

import java.time.Instant;

public class Rental {
    private Client client;
    private Car car;
    private Instant rentalDate;
    private Instant returnDate;


    public Rental(Client client, Car car, String rentalDate, String returnDate) {
        this.client = client;
        this.car = car;
        this.rentalDate = Instant.parse(rentalDate);
        this.returnDate = Instant.parse(returnDate);
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

    public Instant getRentalDate() {
        return this.rentalDate;
    }

    public Instant getReturnDate() {
        return this.returnDate;
    }

    @Override
    public String toString(){
        return "Rental: {" +
                "Client: " + getClient().getClientID() + " " +
                "Car: " + getCar().getVin() + " " +
                "Rental Date: " + getRentalDate() + " " +
                "Return Date: " + getReturnDate() + "}";
    }

}
