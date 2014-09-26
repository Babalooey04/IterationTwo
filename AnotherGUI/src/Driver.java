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
		app.get_DBinfo();
		//app.run();
		
		//app.insert_IntoDB("Test1", "1/2 Tsp Salt, 2 LBS beef", "Cook it yo!");
		//app.delete_FromDB();
	}

}
