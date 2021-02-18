package assignment;


import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class PlotEarth extends Plot {
    Earth E;
    int blocksize = 1;
    double longitude = 360;
    double latitude = 90;
    double resolution = 0.1;
    //double banana = 0;

    public PlotEarth(String filename) throws FileNotFoundException {
        E = new Earth();
        E.readDataArray(filename);
        E.readDataMap(filename);

        setScaleX(0, 360);
        setScaleY(-90, 90);
    }


    public void risingSeaLevel(double sea) throws FileNotFoundException {
        /*System.out.println("Enter a value to increase the level of the sea: \n"); //In case you do it manually
        Scanner in = new Scanner(System.in);
        banana = in.nextDouble();*/

        for (Map.Entry<Double, TreeMap<Double,Double>> entry : E.getMapOfEarth().entrySet()) //Iterates through map and uses longitude as key
        {
            double key = entry.getKey();
            TreeMap<Double,Double> value = entry.getValue(); //Value is equal to the inside map
            for(Map.Entry<Double, Double> treeMap : value.entrySet()){ //Iterates through map(latitude and altitude values), and becomes the new tree map
                double rising = treeMap.getKey();
                double normal = treeMap.getValue();
                double rising1 = normal - sea; //Adds or subtracts the coordinates depending on the value entered by user

                if(E.getMapOfEarth().get(key) == null){
                    E.getMapOfEarth().put(key, new TreeMap<>());
                }
                else{
                    E.getMapOfEarth().get(key).put(rising, rising1);
                }
            }

        }
    }



    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;

        this.width = getWidth();
        this.height = getHeight();


        TreeMap<Double, TreeMap<Double, Double>> h = E.getMapOfEarth();

        for(double x = 0; x < longitude; x += resolution){ //iterates through longitude
            for(double y = -90; y < latitude; y += resolution){ //iterates through latitude
                double curX = h.floorKey(x); //sets longitude as key
                double curY = h.get(curX).floorKey(y); //uses longitude to get latitude
                double altColour = h.get(curX).get(curY); //uses longitude and latitude to get altitude

                if(altColour >= 0 && altColour <= 6000) {
                    double percentageAlt = (altColour / 60) * 2.55;

                    int land = (int) Math.round(percentageAlt);

                    if(land > 0 && land <= 7.5){
                        g2d.setColor(new Color(5, 64, 7));
                    }
                    else if(land > 7.5 && land <= 45){
                        g2d.setColor(new Color(7, 99, 24));
                    }
                    else if(land > 45 && land <= 140){
                        g2d.setColor(new Color(78, 82, 12));
                    }
                    else if(land > 140 && land <= 300){
                        g2d.setColor(new Color(130, 122, 94));
                    }
                    else{
                        g2d.setColor(new Color(14, 51, 21));
                    }
                }
                else if(altColour < 0){
                    altColour *= -1; //Makes altitude at sea level positive
                    double percentageAlt = (altColour / 60) * 2.55;

                    int sea = (int) Math.round(percentageAlt);

                    if(sea > 0 && sea <= 10){ //230
                        g2d.setColor(new Color(5, 109, 125));
                    }
                    else if(sea > 10 && sea <= 140){ //230
                        g2d.setColor(new Color(29, 92, 120));
                    }
                    else if(sea > 140 && sea <= 180){ //230
                        g2d.setColor(new Color(13, 70, 102));
                    }
                    else if(sea > 180 && sea <= 300){ //230
                        g2d.setColor(new Color(8, 54, 110));
                    }
                    else if(sea > 300 && sea <= 420){ //230
                        g2d.setColor(new Color(29, 46, 92));
                    }
                    else{
                        g2d.setColor(new Color(16, 37, 94));
                    }
                }
                else{
                    g2d.setColor(new Color(166, 155, 7));
                }

                g.fillRect(scaleX(x), scaleY(y), blocksize, blocksize);
            }
        }
    }
}

