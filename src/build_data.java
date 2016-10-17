import java.sql.*;

public class build_data {
	public static void main(String args[])
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");     
			//Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("Success loading Mysql Driver!");
		}
		catch (Exception e) {
			System.out.print("Error loading Mysql Driver!");
			e.printStackTrace();
		}
		try {
			Connection connect = DriverManager.getConnection( "jdbc:mysql://localhost:3306/Bookbase","root","123456");

			int num=10;
			PreparedStatement Statement=connect.prepareStatement("INSERT INTO Book VALUES(?,?,?,?,?,?)");
			//PreparedStatement Statement=connect.prepareStatement("INSERT INTO Author VALUES(?,?,?,?)");
			/*for(int i=0;i<num;i++)        
			{
				Statement.setString(1,""+i);
				Statement.setString(2,"Name_"+i);
				Statement.setString(3,"20");
				Statement.setString(4,"China"); 
				Statement.executeUpdate();
			}*/
			for(int i=0;i<num;i++)        
			{
				Statement.setString(1,""+i);
				Statement.setString(2,"book_"+i);
				Statement.setString(3,""+i);
				Statement.setString(4,"HITPress"); 
				Statement.setString(5,"2016-10-1");
				Statement.setString(6,"10"); 
				Statement.executeUpdate();
			}
			System.out.println("Success insert data into database!");
			// } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// System.out.println("An error has occurred:"+e.toString());
			//  e.printStackTrace();
		}catch(SQLException e)
		{
		}
	}
}