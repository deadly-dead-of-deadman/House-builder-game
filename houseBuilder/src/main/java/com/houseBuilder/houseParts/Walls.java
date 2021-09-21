package com.houseBuilder.houseParts;

import com.houseBuilder.storage.DataStorage;

import java.sql.SQLException;

public class Walls extends HousePart {

    @Override
    public String getType() {
        return "Walls";
    }

    @Override
    public int getTime() throws SQLException {
        return time = DataStorage.getTimeFromDB(getType(),getMaterials());
    }

}

