package com.developer.rohithragav.anchor;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by ROHITH RAGAV on 9/8/2017.
 */

public class LinearLine {
    public static final Double p1 = -252.9;
    public static final Double p2 = 8.119*Math.pow(10,4);
    public static final Double p3 =  -9.775*Math.pow(10,6);
    public static final Double p4 =  5.231*Math.pow(10,8) ;
    public static final Double p5 =  -1.05*Math.pow(10,10);

    public static String getDistance(Double lan,Double lat){
        Double x=lan;
        Double y=lat;
        Double distance = p1*Math.pow(x,4) + p2*Math.pow(x,3) + p3*Math.pow(x,2) + p4*x + p5 - y;
        distance = distance/1000f;
        NumberFormat format = new DecimalFormat("#00.00000");
        String dis  = format.format(distance);
        return dis;
    }
    public static Double getLatDistance(){ return 12.838554; }
    public static Double getLanDistance(){ return 80.154376; }
}
