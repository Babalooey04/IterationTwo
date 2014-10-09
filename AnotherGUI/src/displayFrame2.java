import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class displayFrame2 extends JFrame{

    final JPanel listPanel;
    JTextField field;
    JLabel recipe_name;
	JLabel recipe_ingredients;
	JLabel recipe_directions;
	JLabel listLbl;
	JButton saveButton;

	public displayFrame2(){

		//settings for the frame
		setTitle("Add Recipe");
		setPreferredSize(new Dimension(300, 300));setSize(300,300);
        setBackground(Color.gray);

        //creating things to go in frame
        field = new JTextField(1000);
        recipe_name = new JLabel("Recipe name: ");
		recipe_ingredients = new JLabel("Ingredients: ");
		recipe_directions = new JLabel("Directions: ");
		listLbl = new JLabel("Add");
		saveButton = new JButton("SAVE");
		listPanel = new JPanel();
		String[] addOptions = {"Tsting", "One", "Two"};
		JList options = new JList(addOptions);

		//adding things to frame
		add(field, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
        add(recipe_name, BorderLayout.WEST);
        add(recipe_ingredients, BorderLayout.WEST);
        add(recipe_directions, BorderLayout.WEST);
        add(saveButton, BorderLayout.SOUTH);

        listPanel.add(listLbl);
		listPanel.add(options);

		pack();
        setVisible(false);
	}


	public void showFrame(){
		setVisible(!isVisible());
	}
}
