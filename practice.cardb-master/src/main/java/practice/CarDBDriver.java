package practice;

import java.io.*;

/**
 * Driver class for the car database.
 *
 */
public class CarDBDriver{

	/**
	 * Main logic
	 * 
	 * Expected usage:
	 * 	java practice.CarDBDriver -in <relative path to cars.csv> -out <output file name>
	 * 
	 * Example:
	 *  java practice.CarDBDriver -in cars.csv -out actual.txt
	 * 	
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		File dataBase = null;
		for(int i = 0; i < args.length; i++ ){
			if(args[i].equals("-in") && i + 1 < args.length){
				dataBase = new File(args[i+1]);
			}
		}
		String destinationFileName = "";
		for (int i = 0; i< args.length; i++){
			if(args[i].equals("-out") && i + 1 < args.length){
				destinationFileName = args[i+1];
			}
		}
		if(dataBase == null || destinationFileName.isEmpty()){
			return;
		}
		CarList carList = new CarList();
		try{
			BufferedReader dbReader = new BufferedReader(new FileReader(dataBase));
			BufferedWriter dbWriter = new BufferedWriter(new FileWriter(destinationFileName));
			String eachLine = dbReader.readLine();
			while((eachLine = dbReader.readLine())!= null){
				String[] lineRead = eachLine.split(",");
				if(lineRead[5].equals("Hydrogen") || lineRead[5].equals("Electricity")){
					GreenCar greenCar = new GreenCar(
							lineRead[0],
							lineRead[10],
							Integer.parseInt(lineRead[11]),
							GreenCar.TYPE_NAME,
							lineRead[5]);
					carList.addCar(greenCar);
				}
				else{
					int index = lineRead[14].indexOf('/');
					String filteredMpg = lineRead[14];
					if (index != -1) {
						filteredMpg = filteredMpg.substring(0, index);
					}
					GasCar gasCar = new GasCar(
							lineRead[0],
							lineRead[10],
							Integer.parseInt(lineRead[11]),
							GasCar.TYPE_NAME,
							Integer.parseInt(lineRead[2]),
							Integer.parseInt(filteredMpg));
					carList.addCar(gasCar);
				}
			}

			dbWriter.write("All Cars:\n");
			dbWriter.write(carList.toString() + "\n");
			dbWriter.write("Green Cars - Fuel Type:\n");
			dbWriter.write(carList.toStringGreenCars() + "\n");
			dbWriter.write(String.format("Average MPG All: %.2f\n\n", carList.avgMpg()));
			dbWriter.write(String.format("Average MPG Subaru: %.2f\n\n", carList.avgMpgByPartialModel("SUBARU")));
			dbWriter.write(String.format("Average MPG Toyota: %.2f\n\n", carList.avgMpgByPartialModel("TOYOTA")));
			dbWriter.write(String.format("Average MPG Ferrari: %.2f\n\n", carList.avgMpgByPartialModel("FERRARI")));
			dbWriter.write("Vehicle Classes with 4-Cylinder Cars:\n");
			for(String str: carList.findClassesByCylinders(4)){
				dbWriter.write("\t" + str + "\n");
			}
			dbWriter.write("Vehicle Classes with 6-Cylinder Cars:\n");
			for(String str: carList.findClassesByCylinders(6)){
				dbWriter.write("\t" + str + "\n");
			}
			dbWriter.write("Small SUVs with MPG > 22:\n");
			for(String str: carList.findModelsByClassAndMpg("small SUV",22)){
				dbWriter.write("\t" + str + "\n");
			}
			dbWriter.write("Small Cars with MPG > 35:\n");
			for(String str: carList.findModelsByClassAndMpg("small car",35)){
				dbWriter.write("\t" + str + "\n");
			}
			dbWriter.close();

		}catch (Exception ex){
			ex.printStackTrace();
			return;
		}







		//Suggested logic:
		
		/*
		 * 1. Build a CarList object from the file specified by the second command argument.
		 * 2. Output "All Cars:" followed by a newline.
		 * 3. Output the result of calling toString on the CarList.
		 * 4. Output "Green Cars - Fuel Type:" followed by a newline.
		 * 5. Output the result of calling toStringGreenCars on the CarList.
		 * 6. Output "Average MPG All:" followed by the result of calling avgMpg on the CarList 
		 * 	   followed by a newline. The result should be displayed with exactly two digits 
		 *     after the decimal point.
		 * 7. Output "Average MPG Subaru:" followed by the result of calling avgMpgByPartialModel("SUBARU") 
		 *     on the CarList followed by a newline. The result should be displayed with exactly two digits 
		 *     after the decimal point.
		 * 8. Output "Average MPG Toyota:" followed by the result of calling avgMpgByPartialModel("TOYOTA") 
		 *     on the CarList followed by a newline. The result should be displayed with exactly two digits 
		 *     after the decimal point.
		 * 9. Output "Average MPG Ferrari:" followed by the result of calling avgMpgByPartialModel("FERRARI") 
		 *     on the CarList followed by a newline. The result should be displayed with exactly two digits 
		 *     after the decimal point.
		 * 10. Output "Vehicle Classes with 4-Cylinder Cars:" followed by a newline followed by the result of 
		 *     calling findClassesByCylinders(4) on the CarList. For each item in the list returned, output a 
		 *     tab followed by the item followed by a newline.
		 * 11. Output "Vehicle Classes with 6-Cylinder Cars:" followed by a newline followed by the result of 
		 *     calling findClassesByCylinders(6) on the CarList. For each item in the list returned, output a 
		 *     tab followed by the item followed by a newline.
		 * 12. Output "Small SUVs with MPG > 22:" followed by a newline followed by the result of calling 
		 *     findModelsByClassAndMpg("small SUV", 22) on the CarList. For each item in the list returned, 
		 *     output a tab followed by the item followed by a newline.
		 * 13. Output "Small Cars with MPG > 35:" followed by a newline followed by the result of calling 
		 *     findModelsByClassAndMpg("small car", 35) on the CarList. For each item in the list returned, 
		 *     output a tab followed by the item followed by a newline.
		 */
		
	}
}
