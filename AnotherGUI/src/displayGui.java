import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class displayGui extends JFrame{
    JPanel leftPanel;
    private JPanel btnPanel;
    private JScrollPane scrollPane;
    private displayRight rightPanel;
    final JPanel listPanel;
    JTextArea text = new JTextArea(300, 300);

    @SuppressWarnings("unchecked")
	public displayGui(JTable tbl){

    	// Need a Database object
    	final DBConnector dbObject = new DBConnector();
    	dbObject.get_DBinfo();

        setTitle("My Recipes");
        setSize(600,600);
        setBackground(Color.gray);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

      //1. Create another frame.
        final JFrame frame2 = new JFrame("Add Recipe");
        frame2.setPreferredSize(new Dimension(300, 300));

        leftPanel = new JPanel();
        rightPanel = new displayRight();
        btnPanel = new JPanel();
        listPanel = new JPanel();
        JTextField field = new JTextField(1000);
        frame2.add(field, BorderLayout.NORTH);

        btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Recipe Name"));
        leftPanel.setPreferredSize(new Dimension(200,300));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BorderLayout());

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
		final JList Recipes_List = new JList(dbObject.getOutput_recipe());
		final JList Description_List = new JList(dbObject.getOutput_description(0));
		final JList ingredients_List = new JList(dbObject.getOutput_ingredients2(0));


	    Recipes_List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



		//for (String ingredient : dbObject.getOutput_description2())
			//System.out.println(ingredient);

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



    	Recipes_List.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getValueIsAdjusting())
					return;

		        int selectedVar;
		        selectedVar = Recipes_List.getSelectedIndex();
		        Description_List.setListData(dbObject.getOutput_description(selectedVar));
		        ingredients_List.setListData(dbObject.getOutput_ingredients2(selectedVar));
			}
		});

    	rightPanel.addRecipes(Description_List, ingredients_List);

        addButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent event) {

				frame2.setVisible(!frame2.isVisible());
				}
		});
        delButton.addActionListener(new ActionListener() {
  			@Override public void actionPerformed(ActionEvent event) {
  				int selectedVar = Recipes_List.getSelectedIndex();

  				dbObject.delete_FromDB(selectedVar);

  				dbObject.get_DBinfo();

  				System.out.println(dbObject.getOutput_recipe()[selectedVar-1]);

  				Recipes_List.setListData(dbObject.getOutput_recipe());
  			    Description_List.setListData(dbObject.getOutput_description(selectedVar-1));
		        ingredients_List.setListData(dbObject.getOutput_ingredients2(selectedVar-1));
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
