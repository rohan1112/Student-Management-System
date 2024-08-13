import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Toolkit;

public class studentmarks extends JFrame {

	private JPanel contentPane;
	private JTextField txtstudentid;
	private JTextField txtstudentname;
	private JTable table;
	private JLabel lblNewLabel_2;
	private JTextField txtrollno;
	private JLabel lblNewLabel_3;
	private JTextField txtsubject1;
	private JLabel lblNewLabel_4;
	private JTextField txtsubject2;
	private JLabel lblNewLabel_5;
	private JTextField txtsubject3;
	private JLabel lblNewLabel_6;
	private JTextField txtsubject4;

	/**
	 * Launch the application.
	 */

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					studentmarks frame = new studentmarks();
					frame.setVisible(true);	
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection con;
	PreparedStatement pst;

	ResultSet rs;
	private JTextField txtdepartment;
	public void Connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/studms","root","");	
		}
		catch (ClassNotFoundException ex)
		{
			
		}
		catch (SQLException ex)
		{
			
		}
	}
	
	public void table_load() {
		try {
			pst = con.prepareStatement("select*from studentmarks order by studentid");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			table.setRowHeight(30);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public studentmarks() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Rohan\\Downloads\\edu.png"));
		setTitle("Marks Filling");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Connect();
				table_load();
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 70, 930, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fill Student Marks");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(333, 10, 250, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Student Id");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(81, 110, 139, 37);
		contentPane.add(lblNewLabel_1);
		
		txtstudentid = new JTextField();
		txtstudentid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Connect();
				
				try {
				String studentid=txtstudentid.getText();
				
				pst = con.prepareStatement("select studentdata.studentid,studentdata.department,studentdata.studentname,studentdata.rollno,studentmarks.subject1,studentmarks.subject2,"
						+ "studentmarks.subject3,studentmarks.subject4 from studentdata INNER JOIN studentmarks ON studentdata.studentid=studentmarks.studentid WHERE studentdata.STUDENTID=?;");
				pst.setString(1,studentid);
				ResultSet rs = pst.executeQuery();
				
				if(rs.next()==true) {
					
					studentid = rs.getString(1);
					String department = rs.getString(2);
					String studentname = rs.getString(3);
					String rollno = rs.getString(4);
					String subject1 = rs.getString(5);
					String subject2 = rs.getString(6);
					String subject3 = rs.getString(7);
					String subject4 = rs.getString(8);
				
					
					txtstudentname.setText(studentname);
					txtrollno.setText(rollno);
					txtdepartment.setText(department);
					txtsubject1.setText(subject1);
					txtsubject2.setText(subject2);
					txtsubject3.setText(subject3);
					txtsubject4.setText(subject4);
				}
				else if(rs.next()==false){
					pst = con.prepareStatement("select studentid,studentname,rollno,department from studentdata where studentid=?");
					pst.setString(1,studentid);
					rs = pst.executeQuery();
					
					if(rs.next()==true) {
						
						studentid = rs.getString(1);
						String studentname = rs.getString(2);	
						String rollno = rs.getString(3);
						String department = rs.getString(4);
					
					
						
						txtstudentname.setText(studentname);
						txtrollno.setText(rollno);
						txtdepartment.setText(department);
						
					}
					else {
						txtstudentname.setText("");
						txtrollno.setText("");
						txtdepartment.setText("");
						txtsubject1.setText("");
						txtsubject2.setText("");
						txtsubject3.setText("");
						txtsubject4.setText("");
					}
					
				}
				else {
					
					
					txtstudentname.setText("");
					txtrollno.setText("");
					txtdepartment.setText("");
					txtsubject1.setText("");
					txtsubject2.setText("");
					txtsubject3.setText("");
					txtsubject4.setText("");
				}
			}
			
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			
			
			}
		});
		txtstudentid.setBounds(223, 117, 163, 25);
		contentPane.add(txtstudentid);
		txtstudentid.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Student Name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(507, 110, 139, 37);
		contentPane.add(lblNewLabel_1_1);
		
		txtstudentname = new JTextField();
		txtstudentname.setEditable(false);
		txtstudentname.setColumns(10);
		txtstudentname.setBounds(649, 117, 163, 25);
		contentPane.add(txtstudentname);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 440, 824, 193);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblNewLabel_2 = new JLabel("Roll No");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(81, 157, 139, 37);
		contentPane.add(lblNewLabel_2);
		
		txtrollno = new JTextField();
		txtrollno.setEditable(false);
		txtrollno.setColumns(10);
		txtrollno.setBounds(223, 164, 163, 25);
		contentPane.add(txtrollno);
		
		lblNewLabel_3 = new JLabel("Subject 1");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(81, 212, 139, 37);
		contentPane.add(lblNewLabel_3);
		
		txtsubject1 = new JTextField();
		txtsubject1.setColumns(10);
		txtsubject1.setBounds(223, 219, 163, 25);
		contentPane.add(txtsubject1);
		
		lblNewLabel_4 = new JLabel("Subject 2");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(81, 259, 139, 37);
		contentPane.add(lblNewLabel_4);
		
		txtsubject2 = new JTextField();
		txtsubject2.setColumns(10);
		txtsubject2.setBounds(223, 266, 163, 25);
		contentPane.add(txtsubject2);
		
		lblNewLabel_5 = new JLabel("Subject 3");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(507, 212, 139, 37);
		contentPane.add(lblNewLabel_5);
		
		txtsubject3 = new JTextField();
		txtsubject3.setColumns(10);
		txtsubject3.setBounds(649, 219, 163, 25);
		contentPane.add(txtsubject3);
		
		lblNewLabel_6 = new JLabel("Subject 4");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(507, 259, 139, 37);
		contentPane.add(lblNewLabel_6);
		
		txtsubject4 = new JTextField();
		txtsubject4.setColumns(10);
		txtsubject4.setBounds(649, 266, 163, 25);
		contentPane.add(txtsubject4);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String studentid,studentname,rollno,department,subject1,subject2,subject3,subject4;
				
				studentid = txtstudentid.getText();
				studentname = txtstudentname.getText();
				rollno = txtrollno.getText();
				department = txtdepartment.getText();
				subject1 = txtsubject1.getText();
				subject2 = txtsubject2.getText();
				subject3 = txtsubject3.getText();
				subject4 = txtsubject4.getText();
				
				int sub1=Integer.parseInt(subject1);
				int sub2=Integer.parseInt(subject2);
				int sub3=Integer.parseInt(subject3);
				int sub4=Integer.parseInt(subject4);
				float total;
				float percent;
				total=(float)(sub1+sub2+sub3+sub4);
				percent=(float)(total/4);
				
				String percentage=String.valueOf(percent);
				try {
					pst = con.prepareStatement("insert into studentmarks(studentid,studentname,rollno,department,subject1,subject2,subject3,subject4,percent) values(?,?,?,?,?,?,?,?,?)");
					pst.setString(1,studentid);
					pst.setString(2,studentname);
					pst.setString(3,rollno);
					pst.setString(4,department);
					pst.setString(5,subject1);
					pst.setString(6,subject2);
					pst.setString(7,subject3);
					pst.setString(8,subject4);
					pst.setString(9,percentage);
					
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Added!!!");
					table_load() ;
					txtstudentid.setText("");
					txtstudentname.setText("");
					txtrollno.setText("");
					txtdepartment.setText("");
					txtsubject1.setText("");
					txtsubject2.setText("");
					txtsubject3.setText("");
					txtsubject4.setText("");
			
					
					
				}
				catch (SQLException e1){
					
					e1.printStackTrace();
				}	
				
			}
		
				
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton.setBounds(147, 339, 120, 53);
		contentPane.add(btnNewButton);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String studentid,studentname,rollno,subject1,subject2,subject3,subject4;
				
				studentid = txtstudentid.getText();
				studentname = txtstudentname.getText();
				rollno = txtrollno.getText();
				subject1 = txtsubject1.getText();
				subject2 = txtsubject2.getText();
				subject3 = txtsubject3.getText();
				subject4 = txtsubject4.getText();
				
				int sub1=Integer.parseInt(subject1);
				int sub2=Integer.parseInt(subject2);
				int sub3=Integer.parseInt(subject3);
				int sub4=Integer.parseInt(subject4);
				float total;
				float percent;
				total=(float)(sub1+sub2+sub3+sub4);
				percent=(float)(total/4);
				
				String percentage=String.valueOf(percent);

				try {
					pst = con.prepareStatement("UPDATE studentmarks SET subject1=?,subject2=?,subject3=?,subject4=?,percent=? where studentid=?");
					pst.setString(1,subject1);
					pst.setString(2,subject2);
					pst.setString(3,subject3);
					pst.setString(4,subject4);
					pst.setString(5,percentage);
					pst.setString(6,studentid);
					
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Updated Sucessfully!!!");
					table_load() ;
					txtstudentid.setText("");
					txtstudentname.setText("");
					txtrollno.setText("");
					txtsubject1.setText("");
					txtsubject2.setText("");
					txtsubject3.setText("");
					txtsubject4.setText("");
			
					
					
				}
				catch (SQLException e1){
					
					e1.printStackTrace();
				}	
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnUpdate.setBounds(298, 339, 146, 53);
		contentPane.add(btnUpdate);
		
		JButton btnNewButton_1_1 = new JButton("DELETE");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String studentid;
				studentid = txtstudentid.getText();
				try {
					pst = con.prepareStatement("delete from studentmarks where studentid=?");
					pst.setString(1,studentid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Deleted Sucessfully!!!");
					table_load() ;
				}
				catch (SQLException e1){
					
					e1.printStackTrace();
				}	
				
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_1_1.setBounds(473, 339, 120, 53);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("RESET");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtstudentid.setText("");
				txtstudentname.setText("");
				txtrollno.setText("");
				txtdepartment.setText("");
				txtsubject1.setText("");
				txtsubject2.setText("");
				txtsubject3.setText("");
				txtsubject4.setText("");
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_1_1_1.setBounds(619, 339, 120, 53);
		contentPane.add(btnNewButton_1_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Department");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(507, 157, 139, 37);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtdepartment = new JTextField();
		txtdepartment.setEditable(false);
		txtdepartment.setColumns(10);
		txtdepartment.setBounds(649, 164, 163, 25);
		contentPane.add(txtdepartment);
		
	}
}
