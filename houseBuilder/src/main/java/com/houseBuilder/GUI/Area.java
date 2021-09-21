package com.houseBuilder.GUI;

import java.awt.*;

public class Area extends Rectangle {
    private int width = 220;
    private int height = 100;
    private int x;
    private int y;
    private static Area[] areas = new Area[5];

    public Area(int X, int Y){
        this.x = X;
        this.y = Y;
    }

    @Override
    public boolean contains(Point p) {
        return super.contains(p);
    }


    public int getXCord() {
        return x;
    }


    public int getYCord() {
        return y;
    }

    public void drawArea(Graphics g){
        g.drawRect(getXCord(), getYCord(),width,height);
        g.setColor(Color.darkGray);
        g.fillRect(getXCord(), getYCord(),width,height);
    }

    public boolean isHit(int x2, int y2){
        Point point = new Point(x2,y2);
        Rectangle bounds = new Rectangle(x, y, width, height);
        if(bounds.contains(point)){
            return true;
        } else {
            return false;
        }
    }

    public static Area[] getAreas(){
        return areas;
    }

    public static void setAreas(Area[] newAreas){
        areas = newAreas;
    }

    public static void setDefaultAreas(){
        Area[] defaultAreas = new Area[5];
        defaultAreas[0] = new Area(150,190);
        defaultAreas[1] = new Area(685,190);
        defaultAreas[2] = new Area(1200,190);
        defaultAreas[3] = new Area(155,570);
        defaultAreas[4] = new Area(685,570);
        areas = defaultAreas;
    }
    public static void moveAreas(Area[] oldAreas, int moveMode){
        int dx = 0;
        int dy = 0;
        if(moveMode == 1) {
            dx = 7;
            dy = 40;
        }
            oldAreas[0] = new Area(150 + dx, 190 + dy);
            oldAreas[1] = new Area(685 + dx, 190 + dy);
            oldAreas[2] = new Area(1200 + dx, 190 + dy);
            oldAreas[3] = new Area(155 + dx, 570 + dy);
            oldAreas[4] = new Area(685 + dx, 570 + dy);
        Area.setAreas(oldAreas);
    }

}
