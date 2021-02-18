package assignment;

public class MapCoordinate {

    public double mapCoordinates(final double longitude1, final double latitude1, final double longitude2, final double latitude2){

        //Converts longitude and latitude entered by user to radians
        double longi1 = Math.toRadians(longitude1);
        double lati1 = Math.toRadians(latitude1);
        double longi2 = Math.toRadians(longitude2);
        double lati2 = Math.toRadians(latitude2);

        double distanceLongitude = longi2 - longi1;
        double distanceLatitude = lati2 - lati1;

        //Formula obtained from https://www.movable-type.co.uk/scripts/latlong.html
        //Haversine Formula
        double distanceFormula = Math.pow(Math.sin(distanceLatitude / 2), 2) + Math.cos(lati1) * Math.cos(lati2) * Math.pow(Math.sin(distanceLongitude / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(distanceFormula));
        double earthRadius = 6371;

        return Math.round(c * earthRadius);
    }
}
