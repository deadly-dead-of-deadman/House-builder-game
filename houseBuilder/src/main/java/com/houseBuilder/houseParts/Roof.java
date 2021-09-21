package com.houseBuilder.houseParts;

import java.sql.SQLException;

public class Roof extends HousePart {

    @Override
    public String getType() {
        return "Roof";
    }

    @Override
    public int getTime() throws SQLException {
        return time;
    }

}
