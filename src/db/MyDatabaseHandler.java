package db;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MyDatabaseHandler {
    
    Connection connect = null;
    Statement statement = null;
    PreparedStatement pStatement = null;
    ResultSet result = null;
    String data=null;
    Boolean status=false;
    public Connection conn(){
        return connect;
    }
    public void closeConnection() throws SQLException{
        connect.close();
    }
    public void setConnection(String name,String user,String password){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String databaseName=name;
            String url="jdbc:mysql://localhost/online_food";
            
            connect = DriverManager.getConnection
              (url,user,password);
            
            System.out.println("Successfully connected..");
            status=true;
        
      

            
        }catch(Exception e){
            System.out.println("Connection Problem..");
            e.printStackTrace();
        }
        

        
    }
     public boolean getStatus(){
         return status;
     }
     public ArrayList<String> getDatabaseMetaData()
    {
        ArrayList<String> s = new ArrayList();
        try {

            DatabaseMetaData dbmd = connect.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = dbmd.getTables(null, null, "%", types);
            while (rs.next()) {
                System.out.println(rs.getString("TABLE_NAME"));
                s.add(rs.getString("TABLE_NAME"));
            }
        } 
            catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    
    }
         Vector columnNamesVector = new Vector();
        Vector dataVector = new Vector();
        public Vector getColumn(){
            return columnNamesVector;
        }
        public Vector getData(){
            return dataVector;
        }
    public void testQuery3(String a,String b,String c)
    {
        dataVector.clear();
        columnNamesVector.clear();
        String sql = "SELECT "+a+" FROM "+b+" GROUP BY "+c;
                  ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();
        try{
             String query="SELECT "+a+" FROM "+b+" GROUP BY "+c;
              statement = connect.createStatement();
            result = statement.executeQuery(query);
            ResultSetMetaData md = result.getMetaData();
            int columns = md.getColumnCount();
      
         for (int i = 1; i <= columns; i++)
            {
                columnNames.add( md.getColumnName(i) );
            }

            while (result.next())
            {
                ArrayList row = new ArrayList(columns);

                for (int i = 1; i <= columns; i++)
                {
                    row.add( result.getObject(i) );
                }

                data.add( row );
            }
          
            
        }catch(Exception e){
            e.printStackTrace();
        }
     

        for (int i = 0; i < data.size(); i++)
        {
            ArrayList subArray = (ArrayList)data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++)
            {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++ )
            columnNamesVector.add(columnNames.get(i));
    }
    public void delete(String s,String table, String column) throws SQLException
    {
         String query = "delete from "+table+" where "+column+" = '"+s+"'";
         System.out.println(query);
      PreparedStatement preparedStmt = connect.prepareStatement(query);
     // preparedStmt.setInt(1, 3);

      // execute the preparedstatement
      preparedStmt.execute();
      
   //   connect.close();
    }
    public String getPassword(String mail) throws SQLException{
        String pass="";
        String query = "select password from customer where mail = '"+mail+"'";
        statement = connect.createStatement();
                    result = statement.executeQuery(query);
             //       int m = result.getInt("max(id)");
               //     System.out.println(m);
            ResultSetMetaData md = result.getMetaData();
        //showResult(result);
              try{
            
            System.out.println(result.getMetaData().getColumnCount());
           
             for(int i = 1 ; i <= result.getMetaData().getColumnCount(); i++){
                    columnName.add(result.getMetaData().getColumnName(i));
                    
                }
            
             
           /*  for(String c : columnName){
                 System.out.print(c+" ");
             }
             System.out.println("");*/
             
            while(result.next()){
               String ss=""; 
              for(String c : columnName){
                  ss=result.getString(c);

              } 
                
            
                System.out.println(ss);
                
                data+=ss;
                pass=ss;
            
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
      return pass;
    }
    //
    //
    public String admingetPassword(int id) throws SQLException{
        String pass="";
        String query = "select password from staff where id = "+id;
        statement = connect.createStatement();
                    result = statement.executeQuery(query);
             //       int m = result.getInt("max(id)");
               //     System.out.println(m);
            ResultSetMetaData md = result.getMetaData();
        //showResult(result);
              try{
            
            System.out.println(result.getMetaData().getColumnCount());
           
             for(int i = 1 ; i <= result.getMetaData().getColumnCount(); i++){
                    columnName.add(result.getMetaData().getColumnName(i));
                    
                }
            
             
           /*  for(String c : columnName){
                 System.out.print(c+" ");
             }
             System.out.println("");*/
             
            while(result.next()){
               String ss=""; 
              for(String c : columnName){
                  ss=result.getString(c);

              } 
                
            
                System.out.println(ss);
                
                data+=ss;
                pass=ss;
            
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
      return pass;
    }
    //
    public int id(String mail) throws SQLException
    {
        int maxID=0;
        String query = "select id from customer where mail = '"+mail+"'";
        System.out.println(query);
                statement = connect.createStatement();
                result = statement.executeQuery(query);
    
                  try{
            
            System.out.println(result.getMetaData().getColumnCount());
           
             for(int i = 1 ; i <= result.getMetaData().getColumnCount(); i++){
                    columnName.add(result.getMetaData().getColumnName(i));
                    
                }
            
            while(result.next()){
               String ss=""; 
              for(String c : columnName){
//                  ss+=result.getString(c)+" ";
                  maxID= result.getInt(c);
              } 
                
            
                System.out.println(maxID);
                
                data+=ss+'*';
            
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
      
           return maxID;
    }
 public int maxOrder() throws SQLException
    {
         int maxID=0;
 
        String query = "select max(o_id) from orders";
       // query = "select password from customer where mail = 'sadeeb.mufc@gmail.com'";
        statement = connect.createStatement();
                    result = statement.executeQuery(query);
             //       int m = result.getInt("max(id)");
               //     System.out.println(m);
            ResultSetMetaData md = result.getMetaData();
        //showResult(result);
              try{
            
            System.out.println(result.getMetaData().getColumnCount());
           
             for(int i = 1 ; i <= result.getMetaData().getColumnCount(); i++){
                    columnName.add(result.getMetaData().getColumnName(i));
                    
                }
            
             
           /*  for(String c : columnName){
                 System.out.print(c+" ");
             }
             System.out.println("");*/
             
            while(result.next()){
               String ss=""; 
              for(String c : columnName){
//                  ss+=result.getString(c)+" ";
                  maxID= result.getInt(c);
              } 
                
            
                System.out.println(maxID);
                
                data+=ss+'*';
            
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
      
           return maxID;
    } 
    //
    public int max() throws SQLException
    {
         int maxID=0;
 
        String query = "select max(id) from customer";
       // query = "select password from customer where mail = 'sadeeb.mufc@gmail.com'";
        statement = connect.createStatement();
                    result = statement.executeQuery(query);
             //       int m = result.getInt("max(id)");
               //     System.out.println(m);
            ResultSetMetaData md = result.getMetaData();
        //showResult(result);
              try{
            
            System.out.println(result.getMetaData().getColumnCount());
           
             for(int i = 1 ; i <= result.getMetaData().getColumnCount(); i++){
                    columnName.add(result.getMetaData().getColumnName(i));
                    
                }
            
             
           /*  for(String c : columnName){
                 System.out.print(c+" ");
             }
             System.out.println("");*/
             
            while(result.next()){
               String ss=""; 
              for(String c : columnName){
//                  ss+=result.getString(c)+" ";
                  maxID= result.getInt(c);
              } 
                
            
                System.out.println(maxID);
                
                data+=ss+'*';
            
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
      
           return maxID;
    }
    //
    //
    public  DefaultTableModel buildTableModel()
        throws SQLException {

    ResultSetMetaData metaData = result.getMetaData();

    // names of columns
    Vector<String> columnNames = new Vector<String>();
    int columnCount = metaData.getColumnCount();
    for (int column = 1; column <= columnCount; column++) {
        columnNames.add(metaData.getColumnName(column));
    }

    // data of the table
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (result.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            vector.add(result.getObject(columnIndex));
        }
        data.add(vector);
    }

    return new DefaultTableModel(data, columnNames);

}
    public void testQuery2(String s){
        dataVector.clear();
        columnNamesVector.clear();
                ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();
        try{
             String query="SELECT * FROM "+s;
              statement = connect.createStatement();
            result = statement.executeQuery(query);
            ResultSetMetaData md = result.getMetaData();
            int columns = md.getColumnCount();
      
         for (int i = 1; i <= columns; i++)
            {
                columnNames.add( md.getColumnName(i) );
            }

            while (result.next())
            {
                ArrayList row = new ArrayList(columns);

                for (int i = 1; i <= columns; i++)
                {
                    row.add( result.getObject(i) );
                }

                data.add( row );
            }
          
            
        }catch(Exception e){
            e.printStackTrace();
        }
     

        for (int i = 0; i < data.size(); i++)
        {
            ArrayList subArray = (ArrayList)data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++)
            {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++ )
            columnNamesVector.add(columnNames.get(i));
        
    }
    //
     public void testQuery4(String s){
        dataVector.clear();
        columnNamesVector.clear();
                ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();
        try{
             String query=s;
              statement = connect.createStatement();
            result = statement.executeQuery(query);
            ResultSetMetaData md = result.getMetaData();
            int columns = md.getColumnCount();
      
         for (int i = 1; i <= columns; i++)
            {
                columnNames.add( md.getColumnName(i) );
            }

            while (result.next())
            {
                ArrayList row = new ArrayList(columns);

                for (int i = 1; i <= columns; i++)
                {
                    row.add( result.getObject(i) );
                }

                data.add( row );
            }
          
            
        }catch(Exception e){
            e.printStackTrace();
        }
     

        for (int i = 0; i < data.size(); i++)
        {
            ArrayList subArray = (ArrayList)data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++)
            {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++ )
            columnNamesVector.add(columnNames.get(i));
        
    }
    //
    
    public ArrayList<String> testQuery(String s){
        System.out.println(s);
        //columnName=null;
        columnName.clear();
        try{
            String query="SELECT * FROM "+s;
            statement = connect.createStatement();
            result = statement.executeQuery(query);
            for(int i = 1 ; i <= result.getMetaData().getColumnCount(); i++){
                    columnName.add(result.getMetaData().getColumnName(i));
                    
                }
            System.out.println("Successfull Query..");
            
            //
            
            //
            
        }catch(Exception e){
            System.out.println("Error in Query..");
            e.printStackTrace();
        }
        
        return columnName;
    }
     ArrayList<String> columnName = new ArrayList<String>();

    public void showResult(ResultSet result){
        try{
            
            System.out.println(result.getMetaData().getColumnCount());
           
             for(int i = 1 ; i <= result.getMetaData().getColumnCount(); i++){
                    columnName.add(result.getMetaData().getColumnName(i));
                    
                }
            
             
             for(String c : columnName){
                 System.out.print(c+" ");
             }
             System.out.println("");
             
            while(result.next()){
               String ss=""; 
              for(String c : columnName){
                  ss+=result.getString(c)+" ";
              } 
                
            
                System.out.println(ss);
                data+=ss+'*';
            
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public int insertData(String s){
        
        try{
            String query = s;
            pStatement = connect.prepareStatement(query);
            
            
            pStatement.executeUpdate();
            
            
            System.out.println("Successfull");
            
        }catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
    // Duplicate entry
   // JOptionPane.showMessageDialog(this, "Eggs are not supposed to be green.");
          //  System.out.println("dupli");
   return 666;
}catch(Exception e){
            e.printStackTrace();
        }
    
    return 1;
}
}
