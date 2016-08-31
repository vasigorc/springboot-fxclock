/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.vasigorc.clock;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

/**
 *
 * @author daniel liang
 */
@Component
public class ClockPane extends Pane {

    private int hour;
    private int minute;
    private int second;

    //Clock's pane width and hight
    private double w = 250, h = 250;

    //Construct a default Pane with the current time
    public ClockPane() {
        setCurrentTime();
    }

    public ClockPane(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        paintClock();
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
        paintClock();
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
        paintClock();
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
        paintClock();
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
        paintClock();
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
        paintClock();
    }

    private void setCurrentTime() {
        //Construct a Calendar for the current date and time
        Calendar calendar = new GregorianCalendar();

        //set current hour, minute and second
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);

        paintClock();
    }

    private void paintClock() {
        //Initialize clock parameters
        double clockRadius = Math.min(w, h) * 0.8 * 0.5;
        double centerX = w / 2;
        double centerY = h / 2;

        //Draw circle
        Circle circle = new Circle(centerX, centerY, clockRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Text t1 = new Text(centerX-7, centerY - clockRadius +25, "12");
        Text t2 = new Text(centerX - clockRadius + 15, centerY + 5, "9");
        Text t3 = new Text(centerX + clockRadius - 22, centerY + 5, "3");
        Text t4 = new Text(centerX-5, centerY + clockRadius - 14, "6");
        Text t5 = new Text(centerX + 36, centerY - clockRadius + 34, "1");
        Text t6 = new Text(centerX + clockRadius - 34, centerY -34, "2");
        Text t7 = new Text(centerX + clockRadius - 34, centerY +44, "4");
        Text t8 = new Text(centerX + 36, centerY + clockRadius - 25, "5");
        Text t9 = new Text(centerX -44, centerY + clockRadius - 25, "7");
        Text t10 = new Text(centerX - clockRadius + 26, centerY +44, "8");
        Text t11 = new Text(centerX - clockRadius + 26, centerY -34, "10");
        Text t12 = new Text(centerX -43, centerY - clockRadius + 36, "11");
        //Draw clock hashes
        Line[] clockHashes = new Line[60];//60 minutes

        for (int i = 0; i < 60; i++) {
            double hashL;
            if (i % 5 == 0) {
                hashL = clockRadius * 0.12;
            } else {
                hashL = clockRadius * 0.07;
            }
            double startX = centerX + (clockRadius - hashL) * Math.sin(i * (2 * Math.PI / 60));
            double startY = centerY - (clockRadius - hashL) * Math.cos(i * (2 * Math.PI / 60));
            double endX = centerX + clockRadius * Math.sin(i * (2 * Math.PI / 60));
            double endY = centerY - clockRadius * Math.cos(i * (2 * Math.PI / 60));
            Line hashLine = new Line(startX, startY, endX, endY);
            hashLine.setStroke(Color.BLACK);
            clockHashes[i] = hashLine;
        }

        //Draw seconds hand
        double sLength = clockRadius * 0.8;
        double secondX = centerX + sLength * Math.sin(second * (2 * Math.PI / 60));
        double secondY = centerY - sLength * Math.cos(second * (2 * Math.PI / 60));
        Line sLine = new Line(centerX, centerY, secondX, secondY);
        sLine.setStroke(Color.RED);

        //Draw minute hand
        double mLength = clockRadius * 0.65;
        double xMinute = centerX + mLength * Math.sin(minute * (2 * Math.PI / 60));
        double minuteY = centerY - mLength * Math.cos(minute * (2 * Math.PI / 60));
        Line mLine = new Line(centerX, centerY, xMinute, minuteY);
        mLine.setStroke(Color.BLUE);

        //Draw hour hand
        double hLength = clockRadius * 0.5;
        double hourX = centerX + hLength * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        double hourY = centerY - hLength * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        Line hLine = new Line(centerX, centerY, hourX, hourY);
        mLine.setStroke(Color.GREEN);

        getChildren().clear();
        getChildren().addAll(circle, t1, t2, t3, t4, t5, t6, t7, 
                t8, t9, t10, t11, t12, sLine, mLine, hLine);
        getChildren().addAll(Arrays.asList(clockHashes));
    }
}
