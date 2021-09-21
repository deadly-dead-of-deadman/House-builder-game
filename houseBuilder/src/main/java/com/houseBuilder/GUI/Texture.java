package com.houseBuilder.GUI;

import com.houseBuilder.storage.DataStorage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Texture {

    public static String getTexture(String materialType, String material, int textureFloor) throws SQLException, IOException {

        String[] materials = DataStorage.getMaterialsFromDB(materialType);
        String url = "";

        if(materialType.equals("Roof")){
            for (int i = 0; i < materials.length - 1; i++) {
                if (materials[i].equals(material)) {
                    url = "C:\\Users\\vanya\\IdeaProjects\\houseBuilder\\src\\texture\\Roof" + material + ".png";
                }
            }
        } else if (materialType.equals("Walls")){
            if(textureFloor == 1 || textureFloor == 2) {
                for (int i = 0; i < materials.length; i++) {
                    if (materials[i].equals(material)) {
                        url = "C:\\Users\\vanya\\IdeaProjects\\houseBuilder\\src\\texture\\Walls" + textureFloor + material + ".png";
                    }
                }
            } else if(textureFloor == 3){
                for (int i = 0; i < materials.length; i++) {

                    if (materials[i].equals(material)) {
                        url = "C:\\Users\\vanya\\IdeaProjects\\houseBuilder\\src\\texture\\WallRoof" + material + ".png";
                    }
                }
            }
        }

        File temp = new File(url);
        if (!temp.exists()) {
            throw new IOException("Missing texture");
        }

        return url;
    }
}
