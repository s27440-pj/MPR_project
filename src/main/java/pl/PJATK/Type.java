package pl.PJATK;

public enum Type {
    PREMIUM(30), STANDARD(1.5), ECONOMY(1);

    private final double multiplyer;

    Type(double multiplyer) {
        this.multiplyer = multiplyer;
    }

    public double getMultiplyer() {
        return multiplyer;
    }
}
