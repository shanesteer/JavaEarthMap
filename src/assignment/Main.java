package assignment;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Earth E = new Earth();
        E.readDataArray("./src/assignment/earth.xyz");
        E.readDataMap("./src/assignment/earth.xyz");
        //System.out.println(Arrays.deepToString(E.arrayOfEarth));
        //System.out.println(E.getMapOfEarth());
        //System.out.println(E.coordinatesAbove(6000));
        //System.out.println(E.coordinatesBelow(-6000));


            String selector;
            String selector1;
            String longi1;
            String longi2;
            String lati1;
            String lati2;
            double altitude = 0;
            double longitude = 0;
            double latitude = 0;
            double longitude1 = 0;
            double longitude2 = 0;
            double latitude1 = 0;
            double latitude2 = 0;


            Scanner input = new Scanner(System.in);
            while (true) {
                System.out.println("Enter 1 to find proportions of coordinates above a certain altitude.");
                System.out.println("Enter 2 to find proportions of coordinates below a certain altitude.");
                System.out.println("Enter 3 to find altitude based on a certain longitude and latitude.");
                System.out.println("Enter 4 to find distance between 2 points of the Earth.");
                System.out.println("Enter \"quit\" to end the program.");
                System.out.print("Please enter 1, 2, 3, 4 or \"quit\" to end the program: ");

                selector = input.nextLine().toLowerCase();


                if (selector.equals("quit")) {
                    System.out.println("Bye!");
                    break;
                } else if (selector.equals("1")) {
                    System.out.print("Please enter an altitude: \n");
                    Scanner aboveProportion = new Scanner(System.in);
                    try {
                        altitude = aboveProportion.nextDouble();
                        E.percentageAbove(altitude);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid altitude. Please enter a valid altitude or \"quit\" to end the program.");
                        continue;
                    }
                } else if (selector.equals("2")) {
                    System.out.print("Please enter an altitude: \n");
                    Scanner belowProportion = new Scanner(System.in);
                    try {
                        altitude = belowProportion.nextDouble();
                        E.percentageBelow(altitude);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid altitude. Please enter a valid altitude or \"quit\" to end the program.");
                        continue;
                    }
                } else if (selector.equals("3")) {
                    while (true) {
                        System.out.print("Please enter a longitude (0-360) and latitude (-90-90): \n");
                        Scanner coordinates = new Scanner(System.in);
                        try {
                            longitude = coordinates.nextDouble();
                            latitude = coordinates.nextDouble();
                            altitude = E.getAltitude(longitude, latitude);

                        } catch (Exception e) {
                            System.out.println("Please enter valid longitude/latitude.");
                            continue;
                        }

                        System.out.print("Enter 1 for percentage \n");
                        System.out.print("Enter 2 for altitude \n");
                        System.out.print("Enter \"quit\" to end the program \n");
                        System.out.print("Please enter 1, 2 or \"quit\" to end program: ");
                        Scanner in = new Scanner(System.in);
                        selector1 = in.nextLine().toLowerCase();

                        if (selector1.equals("1")) {
                            E.percentageAbove(altitude);
                            E.percentageBelow(altitude);
                            break;
                        }

                        if (selector1.equals("2")) {
                            continue;
                        }

                        if (selector1.equals("quit")) {
                            System.out.println("Bye!");
                            break;
                        }

                    }

                }
                else if(selector.equals("4")){
                    Scanner coordinatesDistance = new Scanner(System.in);
                    System.out.print("Enter longitude 1: \n");
                    longi1 = coordinatesDistance.nextLine();
                    System.out.print("Enter latitude 1: \n");
                    lati1 = coordinatesDistance.nextLine();
                    System.out.print("Enter longitude 2: \n");
                    longi2 = coordinatesDistance.nextLine();
                    System.out.print("Enter latitude 2: \n");
                    lati2 = coordinatesDistance.nextLine();

                    longitude1 = Double.parseDouble(longi1);
                    latitude1 = Double.parseDouble(lati1);
                    longitude2 = Double.parseDouble(longi2);
                    latitude2 = Double.parseDouble(lati2);

                    MapCoordinate mapCoord = new MapCoordinate();
                    System.out.println(mapCoord.mapCoordinates(longitude1, latitude1, longitude2, latitude2) + " km");

                }else {
                    System.out.println("Please enter 1, 2, 3, 4 or \"quit\" to end program");
                    continue;
                }

                break;

            }

    }
}
