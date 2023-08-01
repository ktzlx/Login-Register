package net.codejava;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class gui extends JFrame  {
	
	static JLabel userlabel;
    static JTextField userText;
  
    static JLabel passlabel;
    static JPasswordField passText;
    static JButton login;
    static JButton register;
    static JLabel success;
    static JFrame frame;
    static JPanel panel;
    static JLabel page;
    
    String jdbcURL = "jdbc:mysql://localhost:3306/dbproject";
	String username = "sqluser";
	String password = "password";
	
	public gui() {
		panel = new JPanel();
        frame = new JFrame();
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        page = new JLabel("Login page");
        page.setBounds(220, 20, 165, 25);
        panel.add(page);

        userlabel = new JLabel("User");
        userlabel.setBounds(100, 70, 80, 25);
        panel.add(userlabel);

        userText = new JTextField(20);
        userText.setBounds(175, 70, 165, 25);
        panel.add(userText);

        passlabel = new JLabel("Password");
        passlabel.setBounds(100, 120, 80, 25);
        panel.add(passlabel);

        passText = new JPasswordField();
        //JTextField passText = new JTextField(20);
        passText.setBounds(175, 120, 165, 25);
        panel.add(passText);

        login = new JButton("Login");
        login.setBounds(210, 160, 85, 25);
        login.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		String user = userText.getText();
        		String pass = passText.getText();
    
    			try {
    				Connection con = DriverManager.getConnection(jdbcURL, username, password);
    				PreparedStatement st = (PreparedStatement)con.prepareStatement("Select user, pass from students where user=? and pass=?");
    				st.setString(1, user);
    				st.setString(2, pass);
    				ResultSet rs = st.executeQuery();
    				if(rs.next()) {
    					success.setText("Logging in...");
    					UpdatePage m = new UpdatePage(user, pass);
    					frame.setVisible(false);
    				}else{
    	                success.setText("Incorrect credentials. Try again.");
    	            }
    				con.close(); 				
    			}catch(SQLException ex){
    				ex.printStackTrace();
    			}
        	}
        });
        panel.add(login);

        register = new JButton("Register");
        register.setBounds(210, 190, 85, 25);
        register.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("register page");
        		rpage register = new rpage();
        		frame.setVisible(false);
        	}
        });
        panel.add(register);

        success = new JLabel("");
        success.setBounds(160,220,300,25);
        panel.add(success);
        
        frame.setVisible(true);
       
	}
    public static void main(String[] args){
    	new gui();
    }
}
