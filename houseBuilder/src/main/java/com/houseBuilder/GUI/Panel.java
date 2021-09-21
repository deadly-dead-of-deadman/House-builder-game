package com.houseBuilder.GUI;

import com.houseBuilder.House;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

public class Panel extends JPanel {

    Panel() {
    }

    public void paintComponent(Graphics g) {
        try {
            House.drawHouses(g);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


}
