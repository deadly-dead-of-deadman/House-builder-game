package com.houseBuilder.GUI;

import com.houseBuilder.House;
import com.houseBuilder.storage.DataStorage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PopWindow extends JFrame implements ActionListener {
    JFrame frame = new JFrame();
    JComboBox<String> roof;
    JComboBox<String> walls;
    JComboBox<String> foundation;
    JTextField floor = new JTextField("1");
    JCheckBox bPresense = new JCheckBox("Basement");
    JLabel label1 = new JLabel("Roof");
    JLabel label2 = new JLabel("Walls");
    JLabel label3 = new JLabel("Foundation");
    JLabel label4 = new JLabel("Floors(max 3)");
    JButton submit = new JButton("Submit");
    String[] RoofTypes;
    String[] WallsTypes;
    String[] FoundationTypes;

    PopWindow() throws SQLException {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400,300);
        frame.setLayout(null);

        label1.setBounds(50,15,100,50);
        label2.setBounds(50,115,100,50);
        label3.setBounds(150,15,100,50);
        label4.setBounds(250,15,100,50);

        floor.setBounds(250,50,100,50);

        submit.setBounds(250,150,100,50);

        RoofTypes = DataStorage.getMaterialsFromDB("Roof");
        WallsTypes = DataStorage.getMaterialsFromDB("Walls");
        FoundationTypes = DataStorage.getMaterialsFromDB("Foundation");
        roof = new JComboBox<>( RoofTypes);
        walls = new JComboBox<>( WallsTypes);
        foundation = new JComboBox<>( FoundationTypes);

        roof.addActionListener(this);
        walls.addActionListener(this);
        foundation.addActionListener(this);
        bPresense.addActionListener(this);
        submit.addActionListener(this);

        roof.setBounds(50,50,100,50);
        walls.setBounds(50,150,100,50);
        foundation.setBounds(150,50,100,50);
        bPresense.setBounds(150,150,100,50);

        frame.add(submit);
        frame.add(floor);
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
        frame.add(roof);
        frame.add(walls);
        frame.add(foundation);
        frame.add(bPresense);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == submit) {
        int floors = Integer.parseInt(floor.getText());
        House house = new House(floors, RoofTypes[roof.getSelectedIndex()], WallsTypes[walls.getSelectedIndex()], FoundationTypes[foundation.getSelectedIndex()], bPresense.isSelected());
        Frame.setSelectedHouse(house);
    }

    }
}
