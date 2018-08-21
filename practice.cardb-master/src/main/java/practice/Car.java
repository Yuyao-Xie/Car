package practice;

public abstract class Car implements Comparable<Car> {
    protected String model;
    protected String vehicleClass;
    protected int pollutionScore;
    protected String carType;

    public Car(String model,String vehicleClass, int pollutionScore){
        this. model = model;
        this.vehicleClass = vehicleClass;
        this.pollutionScore = pollutionScore;
    }

    @Override
    public int compareTo(Car x) {
        int res = this.pollutionScore - x.pollutionScore;
        if (res == 0) {
            return this.model.compareTo(x.model);
        }
        else {
            return res;
        }
    }

    public String getCarType(){
      return carType;
    }
}