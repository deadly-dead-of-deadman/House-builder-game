package com.houseBuilder.storage;

import com.houseBuilder.House;

import java.sql.*;

public class DataStorage {

    public static House saveHouse(House house, int area) {
        try {
            return saveToDb(house, area);
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot save house", exception);
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:file:C:\\Users\\vanya\\IdeaProjects\\houseBuilder\\DB\\houseDB;" +
                "MV_STORE=false", "user", "password");
    }

    private static House saveToDb(House house, int area) throws SQLException {
        try (Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("insert into builthouses ( id ,area_id, roof, walls, foundation, floors, basement) values (?, ?, ?, ?, ?, ?, ?)");
            statement.setLong(1,house.getId());
            statement.setInt(2, area);
            statement.setString(3, house.getRoof().getMaterials());
            statement.setString(4, house.getWalls().getMaterials());
            statement.setString(5, house.getFoundation().getMaterials());
            statement.setInt(6, house.getFloors());
            statement.setInt(7, house.getBasement());
            statement.executeUpdate();
        }
        return house;
    }

    public static House getHouseFromDB() {
        try (Connection connection = getConnection();) {
            PreparedStatement statement = connection.prepareStatement("select id ,area_id, roof, walls, foundation, floors, basement from builthouses");
            ResultSet rs = statement.executeQuery();
            rs.last();
            int area = rs.getInt(2);
            String roof = rs.getString(3);
            String walls = rs.getString(4);
            String foundation = rs.getString(5);
            int floors = rs.getInt(6);
            boolean basement = rs.getBoolean(7);
            return new House(floors,roof,walls,foundation,basement);
        } catch (SQLException exception) {
            throw new RuntimeException("Cannot load house", exception);
        }
    }

    public static String[] getMaterialsFromDB(String materialType) throws SQLException {
        String[] materialTypes;
        int i = 0;
        try (Connection connection = getConnection();) {
            Statement statement = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = statement.executeQuery("select id, name, time, type from materials");
            rs.next();

            while(!rs.isAfterLast()){
                if(rs.getString(4).equals(materialType)){
                    i++;
                }
                rs.next();
            }
            rs.first();
            materialTypes = new String[i];
            i=0;

            while(!rs.isAfterLast()){
                if(rs.getString(4).equals(materialType)){
                    materialTypes[i] = rs.getString(2);
                    i++;
                }
                rs.next();
            }

        } catch (SQLException exception) {
            throw new RuntimeException("Cannot get materials", exception);
        }
        return materialTypes;
    }


    public static int countWorkersFromDB(String housePart) {
        int workersCount = 0;

        try (Connection connection = getConnection();) {
            Statement statement = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = statement.executeQuery("select id, name, house_part, profession from workers");
            rs.next();

            while(!rs.isAfterLast()){
                if(rs.getString(3).equals(housePart)){
                    workersCount++;
                }
                rs.next();
            }

        } catch (SQLException exception) {
            throw new RuntimeException("Cannot count workers", exception);
        }
        return workersCount;
    }

    public  static int getTimeFromDB(String housePart, String material){
        int time = 0;
        int i = 0;
        try (Connection connection = getConnection();) {
            Statement statement = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = statement.executeQuery("select id, name, time, type from materials");
            rs.next();

            while(!rs.isAfterLast()){
                if(rs.getString(2).equals(material) && rs.getString(4).equals(housePart)){
                    time = rs.getInt(3);
                }
                rs.next();
            }

        } catch (SQLException exception) {
            throw new RuntimeException("Cannot get time", exception);
        }
        return time;
    }
}
