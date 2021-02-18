package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;

public class Earth {
    private double[][] arrayOfEarth;
    private TreeMap<Double, TreeMap<Double,Double>> mapOfEarth;
    int lineCounter = 0;

    public void readDataArray(String filename) throws FileNotFoundException {
        Scanner input = new Scanner(new File(filename));

        while(input.hasNextLine()){
            lineCounter++;
            input.nextLine();
        }

        System.out.println(lineCounter);

        arrayOfEarth = new double [lineCounter][3];

        input.close();
        input = new Scanner(new File(filename));


        for (int i = 0; i<lineCounter;i++){
            String earthLine = input.nextLine();
            String[] earthData = earthLine.split("\t"); //split  data by tabs
            arrayOfEarth[i][0] = Double.parseDouble(earthData[0]);
            arrayOfEarth[i][1] = Double.parseDouble(earthData[1]);
            arrayOfEarth[i][2] = Double.parseDouble(earthData[2]);
        }

        input.close();
    }



    public List<Double> coordinatesAbove(double altitude){
            List<Double> coordinatesAbove = new ArrayList<>();
            for(int i = 0; i<arrayOfEarth.length; i++){
                coordinatesAbove.add(arrayOfEarth[i][2]); //goes through altitude in array
            }
        coordinatesAbove.removeIf(j -> j < altitude); //removes altitude if it is smaller than the altitude entered
        return coordinatesAbove;
    }

    public List<Double> coordinatesBelow(double altitude){
        List<Double> coordinatesBelow = new ArrayList<>();
        for(int i = 0; i<arrayOfEarth.length; i++){
            coordinatesBelow.add(arrayOfEarth[i][2]); //goes through altitude in array
        }
        coordinatesBelow.removeIf(j -> j > altitude); //removes altitude if it is bigger than the altitude entered
        return coordinatesBelow;
    }

    public void percentageAbove(double altitude){
        List<Double> amtCoAbove = coordinatesAbove(altitude);
        double totalCoAbove = amtCoAbove.size();
        DecimalFormat roundPercentage = new DecimalFormat("#.#"); //rounds coordinates to 1 decimal place
        double aboveCor = totalCoAbove / lineCounter * 100; //calculates percentage
        aboveCor = Double.parseDouble(roundPercentage.format(aboveCor));
        System.out.println("Proportion of coordinates above " + altitude + " meters: " + aboveCor + "%");

    }

    public void percentageBelow(double altitude){
        List<Double> amtCoBelow = coordinatesBelow(altitude);
        double totalCoBelow = amtCoBelow.size();
        DecimalFormat roundPercentage = new DecimalFormat("#.#"); //rounds coordinates to 1 decimal place
        double belowCor = totalCoBelow/lineCounter *100; //calculates percentage
        belowCor = Double.parseDouble(roundPercentage.format(belowCor));
        System.out.println("Proportion of coordinates below " + altitude + " meters: " + belowCor + "%");
    }


    public void readDataMap(String filename) throws FileNotFoundException{
        Scanner input = new Scanner(new File(filename));
        mapOfEarth = new TreeMap<>();

        double longitude, latitude, altitude;

        while(input.hasNextLine()){
            String earthLine = input.nextLine();
            String[] earthData = earthLine.split("\t"); //split  data by tabs

            longitude = Double.parseDouble(earthData[0]);
            latitude = Double.parseDouble(earthData[1]);
            altitude = Double.parseDouble(earthData[2]);


            if(mapOfEarth.get(longitude) == null){
                mapOfEarth.put(longitude, new TreeMap<>());
                mapOfEarth.get(longitude).put(latitude, altitude);
            }
            else{
                mapOfEarth.get(longitude).put(latitude, altitude);
            }
        }
        input.close();
    }

    public void generateMap(double resolution){
        if(!mapOfEarth.isEmpty()){
            mapOfEarth.clear();
        }
        Random rnd = new Random();

        DecimalFormat decimalRounder = new DecimalFormat("#");
        int longi = Integer.parseInt(decimalRounder.format(360 * (1.0 / resolution)));
        int lati = Integer.parseInt(decimalRounder.format(180 * (1.0 / resolution)));
        int alti = longi * lati;

        for(int i = 0; i < longi; i++){
            double longitude = rnd.nextInt(longi); //Random longitude

            if(mapOfEarth.containsKey(longi)){ //If key exists, continue loop
                i--; //If longitude can't be put in map, i counter still increases
                continue;
            }else{
                mapOfEarth.put(longitude, new TreeMap<>()); //Add longitude to map
            }

            for(int j = 0; j < lati; j++){
                double latitude = rnd.nextInt(lati); //Random latitude

                TreeMap<Double, Double> x = mapOfEarth.get(longi);

                if(x.containsKey(latitude)){
                    j--; //If latitude can't be put in map, j counter still increases
                    continue;
                }else{
                    double altitude = rnd.nextInt(alti); //Random altitude
                    mapOfEarth.get(longitude).put(latitude,altitude); //Implement map
                }

            }

        }
    }

    public double getAltitude(double longitude, double latitude){
        double altitude = mapOfEarth.get(longitude).get(latitude);
        System.out.println("The altitude at longitude " + longitude + " and latitude " + latitude + " is " + altitude + " meters.");
        return altitude;
    }

    public TreeMap<Double, TreeMap<Double, Double>> getMapOfEarth() {
        return mapOfEarth;
    }

}
