package mypack;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankDAO {

    public static boolean createAccount(Bank b) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        int res = 0;
        String create = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        
        try {
    		Class.forName("com.mysql.cj.jdbc.Driver");

    		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pentagon?useSSL=false&requireSSL=false\r\n"
      		 		+ "","root","password");            ps = con.prepareStatement(create);
            ps.setInt(1, b.getAccno());
            ps.setString(2, b.getName());
            ps.setInt(3, b.getBalance());
            ps.setString(4, b.getPin());
            res = ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }

        return res > 0;
    }

    public static Bank getDetails(Bank b) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        Bank b2 = null; // New Bank instance to store the result
        String data = "SELECT * FROM USERS WHERE accno = ? AND pin = ?";

        try {
        	 Class.forName("com.mysql.cj.jdbc.Driver");
     		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pentagon?useSSL=false&requireSSL=false\r\n"
     		 		+ "","root","password"); 
            ps = con.prepareStatement(data);
            ps.setInt(1, b.getAccno());
            ps.setString(2, b.getPin());

            res = ps.executeQuery();
            if (res.next()) {
                b2 = new Bank();
                b2.setAccno(res.getInt("accno"));
                b2.setName(res.getString("name"));
                b2.setBalance(res.getInt("balance"));
                b2.setPin(res.getString("pin"));
            }
        } finally {
            if (res != null) res.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }

        return b2; 
    }

    public static int updatePin(int acc, String newPin) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        int res = 0;
        String updatePin = "UPDATE USERS SET pin = ? WHERE accno = ?";

        try {
    		Class.forName("com.mysql.cj.jdbc.Driver");

    		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pentagon?useSSL=false&requireSSL=false\r\n"
      		 		+ "","root","password");            ps = con.prepareStatement(updatePin);
            ps.setString(1, newPin);
            ps.setInt(2, acc);
            res = ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }

        return res;
    }
   
    public static int updateBalance(int acc,int bal) throws ClassNotFoundException, SQLException {
    	Connection con = null;

        PreparedStatement ps = null;
      int res = 0;
       String update = "UPDATE USERS SET BALANCE = ? WHERE ACCNO =?";
       int rs = 0;
       con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pentagon?useSSL=false&requireSSL=false\r\n"
		 		+ "","root","password");
       ps = con.prepareStatement(update);
       ps.setInt(1, bal);
       ps.setInt(2,acc);
       return res = ps.executeUpdate();
       
    	
    }
    public static int getBal(int acc) throws ClassNotFoundException, SQLException {
    	Connection con = null;
    	 
         PreparedStatement ps = null;
        ResultSet res = null;  
        int balance = 0;
         String data = "SELECT * FROM USERS WHERE ACCNO=?";
         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pentagon?useSSL=false&requireSSL=false\r\n"
  		 		+ "","root","password");         ps = con.prepareStatement(data);
         ps.setInt(1,acc);
         res = ps.executeQuery();
         while(res.next()) {
        	 balance = res.getInt(3);
        	 
         }
		return balance;
         
    }
    public static int withdraw(int ac,int balance) throws ClassNotFoundException, SQLException {
    	Connection con = null;
    	PreparedStatement ps = null;
    	int res = 0;
    	String qry = "UPDATE USERS SET BALANCE =? WHERE ACCNO =?";
    	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pentagon?useSSL=false&requireSSL=false\r\n"
  		 		+ "","root","password");    	ps = con.prepareStatement(qry);
    	ps.setInt(1,balance);
    	ps.setInt(2, ac);
    	res = ps.executeUpdate();
    	return res;
    	
    }

	public static String searchacc(int account)throws ClassNotFoundException, SQLException {
		int acc = account;
		Connection cn=null;
		PreparedStatement pt = null;
		
		String w = "SELECT * FROM USERS WHERE ACCNO=?";
		 cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pentagon","root","password"); 
	  pt = cn.prepareStatement(w);
	  pt.setInt(1,acc);
	  ResultSet  res = pt.executeQuery();
	  if (res.next()) {
         return "Account is Found";	 
	
	}
	  else {
		  return "Account Not Exist";
	  }
}
}
