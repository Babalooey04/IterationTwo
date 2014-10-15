import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;

public class rightPanel extends JPanel{

	JList list_1;
	JList list_2;
	final JPanel panel_1;
	final JPanel panel_4;

	public rightPanel(String description, String ingredients, JList firstList, JList secondList) {

		list_1 = firstList;
		list_2 = secondList;

		setPreferredSize(new Dimension(400, 500));
		setBackground(Color.WHITE);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel_1 = new JPanel();
		
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0,0,0)), description + " Description"));
		panel_1.setPreferredSize(new Dimension(350, 350));

		panel_1.add(list_1);

		panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setPreferredSize(new Dimension(300, 200));
		panel_4.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0,0,0)), ingredients + " Ingredients"));
		add(panel_4);
		add(panel_1);
		panel_4.add(list_2);

		JPanel search_panel = new JPanel();
		search_panel.setBackground(Color.WHITE);
		search_panel.setSize(new Dimension(400, 20));
		search_panel.setLayout(new GridLayout(0, 2));
		JTextField search_text = new JTextField("");
		JButton search_button = new JButton("Search");
		search_panel.add(search_text);
		search_panel.add(search_button);
		add(search_panel);
	}

	public void changeRecipe(String description_Name, Object[] description, String ingredients_Name, Object[] ingredients) {

        list_1.setListData(description);
        list_2.setListData(ingredients);
        panel_1.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0,0,0)), description_Name + " Description"));
        panel_4.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0,0,0)), ingredients_Name + " Ingredients"));
	}
}
