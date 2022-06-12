package za.co.uyanda.interview.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author TRAIN 85
 */
public class DbManager {
    private Connection con = null;
    private final String uri;
    private final String database;
    private final String driver;
    private final String username;
    private final String password;

    public DbManager(String uri, String database, String driver, String username, String password) {
        this.uri = uri;
        this.database = database;
        this.username = username;
        this.password = password;
        this.driver = driver;
    }
    
    public Connection getConnection(){
        if(con == null){
          createConnection();
        }
        return con;
    }
    
    private boolean createConnection(){
        boolean retVal=false;
        try{
          Class.forName(driver);
        }catch(ClassNotFoundException ex){
          System.out.println("Error: "+ex.getMessage());
          return retVal;
        }
        System.out.println("Driver Loaded");
        try{
          con = DriverManager.getConnection(uri+database,username,password);
          retVal=true;
        }catch(SQLException ex){
          System.out.println("Ooops Could not connect: "+ex.getMessage());
          return retVal;
        }
        return retVal;        
    }
    
    public boolean closeConnection(){
        boolean retVal = false;     
          if (con!=null){
            try{
              con.close();
              retVal=true;
            }catch(SQLException ex){
              System.out.println("Error closing connection: "+ex.getMessage());
            }finally{
              con=null;
            }
          }
       return retVal;
    }
    
    @Override
    public String toString() {
        return "DbManager{" + "con=" + con + ", uri=" + uri + ", database=" + database + ", driver=" + driver + ", username=" + username + ", password=" + password + '}';
    }
    
}
