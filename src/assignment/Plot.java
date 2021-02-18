package assignment;

import javax.swing.*;

public class Plot extends JComponent {
    // dimensions of plotting area with default values
    int width = 1920, height = 1080;
    // dimensions of user-space coordinates with default values
    double xmin=0,  xmax=1, ymin=0, ymax=1;
    // transformation of coordinates
    public  int scaleX(double x) {
        x += 180; //Add 180 to every longitude

        if(x > 360){
            x -= 360;
        }

        return (int) (width * (x - xmin) / (xmax - xmin));
    }
    public  int scaleY(double y) {

        return (int) (height * (ymin - y)/(ymax - ymin)+height);
    }


    public  void setScaleX(double min, double max) {
        xmin = min;   xmax = max;   }
    public  void setScaleY(double min, double max) {
        ymin = min;   ymax = max; }

}


