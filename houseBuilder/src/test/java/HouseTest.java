import com.houseBuilder.*;
import com.houseBuilder.houseParts.HousePart;
import com.houseBuilder.storage.DataStorage;
import org.junit.*;
import com.houseBuilder.Process;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class HouseTest {

    @Test
    public void testBuildingProcess() throws SQLException {
        int floors = 2;
        House myHouse = new House( floors, "Corrugated board", "Brick", "Plate", true);
        Assert.assertEquals(220, Process.build(myHouse));

    }


    @Test
    public void testHousePlanComposition() {
        int floors = 1;
        House myHouse = new House(floors, "Felt", "Concrete", "Strip", false);

        ArrayList<HousePart> planB = new ArrayList<>();

        planB.add(myHouse.foundation);
        if (myHouse.basement.getPresence()) {
            planB.add(myHouse.basement);
        }
        for (int i = 0; i < floors; i++) {
            planB.add(myHouse.walls);
        }
        planB.add(myHouse.roof);

        Assert.assertEquals(planB, myHouse.getPlan());
    }


}
