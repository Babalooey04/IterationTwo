import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * This class demonstrates how to connect to MySQL and run some basic commands.
 * 
 * In order to use this, you have to download the Connector/J driver and add
 * its .jar file to your build path.  You can find it here:
 * 
 * http://dev.mysql.com/downloads/connector/j/
 * 
 * You will see the following exception if it's not in your class path:
 * 
 * java.sql.SQLException: No suitable driver found for jdbc:mysql://localhost:3306/
 * 
 * To add it to your class path:
 * 1. Right click on your project
 * 2. Go to Build Path -> Add External Archives...
 * 3. Select the file mysql-connector-java-5.1.24-bin.jar
 *    NOTE: If you have a different version of the .jar file, the name may be
 *    a little different.
 *    
 * The user name and password are both "root", which should be correct if you followed
 * the advice in the MySQL tutorial. If you want to use different credentials, you can
 * change them below. 
 * 
 * You will get the following exception if the credentials are wrong:
 * 
 * java.sql.SQLException: Access denied for user 'userName'@'localhost' (using password: YES)
 * 
 * You will instead get the following exception if MySQL isn't installed, isn't
 * running, or if your serverName or portNumber are wrong:
 * 
 * java.net.ConnectException: Connection refused
 */
public class DBConnector {

	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "Pitsnip1";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "Recipe";
	
	/** The name of the table we are testing with */
	private final String tableName = "recipes_tbl";
	
	int MAX = 100;
	ArrayList<String[]> result_return = new ArrayList<String[]>();
	
	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return conn;
	}

	/**
	 * Run a SQL command which does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	/**
	 * Connect to MySQL and do some stuff.
	 */
	public void insert_IntoDB(String name, String ingredients, String directions){

		// Connect to MySQL
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		// Insert into table
				try {
				    String createString =
					        "INSERT INTO recipes_tbl(recipes_name, recipes_ingredient, recipes_directions)" + 
				    " VALUES('" + name + "', '" + ingredients + "', '" + directions + "')";
					this.executeUpdate(conn, createString);
					System.out.println("Inserted new recipe!");
			    } catch (SQLException e) {
					System.out.println("ERROR: Failed to insert the recipe");
					e.printStackTrace();
					return;
				}
		
	}
	public String[] get_DBinfo(){
		
		// Instance variables

		int i = 0;
		
		// Connect to MySQL
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		String[] internalArray = new String[MAX];

		
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return null;
		}
		// Query the recipes_tbl. Need to abstract this to also querry tags and other tables.
				try {
					stmt = conn.createStatement();
					resultSet = stmt.executeQuery("SELECT * FROM recipes_tbl");
					while(resultSet.next()){
						String id = resultSet.getString("id");
						internalArray[i++] = id;
						String recipe_name = resultSet.getString("recipes_name");
						internalArray[i++] = recipe_name;
						String recipe_ingredients = resultSet.getString("recipes_ingredient");
						internalArray[i++] = recipe_ingredients;
						String recipe_directions = resultSet.getString("recipes_directions");
						internalArray[i++] = recipe_directions;
					
						System.out.println("ID: " + id + ", Recipe: " + recipe_name + ", Ingredients: " 
							+ recipe_ingredients + ", Directions: " + recipe_directions);
						result_return.add(internalArray);
						i ++;
					}
				
			    } catch (SQLException e) {
					System.out.println("ERROR: Failed to querry the recipes_tbl");
					e.printStackTrace();
					return null;
				}
				return internalArray;
	}
	
	public void delete_FromDB(){

		// Connect to MySQL
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		// Insert into table
				try {
				    String createString =
					        "DELETE FROM recipes_tbl WHERE id=4";
					this.executeUpdate(conn, createString);
					System.out.println("Deleted that old recipe!");
			    } catch (SQLException e) {
					System.out.println("ERROR: Failed to delete the recipe");
					e.printStackTrace();
					return;
				}
		
	}
	public void run() {

		// Connect to MySQL
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}

		// Create a table
		try {
		    String createString =
			        "CREATE TABLE " + this.tableName + " ( " +
			        "ID INTEGER NOT NULL, " +
			        "NAME varchar(40) NOT NULL, " +
			        "STREET varchar(40) NOT NULL, " +
			        "CITY varchar(20) NOT NULL, " +
			        "STATE char(2) NOT NULL, " +
			        "ZIP char(5), " +
			        "PRIMARY KEY (ID))";
			this.executeUpdate(conn, createString);
			System.out.println("Created a table");
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}
		
		// Drop the table
		try {
		    String dropString = "DROP TABLE " + this.tableName;
			this.executeUpdate(conn, dropString);
			System.out.println("Dropped the table");
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not drop the table");
			e.printStackTrace();
			return;
		}
	}
}
