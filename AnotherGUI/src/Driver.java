import javax.swing.JTable;



public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JTable table = null;
		// TODO Auto-generated method stub
		displayGui dg = new displayGui(table);
		dg.setVisible(true);
		
		DBConnector app = new DBConnector();
		app.run();
	}

}
