package pl.PJATK;

public class Car {
    private final String mark;
    private final String model;
    private final String vin;
    private final Type type;

    public Car (String mark, String model, String vin, Type type){
        this.mark = mark;
        this.model = model;
        this.vin = vin;
        this.type = type;
    }

    public String getMark(){
        return this.mark;
    }
    public String getModel(){
        return this.model;
    }
    public String getVin(){
        return this.vin;
    }
    public Type getType(){
        return this.type;
    }

    @Override
    public String toString(){
        return " Car{" +
                "mark: " + getMark() + " " +
                "model: " + getModel() + " " +
                "vin: " + getVin() + " " +
                "car type: " + getType() + " " +
                '}';
    }
}
