package astartest;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michael Kramer
 */
public class AStarUI extends JFrame {
    private int gridWidth = 0;
    private int gridHeight = 0;
    
    private Point startPoint;
    private Point endPoint;
    
    JPanel[][] panelArray ;
            
    
    
    public AStarUI(AStarController parentController) {
        this.gridHeight = parentController.getGridHeight();
        this.gridWidth = parentController.getGridWidth();
        this.startPoint = parentController.getStartPoint();
        this.endPoint = parentController.getEndPoint();
        this.panelArray = parentController.getPanelArray();
        configureWindow();
        addComponents();
    }
    
    private void configureWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        for(int i =0; i < gridHeight; i ++) {
            for(int j =0; j < gridWidth; j++) {
                JPanel spacePanel = new JPanel();   
                
                c.gridx = j;
                c.gridy = i;
                c.fill = GridBagConstraints.BOTH;
                c.weightx = 1;
                c.weighty = 1;      

                panelArray[i][j] = spacePanel;
                add(spacePanel, c);
            }
        }
    }
}
