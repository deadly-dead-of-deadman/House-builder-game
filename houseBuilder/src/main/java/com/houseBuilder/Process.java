package com.houseBuilder;

import com.houseBuilder.houseParts.HousePart;
import com.houseBuilder.storage.DataStorage;


import java.sql.SQLException;
import java.util.ArrayList;

public class Process {

    public static int build(House house) throws SQLException {
        ArrayList<HousePart> Plan = house.getPlan();
        int build_time = 0;
        for (int i = 0; i < Plan.size(); i++) {
            HousePart house_part = Plan.get(i);
            int workers = DataStorage.countWorkersFromDB(Plan.get(i).getType());
            build_time += house_part.getTime();
        }
        return build_time;
    }

}
