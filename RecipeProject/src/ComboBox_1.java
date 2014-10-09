import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;


public class ComboBox_1 extends JFrame implements ActionListener {

	final String[] SortItems = {"All Recipes", "Breakfast", "Lunch", "Dinner", "Dessert"};
	JComboBox mainComboBox = new JComboBox(SortItems);
	DBConnector app = new DBConnector();
	
	public ComboBox_1(){
		mainComboBox.addActionListener( this );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String item = (String)mainComboBox.getSelectedItem();
	
		for (String theForce : SortItems)
			if (theForce.compareTo(item) == 0){
				app.search_DB(item);
				System.out.println("Send " + theForce + " search request to database");
			}

		
	}
	public JComboBox getComboBox(){
		return mainComboBox;
	}
}
