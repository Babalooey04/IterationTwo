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
    private displayFrame2 frame2;
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

        leftPanel = new JPanel();
        rightPanel = new displayRight();
        frame2 = new displayFrame2();
        btnPanel = new JPanel();


        btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Recipe Name"));
        leftPanel.setPreferredSize(new Dimension(200,300));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BorderLayout());


        //Buttons
        JButton addButton = new JButton("+");
        JButton delButton = new JButton("-");


		final JList Recipes_List = new JList(dbObject.getOutput_recipe());
		final JList Description_List = new JList(dbObject.getOutput_description(0));
		final JList ingredients_List = new JList(dbObject.getOutput_ingredients2(0));


	    Recipes_List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//for (String ingredient : dbObject.getOutput_description2())
			//System.out.println(ingredient);

		//btnPanel.setPreferredSize(new Dimension(300,40));
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

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

				frame2.showFrame();
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

    }
}
