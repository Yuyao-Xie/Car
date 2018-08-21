package practice;

public class GasCar extends Car{
    private int numberCylinders;
    private int mpg;
    public static final String TYPE_NAME = "gasCar";

    public GasCar(String model,String vehicleClass, int pollutionScore, String TYPE_NAME,int numberCylinders, int mpg){
        super(model,vehicleClass,pollutionScore);
        this.numberCylinders = numberCylinders;
        this.mpg = mpg;
        this.carType = TYPE_NAME;
    }

    public int getMpg(){
        return mpg;
    }

    public  int getNumberCylinders(){
        return numberCylinders;
    }

    @Override
    public String toString(){
        return "Model: " + this.model + " Class: " + this.vehicleClass + " Pollution Score: "
                + this.pollutionScore + " MPG: " + this.mpg + " Cylinders: " + this.numberCylinders + "\n";

    }


}
