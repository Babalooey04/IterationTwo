import java.awt.event.WindowEvent;

import javax.swing.JTable;


public class Driver {
	

	/**
	 * @param args
	 */
	static MainInterface dg;

	public static void main(String[] args) {
		Driver driver1 = new Driver();
		// TODO Auto-generated method stub
		
		
	}
	public Driver(){
		run();
	}
	public void run(){
		dg = new MainInterface();
		
		dg.setVisible(true);
		
		DBConnector app = new DBConnector();
		app.get_DBinfo();
		//app.run();
		
		//app.insert_IntoDB("Test1", "1/2 Tsp Salt, 2 LBS beef", "Cook it yo!");
		//app.delete_FromDB();
	}
}
