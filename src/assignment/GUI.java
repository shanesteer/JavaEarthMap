package assignment;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class GUI extends PlotEarth{

    public GUI(String filename) throws FileNotFoundException {
        super(filename);
    }

    public static void main(String[] args) throws FileNotFoundException {
        double sea = 0;
        for(int x = 0; x < args.length; x++){ //Gets the level of sea rising from args
            String banana = args[x];

            sea = Double.parseDouble(banana);
        }

        JFrame jf = new JFrame("My Earth");
        jf.getContentPane().setPreferredSize(new Dimension(1920,1080));
        PlotEarth pe = new PlotEarth("./src/assignment/earth.xyz");
        pe.risingSeaLevel(sea);
        jf.add(pe);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
