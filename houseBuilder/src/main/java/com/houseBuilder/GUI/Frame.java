package com.houseBuilder.GUI;

import com.houseBuilder.House;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Frame extends JFrame implements ActionListener {

    Panel panel;
    java.awt.Panel upperPanel;
    JButton planButton;
    JButton buildButton;
    JButton destroyButton;
    private static House selectedHouse;
    private static int selectedAreaIndex;
    private static boolean areaSelected;
    private static int paintMode = 0;
    private static boolean repainted;

    public Frame(){

        panel = new Panel();

        this.setContentPane(new JLabel(new ImageIcon("C:\\Users\\vanya\\IdeaProjects\\houseBuilder\\src\\texture\\background.png")));

        this.setLayout(new BorderLayout());
        upperPanel = new java.awt.Panel();

        planButton = new JButton("House Plan");
        planButton.addActionListener(this);
        buildButton = new JButton("Build");
        buildButton.addActionListener(this);
        destroyButton = new JButton("Destroy");
        destroyButton.addActionListener(this);

        addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                checkTarget(x,y);
            }

        });

        Area.setDefaultAreas();

        panel.add(planButton);
        panel.add(buildButton);
        panel.add(destroyButton);

        this.add(panel,BorderLayout.CENTER);
        this.add(upperPanel,BorderLayout.NORTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == planButton){
            try {
                PopWindow window = new PopWindow();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } else if (e.getSource() == buildButton){
            Frame.setPaintMode(1);
            panel.paintComponent(getGraphics());
            if(!getRepainted()){
                Area.moveAreas(Area.getAreas(),1);
            }
        } else if (e.getSource() == destroyButton){
            Frame.setPaintMode(2);
            panel.paintComponent(getGraphics());
            if(!getRepainted()){
                Area.moveAreas(Area.getAreas(),2);
            }
            repaint();
        }
    }

    public static void checkTarget(int x, int y){
        Area[] areas =  Area.getAreas();
        for(int i = 0; i < areas.length; i++){
            if(areas[i].isHit(x,y)){
                setSelectedAreaIndex(i);
                setAreaSelected(true);
            }
        }
    }

    public static House getSelectedHouse() {
        return selectedHouse;
    }

    public static void setSelectedHouse(House house) {
        selectedHouse = house;
    }

    public static void setAreaSelected(boolean areaSelected) {
        Frame.areaSelected = areaSelected;
    }

    public static boolean isAreaSelected() {
        return areaSelected;
    }

    public static void setSelectedAreaIndex(int index) {
        selectedAreaIndex = index;
    }

    public static int getSelectedAreaIndex() {
        return selectedAreaIndex;
    }

    public static void setRepainted(boolean repainted) {
        Frame.repainted = repainted;
    }

    public static boolean getRepainted(){
        return Frame.repainted;
    }

    public static int getPaintMode(){
        return paintMode;
    }

    public static void setPaintMode(int value) {
        Frame.paintMode = value;
    }
}
