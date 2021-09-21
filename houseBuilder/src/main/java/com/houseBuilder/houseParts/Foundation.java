package com.houseBuilder.houseParts;


import java.sql.SQLException;

public class Foundation extends HousePart {

    @Override
    public String getType() {
        return "Foundation";
    }

    @Override
    public int getTime() throws SQLException {
        return time;
    }

}
