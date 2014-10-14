import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class displayFrame2 extends JFrame{
    JLabel recipe_name;
	JLabel recipe_directions;
	JButton saveButton;
	private JLabel lblIngredients;
	private JLabel label;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JTextArea textArea_2;

	final DBConnector dbObject = new DBConnector();
	
	public displayFrame2(){
		getContentPane().setPreferredSize(new Dimension(300, 300));

		//settings for the frame
		setTitle("Add Recipe");
		setPreferredSize(new Dimension(600, 600));setSize(300,250);
        setBackground(Color.gray);
        recipe_name = new JLabel("Recipe name: ");
		String[] addOptions = {"Tsting", "One", "Two"};
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
        getContentPane().add(recipe_name);
        
        textArea = new JTextArea();
        textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        getContentPane().add(textArea);
        
        lblIngredients = new JLabel("Ingredients:");
        getContentPane().add(lblIngredients);
        
        textArea_1 = new JTextArea();
        textArea_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        textArea_1.setWrapStyleWord(true);
        textArea_1.setLineWrap(true);
        getContentPane().add(textArea_1);
        recipe_directions = new JLabel("Directions: ");
        getContentPane().add(recipe_directions);
        
        textArea_2 = new JTextArea();
        textArea_2.setWrapStyleWord(true);
        textArea_2.setLineWrap(true);
        textArea_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        getContentPane().add(textArea_2);
        
        label = new JLabel("");
        label.setBorder(null);
        getContentPane().add(label);
        saveButton = new JButton("SAVE");
        saveButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		dbObject.insert_IntoDB(textArea.getText(), textArea_1.getText(), textArea_2.getText());
        	}
        });
        getContentPane().add(saveButton);

		pack();
        setVisible(false);
	}


	public void showFrame(){
		setVisible(!isVisible());
	}
}
