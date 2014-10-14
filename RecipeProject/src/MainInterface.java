import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JList;
import javax.swing.JSplitPane;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextField;


public class MainInterface extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public MainInterface() {

		// Need a Database object
		final DBConnector dbObject = new DBConnector();
		dbObject.get_DBinfo();
		final JList list = new JList(dbObject.getOutput_recipe());
		final JList list_1 = new JList(dbObject.getOutput_description(0));
		final JList list_2 = new JList(dbObject.getOutput_ingredients2(0));
		final String[] SortItems = {"Breakfast", "Lunch", "Dinner", "Dessert"};

		setTitle("Recipes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0,0,0)), "Recipes"));
		panel.setPreferredSize(new Dimension(200, 10));
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));

		ComboBox_1 dropDown = new ComboBox_1();
		panel.add(dropDown.getComboBox(), BorderLayout.NORTH);

		panel.add(list);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton button = new JButton("+");
		panel_2.add(button);

		JButton deleteButton = new JButton("-");
		panel_2.add(deleteButton);
		
		JPanel search_panel = new JPanel();
		search_panel.setBackground(Color.WHITE);
		search_panel.setPreferredSize(new Dimension(200, 10));
		search_panel.setLayout(new BorderLayout(0, 0));
		
		JTextField search_text = new JTextField("");
		JButton search_button = new JButton("Search");
		
		search_panel.add(search_text);
		search_panel.add(search_button);
		contentPane.add(search_panel, BorderLayout.SOUTH);
		

		final rightPanel panel_3 = new rightPanel(dbObject.getIndividualOutput_recipe(0), dbObject.getIndividualOutput_ingredients(0), list_1, list_2);
		contentPane.add(panel_3, BorderLayout.EAST);

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(arg0.getValueIsAdjusting())
					return;

		        int selectedVar;
		        selectedVar = list.getSelectedIndex();
		        panel_3.changeRecipe(dbObject.getIndividualOutput_recipe(selectedVar), dbObject.getOutput_description(selectedVar), dbObject.getIndividualOutput_ingredients(selectedVar), dbObject.getOutput_ingredients2(selectedVar));
			}
		});

	    deleteButton.addActionListener(new ActionListener() {
  			@Override public void actionPerformed(ActionEvent event) {
  				int selectedVar = list.getSelectedIndex();

  				dbObject.delete_FromDB(selectedVar);
  				Driver driver_2 = new Driver();
  				dispose();

  			}
  		});

	    final displayFrame2 frame_2 = new displayFrame2();

	    button.addActionListener(new ActionListener() {
  			@Override public void actionPerformed(ActionEvent event) {

  				frame_2.showFrame();
  			}
  		});
	    
	}

}

