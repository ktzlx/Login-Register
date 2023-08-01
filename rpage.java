package net.codejava;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class rpage extends JFrame {
	
	static JFrame regPage;
    static JPanel regPanel;
    
    static JButton regsubmit;
    static JLabel success;
	
	static JTextField idtxt;
    static JTextField fnametxt;
    static JTextField lnametxt;
    static JTextField addtxt;
    static JTextField pntxt;
    static JTextField emailtxt;
    static JTextField dobtxt;
    static JTextField usertxt;
    static JTextField passtxt;
    
    String jdbcURL = "jdbc:mysql://localhost:3306/dbproject";
	String username = "sqluser";
	String password = "password";
	gui g;
	
	public rpage(){
		regPage = new JFrame();
	    regPanel = new JPanel();
	    regPage.setSize(500, 450);
	    regPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    regPage.add(regPanel);
	    regPanel.setLayout(null);
	
	    JLabel rname = new JLabel("Register Page");
	    rname.setBounds(30, 10, 165, 25);
	    regPanel.add(rname);
	
	    JLabel idl = new JLabel("Student ID");
	    idl.setBounds(30,50,130,25);
	    regPanel.add(idl);
	    idtxt = new JTextField(25);
	    idtxt.setBounds(30,70,130,25);
	    regPanel.add(idtxt);
	
	    JLabel fnamel = new JLabel("First Name"); 
	    fnamel.setBounds(30,100,130,25);
	    regPanel.add(fnamel);
	    fnametxt = new JTextField(25);
	    fnametxt.setBounds(30,120,130,25);
	    regPanel.add(fnametxt);
	
	    JLabel lnamel = new JLabel("Last Name");
	    lnamel.setBounds(200,100,130,25);
	    regPanel.add(lnamel);
	    lnametxt = new JTextField(20); 
	    lnametxt.setBounds(200,120,130,25);
	    regPanel.add(lnametxt);
	
	    JLabel addl = new JLabel("Address");
	    addl.setBounds(30,150,130,25);
	    regPanel.add(addl);
	    addtxt = new JTextField(20); 
	    addtxt.setBounds(30,170,130,25);
	    regPanel.add(addtxt);
	
	    JLabel pnl = new JLabel("Phone Number");
	    pnl.setBounds(200,150,130,25);
	    regPanel.add(pnl);
	    pntxt = new JTextField(20); 
	    pntxt.setBounds(200,170,130,25);
	    regPanel.add(pntxt);
	
	    JLabel emaill = new JLabel("Email");
	    emaill.setBounds(30,200,130,25);
	    regPanel.add(emaill);
	    emailtxt = new JTextField(20); 
	    emailtxt.setBounds(30,220,130,25);
	    regPanel.add(emailtxt);
	
	    JLabel dobl = new JLabel("D.O.B.");
	    dobl.setBounds(200,200,130,25);
	    regPanel.add(dobl);
	    dobtxt = new JTextField(20); 
	    dobtxt.setBounds(200,220,130,25);
	    regPanel.add(dobtxt);
	    
	    JLabel userl = new JLabel("Username");
	    userl.setBounds(30,250,130,25);
	    regPanel.add(userl);
	    usertxt = new JTextField(20); 
	    usertxt.setBounds(30,270,130,25);
	    regPanel.add(usertxt);
	
	    JLabel passl = new JLabel("Password");
	    passl.setBounds(200,250,130,25);
	    regPanel.add(passl);
	    passtxt = new JTextField(20); 
	    passtxt.setBounds(200,270,130,25);
	    regPanel.add(passtxt);
	    
	    success = new JLabel("");
	    success.setBounds(150,350,200,25);
	    regPanel.add(success);
	    JButton back = new JButton("Back");
	    back.setBounds(300, 15, 80, 25);
	    regPanel.add(back);
	    back.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		new gui();
	    		regPage.setVisible(false);
	    	}
	    		
	    });
	
	    regsubmit = new JButton("Submit");
	    regsubmit.setBounds(150, 320, 85, 25);
	    regsubmit.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		String tempid = idtxt.getText();
        		String FirstName = fnametxt.getText();
        		String LastName = lnametxt.getText();
        		String Address = addtxt.getText();
        		String PhoneNumber = pntxt.getText();
        		String Email = emailtxt.getText();
        		String tempdob = dobtxt.getText();
        		String user = usertxt.getText();
        		String pass = passtxt.getText();
        		int StudentID=0;
        		if(tempid.length()!=0) {
        			StudentID = Integer.parseInt(tempid);
        		}
        		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        		Date dob = null;
        		if(tempdob.length()!=0) {
				try {
					dob = format.parse(tempdob);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
    
        		if(tempid.length()==0||Address.length()==0||tempdob.length()==0||user.length()==0||pass.length()==0) {
    					success.setText("Missing Field. Try Again.");
    					//con.close();
    			}else {
    				java.sql.Date DOB = new java.sql.Date(dob.getTime());
	    			try {
	        			Connection con = DriverManager.getConnection(jdbcURL, username, password);
	    				PreparedStatement st = (PreparedStatement)con.prepareStatement("INSERT INTO students (StudentID"
	    						+ ", FirstName, LastName, Address, PhoneNumber, Email, DateOfBirth, user, pass) "
	    						+ "VALUES (?,?,?,?,?,?,?,?,?)");    			
	    				st.setInt(1, StudentID);
	    				st.setString(2, FirstName);
	    				st.setString(3, LastName);
	    				st.setString(4, Address);
	    				st.setString(5, PhoneNumber);
	    				st.setString(6, Email);
	    				st.setDate(7, DOB);
	    				st.setString(8, user);
	    				st.setString(9, pass);
	    				int rows = st.executeUpdate();			
	    				if(rows>0) {
	    					success.setText("Submit succesful.");
	    					System.out.println("submitted");
	    					new gui();
	    					regPage.setVisible(false);
	 
	    				}else{
	    	                success.setText("Incorrect field. Try again.");
	    	            }
	    				con.close();
	        		}catch(SQLException ex) {
	        			ex.printStackTrace();
	        		}			
    			} 		
        	}
        });
	    regPanel.add(regsubmit);
	    regPage.setVisible(true);
	}
	
    
	
    public static void main(String[] args) {
	    new rpage();
    }
   
    

}
