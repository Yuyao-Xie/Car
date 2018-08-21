package practice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class CarList {
    private static final int ARRAY_STEP = 10;
    private int numOfCar = 0;
    private Car[] list = new Car[ARRAY_STEP];

    public Car[] addCar(Car newCar){
        if (numOfCar == list.length) {
            list = Arrays.copyOf(list, list.length + ARRAY_STEP);
        }
        for(int i = 0; i < numOfCar; i++){
            if(newCar.compareTo(list[i]) <= 0) {
                Car tempOne = newCar;
                for (int j = i; j < numOfCar + 1; j++) {
                    Car tempTwo = list[j];
                    list[j] = tempOne;
                    tempOne = tempTwo;
                }
                numOfCar++;
                return list;
            }
        }
        numOfCar++;
        list[numOfCar - 1] = newCar;
        return list;
    }

    public String toString(){
        StringBuilder representation = new StringBuilder("");
        for(int i = 0; i < numOfCar; i++){
            representation.append(list[i].toString());
        }
        return representation.toString();
    }

    public String toStringGreenCars(){
        StringBuilder repGreenCar = new StringBuilder("");
        for(int i = 0; i < numOfCar; i++){
            if(list[i].getCarType().equals(GreenCar.TYPE_NAME)){
                repGreenCar.append("Model: " + list[i].model + " Fuel Type: " + ((GreenCar)list[i]).getFuelType() + "\n");
            }
        }
        return repGreenCar.toString();
    }

    public double avgMpg(){
        double numOfGas = 0;
        double totalMpg = 0;
        for(int i = 0; i < numOfCar; i++){
            if(list[i].getCarType().equals(GasCar.TYPE_NAME)){
                numOfGas ++;
                totalMpg += ((GasCar)list[i]).getMpg();
            }
        }
        if (numOfGas == 0) {
            return 0;
        }
        return totalMpg/numOfGas;
    }

    public double avgMpgByPartialModel(String partial){
        double numOfPartial = 0;
        double totalMpg = 0;
        for (int i = 0; i < numOfCar; i++){
            if(list[i].model.contains(partial) && list[i].getCarType().equals(GasCar.TYPE_NAME)){
                numOfPartial++;
                totalMpg += ((GasCar)list[i]).getMpg();
            }
        }
        if (numOfPartial == 0) {
            return 0;
        }
        return totalMpg/numOfPartial;
    }

    public String[] findClassesByCylinders(int numOfCylinder){
        Set<String> classSet = new HashSet<>();
        String[] findings = new String[numOfCar];
        int j  = 0;
        for (int i = 0; i < numOfCar; i++){
            if (list[i].getCarType().equals(GasCar.TYPE_NAME) ){
                if(((GasCar)list[i]).getNumberCylinders() == numOfCylinder){
                    if(!classSet.contains(list[i].vehicleClass)){
                        findings[j] = (list[i].vehicleClass);
                        classSet.add(list[i].vehicleClass);
                        j++;
                    }
                }
            }
        }
        String[] results = Arrays.copyOf(findings, j);
        Arrays.sort(results);
        return results;
    }

    public String[] findModelsByClassAndMpg(String tgClass, int minMpg){
        String[] findings = new String[numOfCar];
        int j  = 0;
        for (int i = 0; i < numOfCar; i++) {
            if (list[i].getCarType().equals(GasCar.TYPE_NAME)) {
                if (list[i].vehicleClass.equals(tgClass)  && ((GasCar) list[i]).getMpg() >= minMpg) {
                    findings[j] = list[i].model;
                    j++;
                }
            }
        }
        String[] results = Arrays.copyOf(findings,j);
        Arrays.sort(results);
        return results;
    }
}
