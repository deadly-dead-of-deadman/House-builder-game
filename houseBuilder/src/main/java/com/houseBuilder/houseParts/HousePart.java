package com.houseBuilder.houseParts;


import com.houseBuilder.storage.DataStorage;
import com.houseBuilder.workersAndEquip.Worker;

import java.sql.SQLException;

public abstract class HousePart {

    private String materials;
    private String type;
    private Worker worker = new Worker();

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Worker getWorker() {
        return worker;
    }

    public int time;

    public int getTime() throws SQLException {
        return time = DataStorage.getTimeFromDB(type, materials);
    }

    public void setTime(int time) {
        this.time = time;
    }

    public static boolean usedEquip() {
        return false;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getMaterials() {
        return materials;
    }

    public String getType(){return type;}


}
