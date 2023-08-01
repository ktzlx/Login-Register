package net.codejava;

import java.awt.Color;
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

public class UpdatePage extends JFrame{
	
	static JLabel success;
    static JTextField fnametxt;
    static JTextField lnametxt;
    static JTextField addtxt;
    static JTextField pntxt;
    static JTextField emailtxt;
    static JTextField usertxt;
    static JTextField passtxt;
	
	String jdbcURL = "jdbc:mysql://localhost:3306/dbproject";
	String username = "sqluser";
	String password = "password";
	
	static String User="";
	static String Pass="";
	int StudentID;
    String Fname = "";
    String Lname="";
    String addd="";
    String phonen="";
    String Emaill="";
	gui g;
	String newfname = "";
	String newlname = "";
	String newadd = "";
	String newphone = "";
	String newemail = "";
	String newuser = "";
	String newpass = "";
	//update update = new update();
	
	public UpdatePage(String User, String Pass) {
		this.User=User;
		this.Pass=Pass;
		
		JPanel regPanel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(500, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(regPanel);
        regPanel.setLayout(null);
        
        
        try {
        	Connection con = DriverManager.getConnection(jdbcURL, username, password);
			PreparedStatement st = (PreparedStatement)con.prepareStatement("Select StudentID, FirstName, LastName,"
					+ " Address, PhoneNumber, Email from students where user=? and pass=?");
			st.setString(1, User);
			st.setString(2, Pass);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				StudentID= rs.getInt("StudentID");
				Fname = rs.getString("FirstName");
				Lname = rs.getString("LastName");
				addd = rs.getString("Address");
				phonen = rs.getString("PhoneNumber");
				Emaill = rs.getString("Email");
				System.out.println("AYY "+StudentID);
			}else{
				System.out.println("Couldnt get info");
            }
			con.close();
        	
        }catch (SQLException ex) {
        	ex.printStackTrace();
        }
        
        
        JLabel rname = new JLabel("Welcome "+Fname +" "+Lname+"!");
	    rname.setBounds(30, 10, 165, 25);
	    regPanel.add(rname);
	
	    JLabel userl = new JLabel("Edit Username");
	    userl.setBounds(30,50,130,25);
	    regPanel.add(userl);
	    usertxt = new JTextField(25);
	    usertxt.setBounds(30,70,130,25);
	    regPanel.add(usertxt);
	    
	    JLabel passl = new JLabel("Edit Password");
	    passl.setBounds(200,50,130,25);
	    regPanel.add(passl);
	    passtxt = new JTextField(20); 
	    passtxt.setBounds(200,70,130,25);
	    regPanel.add(passtxt);
	
	    JLabel fnamel = new JLabel("Edit First Name"); 
	    fnamel.setBounds(30,100,130,25);
	    regPanel.add(fnamel);
	    fnametxt = new JTextField(25);
	    fnametxt.setBounds(30,120,130,25);
	    regPanel.add(fnametxt);
	
	    JLabel lnamel = new JLabel("Edit Last Name");
	    lnamel.setBounds(200,100,130,25);
	    regPanel.add(lnamel);
	    lnametxt = new JTextField(20); 
	    lnametxt.setBounds(200,120,130,25);
	    regPanel.add(lnametxt);
	
	    JLabel addl = new JLabel("Edit Address");
	    addl.setBounds(30,150,130,25);
	    regPanel.add(addl);
	    addtxt = new JTextField(20); 
	    addtxt.setBounds(30,170,130,25);
	    regPanel.add(addtxt);
	
	    JLabel pnl = new JLabel("Edit Phone Number");
	    pnl.setBounds(200,150,130,25);
	    regPanel.add(pnl);
	    pntxt = new JTextField(20); 
	    pntxt.setBounds(200,170,130,25);
	    regPanel.add(pntxt);
	
	    JLabel emaill = new JLabel("Edit Email");
	    emaill.setBounds(30,200,130,25);
	    regPanel.add(emaill);
	    emailtxt = new JTextField(20); 
	    emailtxt.setBounds(30,220,130,25);
	    regPanel.add(emailtxt);
	    
	    success = new JLabel("");
	    success.setBounds(130,330,130,25);
	    regPanel.add(success);
	    
	    JButton logout = new JButton("Logout");
	    logout.setBounds(350, 15, 80, 25);
	    logout.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
				g = new gui();
				frame.setVisible(false);
			}
	    	
	    });
	    regPanel.add(logout);
	    
	    JButton delete = new JButton("Delete Account");
	    delete.setBounds(200, 270, 120, 25);
	    delete.setBackground(Color.red);
	    regPanel.add(delete);
	    delete.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		JPanel delpanel = new JPanel();
	            JFrame dframe = new JFrame();
	            dframe.setSize(300, 200);
	            //dframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            dframe.add(delpanel);
	            delpanel.setLayout(null);
	            JLabel dname = new JLabel("Please enter Student Id to confirm delete.");
	    	    dname.setBounds(20, 10, 300, 25);
	    	    delpanel.add(dname);
	    	    
	    	    JTextField Didtxt = new JTextField(25);
	    	    Didtxt.setBounds(80,35,130,25);
	    	    delpanel.add(Didtxt);
	    	    dframe.setVisible(true);
	    	    
	    	    JLabel dsuccess = new JLabel("");
	    	    dsuccess.setBounds(70,100,200,25);
	    	    delpanel.add(dsuccess);
	    	    
	    	    JButton submit = new JButton("Submit");
	    	    submit.setBounds(100, 70, 80, 25);
	    	    submit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String tempid = Didtxt.getText();
		        		int checkid = Integer.parseInt(tempid);
	        			if(checkid!=StudentID) {
	    					dsuccess.setText("Incorrect ID.");
	    				}else {
			        		try {
			        			Connection con = DriverManager.getConnection(jdbcURL, username, password);
			    				PreparedStatement st = (PreparedStatement)con.prepareStatement("DELETE FROM students WHERE StudentID = ?");  				
			    				st.setInt(1, StudentID);		
			    				int rows = st.executeUpdate();
			    				
			    				if(rows>0) {
			    					dsuccess.setText("Submit succesful.");
			    					dframe.setVisible(false);
			    					frame.setVisible(false);
			    					g = new gui();
			 
			    				}else{
			    	                dsuccess.setText("Incorrect field. Try again.");
			    	            }
			    				con.close();
			        		}catch(SQLException ex) {
			        			ex.printStackTrace();
			        		}
	    				}
					}	
	    	    });
	    	    delpanel.add(submit);
		    }	
    	});
	    //==========================================================================
	    JButton save = new JButton("Save");
	    save.setBounds(70, 270, 85, 25);
	    regPanel.add(save);
	    save.addActionListener(new ActionListener() {
	    	
	    	
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
        		newfname = fnametxt.getText();
        		newlname = lnametxt.getText();
        		newadd = addtxt.getText();
        		newphone = pntxt.getText();
        		newemail = emailtxt.getText();
        		newuser = usertxt.getText();
        		newpass = passtxt.getText();
       
        		JPanel savpanel = new JPanel();
	            JFrame sframe = new JFrame();
	            sframe.setSize(300, 200);
	            //dframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            sframe.add(savpanel);
	            savpanel.setLayout(null);
	            JLabel sname = new JLabel("Please enter Student Id to confirm update.");
	    	    sname.setBounds(20, 10, 300, 25);
	    	    savpanel.add(sname);
	    	    
	    	    JTextField sidtxt = new JTextField(25);
	    	    sidtxt.setBounds(80,35,130,25);
	    	    savpanel.add(sidtxt);
	    	    sframe.setVisible(true);
	    	    
	    	    JLabel dsuccess = new JLabel("");
	    	    dsuccess.setBounds(70,100,200,25);
	    	    savpanel.add(dsuccess);
	    	    
	    	    JButton ssubmit = new JButton("Submit");
	    	    ssubmit.setBounds(100, 70, 80, 25);
	    	    savpanel.add(ssubmit);
	    	    ssubmit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String tempid = sidtxt.getText();
						int check = Integer.parseInt(tempid);
						if(check==StudentID) {
							
							if(newfname.length()==0) {
								newfname = Fname;			
							}if(newlname.length()==0) {
								newlname = Lname;
							}if(newadd.length()==0){
								newadd = addd;
							}if(newphone.length()==0){
								newphone = phonen;
							}if(newemail.length()==0) {
								newemail= Emaill;
							}if(newuser.length()==0){
								newuser = User;
							}if(newpass.length()==0) {
								newpass = Pass;
							}
							try {
					        	Connection con = DriverManager.getConnection(jdbcURL, username, password);
								PreparedStatement st = (PreparedStatement)con.prepareStatement("UPDATE students SET "
										+ "FirstName=?, LastName=?,Address=?, PhoneNumber=?, Email=?, user=?, pass=? where StudentID=?");
								st.setString(1, newfname);
								st.setString(2, newlname);
								st.setString(3, newadd);
								st.setString(4, newphone);
								st.setString(5, newemail);
								st.setString(6, newuser);
								st.setString(7, newpass);
								st.setInt(8,StudentID);
								int rows = st.executeUpdate();
								if(rows>0) {
								dsuccess.setText("Succesfully updated.");
								sframe.setVisible(false);
								Fname = newfname;
								Lname = newlname;
								rname.setText("Welcome "+Fname+" "+Lname+"!");
								System.out.println("AYY ");
							}else{
								System.out.println("Failed to update. Try again.");
				            }
							con.close();
				        	
					        }catch (SQLException ex) {
					        	ex.printStackTrace();
					        }
						}else {
							dsuccess.setText("Incorrect ID.");
						}
						
					}
	    	    
	    	    });
	    		
	    	}  	
	    });
	    
        
        
        
        
        frame.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			new UpdatePage(User, Pass);
		
	}

}
