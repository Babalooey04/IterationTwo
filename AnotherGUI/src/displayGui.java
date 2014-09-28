import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class displayGui extends JFrame{
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel bottomrightPanel;
    private JPanel toprightPanel;
    private JPanel btnPanel;
    private JScrollPane scrollPane;
    final JPanel listPanel;

    @SuppressWarnings("unchecked")
	public displayGui(JTable tbl){
    	
    	// Need a Database object
    	DBConnector dbObject = new DBConnector();
    	dbObject.get_DBinfo();
    	
        setTitle("My Recipes");
        setSize(600,600);
        setBackground(Color.gray);

      //1. Create another frame.
        final JFrame frame2 = new JFrame("Add Recipe");
        frame2.setPreferredSize(new Dimension(300, 300));
        
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomrightPanel = new JPanel();
        toprightPanel = new JPanel();
        btnPanel = new JPanel();
        listPanel = new JPanel(); 
        JTextField field = new JTextField(20);
        frame2.add(field, BorderLayout.NORTH);
        
        btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Recipe Name"));
        leftPanel.setPreferredSize(new Dimension(200,300));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BorderLayout() );
        //rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 170, 0));
        toprightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 300, 200));
        toprightPanel.setBorder(BorderFactory.createTitledBorder("Recipe Description"));
        toprightPanel.setBackground(Color.WHITE);
        bottomrightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 200, 200));
        bottomrightPanel.setBorder(BorderFactory.createTitledBorder("Recipe Ingredients"));
        bottomrightPanel.setBackground(Color.WHITE);
        bottomrightPanel.setPreferredSize(new Dimension(0,200));
        rightPanel.add(bottomrightPanel, BorderLayout.SOUTH);
        rightPanel.add(toprightPanel, BorderLayout.NORTH);

        rightPanel.setBackground(Color.WHITE);
        
        String[] addOptions = {"Tsting", "One", "Two"};
        
        //Buttons
        JButton addButton = new JButton("+");
        JButton delButton = new JButton("-");
        JButton saveButton = new JButton("SAVE");
        
        // Labels
		JLabel listLbl = new JLabel("Add"); 
		JLabel recipe_name = new JLabel("Recipe name: ");
		JLabel recipe_ingredients = new JLabel("Ingredients: ");
		JLabel recipe_directions = new JLabel("Directions: ");
		
		JList options = new JList(addOptions); 
		JList Recipes_List = new JList(dbObject.getOutput_recipe());
		JList Description_List = new JList(dbObject.getOutput_description());
		JList ingredients_List = new JList(dbObject.getOutput_ingredients());
		
		for (String ingredient : dbObject.getOutput_ingredients())
			System.out.println(ingredient);
		
		listPanel.add(listLbl); 
		listPanel.add(options);  
		
        //btnPanel.setPreferredSize(new Dimension(300,40));
      
        
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        
        //4. Size the frame.
        frame2.pack();
        frame2.add(listPanel, BorderLayout.CENTER);
        frame2.setVisible(false);
        
        leftPanel.add(btnPanel, BorderLayout.SOUTH);
        leftPanel.add(Recipes_List, BorderLayout.WEST);
        scrollPane = new JScrollPane(tbl);
        //leftPanel.add(scrollPane,BorderLayout.CENTER);
        //rightPanel.add(scrollPane,BorderLayout.CENTER);
        toprightPanel.add(Description_List);
        bottomrightPanel.add(ingredients_List);
     

        addButton.addActionListener(new ActionListener() { 
			@Override public void actionPerformed(ActionEvent event) { 
				//When the fruit of veg button is pressed 
				//the setVisible value of the listPanel and 
				//comboPanel is switched from true to  
				//value or vice versa. 
				frame2.setVisible(!frame2.isVisible()); 
				} 
		});
        
        btnPanel.add(addButton);
        btnPanel.add(delButton);
        
        //frame2 adds
        frame2.add(recipe_name, BorderLayout.WEST);
        frame2.add(recipe_ingredients, BorderLayout.WEST);
        frame2.add(recipe_directions, BorderLayout.WEST);
        frame2.add(saveButton, BorderLayout.SOUTH);
        
        
    }
}
