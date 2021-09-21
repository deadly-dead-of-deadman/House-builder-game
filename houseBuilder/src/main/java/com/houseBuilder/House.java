package com.houseBuilder;

import com.houseBuilder.GUI.Area;
import com.houseBuilder.GUI.Frame;
import com.houseBuilder.GUI.Texture;
import com.houseBuilder.houseParts.*;
import com.houseBuilder.storage.DataStorage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class House implements Storable{

    private int time;
    private int floors;
    private ArrayList<HousePart> plan = new ArrayList<>();
    public Basement basement = new Basement();
    public Foundation foundation = new Foundation();
    public Walls walls = new Walls();
    public Roof roof = new Roof();
    private static House[] houses = new House[Area.getAreas().length];

    public House( int floors, String roofMaterials, String wallsMaterials, String foundationMaterials, boolean bPresence) {
        this.floors = floors;
        this.roof = new Roof();
        this.roof.setMaterials(roofMaterials);
        this.walls = new Walls();
        this.walls.setMaterials(wallsMaterials);
        this.foundation = new Foundation();
        this.foundation.setMaterials(foundationMaterials);
        this.basement.setPresence(bPresence);
    }


    public Foundation getFoundation() {
        return foundation;
    }

    public Roof getRoof() {
        return roof;
    }

    public Walls getWalls() {
        return walls;
    }

    public int getTime() {
        return time;
    }

    public int getFloors() {
        return floors;
    }

    public int getBasement(){
        if(basement.getPresence()){
            return 1;
        } else{
            return 0;
        }
    }

    public ArrayList<HousePart> getPlan() {
        plan.add(foundation);
        if (basement.getPresence()) {
            plan.add(basement);
        }
        for (int i = 0; i < floors; i++) {
            plan.add(walls);
        }
        plan.add(roof);

        return plan;
    }

    @Override
    public Long getId() {
        Long id = new Date().getTime();
        return id;
    }

    @Override
    public void setId(Long id) {
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public static void drawHouse(House house,int areaIndex, Graphics g) throws SQLException, IOException {
        DataStorage.saveHouse(house,areaIndex);

        int x = Area.getAreas()[areaIndex].getXCord();
        int y = Area.getAreas()[areaIndex].getYCord();

        String wallsURL = Texture.getTexture("Walls", house.getWalls().getMaterials(), 1);
        String walls2URL = Texture.getTexture("Walls", house.getWalls().getMaterials(), 2);
        String roofURL = Texture.getTexture("Roof",house.getRoof().getMaterials(), 3);
        String roofWURL = Texture.getTexture("Walls",house.getWalls().getMaterials(),3);

        Image wallsImage = Toolkit.getDefaultToolkit().getImage(wallsURL);
        Image roofImage = Toolkit.getDefaultToolkit().getImage(roofURL);
        Image roofWImage = Toolkit.getDefaultToolkit().getImage(roofWURL);
        Image walls2Image = Toolkit.getDefaultToolkit().getImage(walls2URL);

        if(house.getFloors() == 1){
            g.drawImage(roofWImage,x-42,y-190,300,200,null);
            g.drawImage(roofImage,x-40,y-210,300,200,null);

        } else if (house.getFloors() == 2){
            g.drawImage(walls2Image,x-40,y-190,300,200,null);
            g.drawImage(roofWImage,x-42,y-285,300,200,null);
            g.drawImage(roofImage,x-40,y-300,300,200,null);

        } else if (house.getFloors() >= 3){
            g.drawImage(walls2Image,x-40,y-190,300,200,null);
            g.drawImage(walls2Image,x-40,y-280,300,200,null);
            g.drawImage(roofWImage,x-42,y-375,300,200,null);
            g.drawImage(roofImage,x-40,y-390,300,200,null);
        }
        g.drawImage(wallsImage,x-40,y-100,300,200,null);
    }



    public static void drawHouses(Graphics g) throws SQLException {
        Area[] areas = Area.getAreas();

        if(Frame.getPaintMode() == 1 && houses[Frame.getSelectedAreaIndex()] == null) {
            houses[Frame.getSelectedAreaIndex()] = Frame.getSelectedHouse();
            Area.moveAreas(areas, com.houseBuilder.GUI.Frame.getPaintMode());
        } else if(Frame.getPaintMode() == 2){
            houses[Frame.getSelectedAreaIndex()] = null;
            Area.moveAreas(areas, com.houseBuilder.GUI.Frame.getPaintMode());
        }
        try {
            for(int i = 0; i < houses.length; i++) {
                if(!(houses[i] == null)){
                    House.drawHouse(houses[i],i,g);
                }
            }
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
        Frame.setRepainted(true);
    }
}
