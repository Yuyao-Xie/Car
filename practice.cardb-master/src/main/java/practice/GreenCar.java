package practice;

public class GreenCar extends Car{
    private String fuelType;
    public static final String TYPE_NAME = "GreenCar";

    public GreenCar(String model,String vehicleClass, int pollutionScore, String TYPE_NAME, String fuelType){
        super(model,vehicleClass,pollutionScore);
        this.fuelType = fuelType;
        this.carType = TYPE_NAME;
    }

    public String getFuelType() {
        return fuelType;
    }

    @Override
    public String toString(){
        return "Model: " + this.model + " Class: " + this.vehicleClass + " Pollution Score: "
                + this.pollutionScore + " Fuel Type: " + this.fuelType + "\n";

    }
}
