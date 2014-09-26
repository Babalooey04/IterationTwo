import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class displayGui extends JFrame{
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel btnPanel;
    private JScrollPane scrollPane;
    final JPanel listPanel;

    public displayGui(JTable tbl){
    	
    	// Need a Database object
    	DBConnector app = new DBConnector();
    	
        setTitle("My Recipes");
        setSize(600,600);
        setBackground(Color.gray);

      //1. Create another frame.
        final JFrame frame2 = new JFrame("Add Recipe");
        frame2.setPreferredSize(new Dimension(300, 300));
        
      //2. Optional: What happens when the frame closes?
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        btnPanel = new JPanel();
        listPanel = new JPanel(); 
        JTextField field = new JTextField(20);
        frame2.add(field, BorderLayout.NORTH);
        
        btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Recipe Name"));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Recipe Description"));
        
        String[] addOptions = {"Tsting", "One", "Two"};
    
        String[] Descriptions = {"Get this from", "MySQL Database", "Need commands"};
        
        // Some logic
        Object[] dbRecipes = {app.get_DBinfo()};
    	String[] Recipes = new String[100];
    	
        for (int i = 0; i < dbRecipes.length; i ++){
        	Recipes = dbRecipes[i];
        }
        
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
		JList Recipes_List = new JList(Recipes);
		JList Description_List = new JList(Descriptions);
		
		listPanel.add(listLbl); 
		listPanel.add(options);  
		
        //btnPanel.setPreferredSize(new Dimension(300,40));
        leftPanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BorderLayout() );
        
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        
      //4. Size the frame.
        frame2.pack();
        frame2.add(listPanel, BorderLayout.CENTER);
        frame2.setVisible(false);
        
        leftPanel.add(btnPanel, BorderLayout.SOUTH);
        leftPanel.add(Recipes_List, BorderLayout.CENTER);
        scrollPane = new JScrollPane(tbl);
        //leftPanel.add(scrollPane,BorderLayout.CENTER);
        //rightPanel.add(scrollPane,BorderLayout.CENTER);
        rightPanel.add(Description_List, BorderLayout.CENTER);
        
     

        addButton.addActionListener(new ActionListener() { 
			@Override public void actionPerformed(ActionEvent event) { 
				//When the fruit of veg button is pressed 
				//the setVisible value of the listPanel and 
				//comboPanel is switched from true to  
				//value or vice versa. 
				frame2.setVisible(!frame2.isVisible()); 
				leftPanel.setVisible(!leftPanel.isVisible());  } });
        
        btnPanel.add(addButton);
        btnPanel.add(delButton);
        
        //frame2 adds
        frame2.add(recipe_name, BorderLayout.WEST);
        frame2.add(recipe_ingredients, BorderLayout.WEST);
        frame2.add(recipe_directions, BorderLayout.WEST);
        frame2.add(saveButton, BorderLayout.SOUTH);
        
        
    }
}
