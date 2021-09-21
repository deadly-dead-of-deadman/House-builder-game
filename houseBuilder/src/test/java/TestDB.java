import com.houseBuilder.House;
import com.houseBuilder.Process;
import com.houseBuilder.storage.DataStorage;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;


public class TestDB {

    @Test
    public void testGetMaterialsFromDB() throws SQLException {
        String[] materialsExpected = {"Corrugated board","Felt","Metal tile"};
        String[] materialsActual = DataStorage.getMaterialsFromDB("Roof");
        Assert.assertEquals(materialsExpected,materialsActual);
    }

    @Test
    public void testWorkersCountFromDB() throws SQLException {
        int workersCountExpected = 1;
        int workersCountActual = DataStorage.countWorkersFromDB("Basement");
        Assert.assertEquals(workersCountExpected,workersCountActual);
    }

    @Test
    public void testSaveHouseToDB() throws SQLException {
        House houseExpected = new House(1,"Felt","Brick","Pile",false);
        int areaIndex = 1;
        DataStorage.saveHouse(houseExpected,areaIndex);
        House houseActual = DataStorage.getHouseFromDB();

        Assert.assertEquals(houseExpected.getFloors(),houseActual.getFloors());
        Assert.assertEquals(houseExpected.getWalls().getMaterials(),houseActual.getWalls().getMaterials());
        Assert.assertEquals(houseExpected.getRoof().getMaterials(),houseActual.getRoof().getMaterials());
        Assert.assertEquals(houseExpected.getFoundation().getMaterials(),houseActual.getFoundation().getMaterials());
        Assert.assertEquals(houseExpected.getBasement(),houseActual.getBasement());

    }
}
