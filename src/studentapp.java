import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import com.toedter.calendar.JDateChooser;

public class studentapp {

	private JFrame frmStudentManagementSystem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					studentapp window = new studentapp();
					window.frmStudentManagementSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public studentapp() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtstudentid;
	private JTextField txtstudentname;
	private JTextField txtrollno;
	private JTextField txtphoneno;
	private JTextField txtteachername;
	private JTextField txtemail;
	private JTextField txtaddress;
	private JTable table;
	
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
			pst = con.prepareStatement("SELECT * FROM `studentdata` ORDER BY `STUDENTID` ASC");
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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStudentManagementSystem = new JFrame();
		frmStudentManagementSystem.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Rohan\\Downloads\\edu.png"));
		frmStudentManagementSystem.setTitle("Student Management System");
		frmStudentManagementSystem.setBounds(0, 0, 1540, 820);
		frmStudentManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStudentManagementSystem.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 470, 1461, 281);
		frmStudentManagementSystem.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
		
		
		
		JLabel lblNewLabel = new JLabel("Student Management System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(596, 23, 347, 48);
		frmStudentManagementSystem.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Current Course Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setToolTipText("");
		panel.setBounds(24, 83, 659, 133);
		frmStudentManagementSystem.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Department");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 33, 105, 28);
		panel.add(lblNewLabel_1);
		
		JComboBox combobox1 = new JComboBox();
		combobox1.setModel(new DefaultComboBoxModel(new String[] {"Computer", "Mechanical", "Electrical"}));
		combobox1.setBounds(139, 39, 153, 21);
		panel.add(combobox1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Year");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 82, 105, 28);
		panel.add(lblNewLabel_1_1);
		
		JComboBox combobox2 = new JComboBox();
		combobox2.setModel(new DefaultComboBoxModel(new String[] {"2020-21", "2021-22"}));
		combobox2.setBounds(139, 88, 153, 21);
		panel.add(combobox2);
		
		JComboBox combobox3 = new JComboBox();
		combobox3.setModel(new DefaultComboBoxModel(new String[] {"FE", "SE", "TE", "BE"}));
		combobox3.setBounds(465, 40, 153, 21);
		panel.add(combobox3);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Academic Year");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(330, 33, 113, 28);
		panel.add(lblNewLabel_1_1_1);
		
		JComboBox combobox4 = new JComboBox();
		combobox4.setModel(new DefaultComboBoxModel(new String[] {"Semester-1", "Semester-2"}));
		combobox4.setBounds(465, 89, 153, 21);
		panel.add(combobox4);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Semester");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1.setBounds(330, 82, 78, 28);
		panel.add(lblNewLabel_1_1_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Student Class Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(771, 81, 714, 357);
		frmStudentManagementSystem.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MMM/y");
		dateChooser.setBounds(493, 158, 151, 19);
		panel_1.add(dateChooser);
		
		JComboBox combobox6 = new JComboBox();
		combobox6.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		combobox6.setBounds(143, 156, 151, 21);
		panel_1.add(combobox6);
		
		JComboBox combobox5 = new JComboBox();
		combobox5.setModel(new DefaultComboBoxModel(new String[] {"Shift-I", "Shif-II"}));
		combobox5.setBounds(143, 104, 151, 21);
		panel_1.add(combobox5);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Student Id");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(28, 51, 105, 28);
		panel_1.add(lblNewLabel_1_1_2);
		
		txtstudentid = new JTextField();
		txtstudentid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String studentid=txtstudentid.getText();
					
					pst = con.prepareStatement("select department,year,course,semester,studentid,studentname,shift,rollno,gender,dob,email,phoneno,"
							+ "address,teachername from studentdata where studentid=?");
					pst.setString(1,studentid);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next()==true) {
						
						String department = rs.getString(1);
						String year = rs.getString(2);
						String course = rs.getString(3);
						String semester= rs.getString(4);
						studentid = rs.getString(5);
						String studentname = rs.getString(6);
						String shift = rs.getString(7);
						String rollno = rs.getString(8);
						String gender = rs.getString(9);
						String dob= rs.getString(10);
						Date date1=new SimpleDateFormat("dd/MMM/y").parse(dob); 
						String email = rs.getString(11);
						String phoneno = rs.getString(12);
						String address = rs.getString(13);
						String teachername = rs.getString(14);
						
						combobox1.setSelectedItem(department);
						combobox2.setSelectedItem(year);
						combobox3.setSelectedItem(course);
						combobox4.setSelectedItem(semester);
						txtstudentid.setText(studentid);
						txtstudentname.setText(studentname);
						combobox5.setSelectedItem(shift);
						txtrollno.setText(rollno);
						combobox6.setSelectedItem(gender);
						dateChooser.setDate(date1);
						txtemail.setText(email);
						txtphoneno.setText(phoneno);
						txtaddress.setText(address);
						txtteachername.setText(teachername);
					}
					else {
						
						txtstudentname.setText("");
						txtrollno.setText("");
						txtteachername.setText("");
						dateChooser.setDate(null);
						txtemail.setText("");
						txtphoneno.setText("");
						txtaddress.setText("");
						txtteachername.setText("");
	
					}
				}
				catch (SQLException | ParseException e1)
				{
					e1.printStackTrace();
				}
				
				
				
			}
		});
		txtstudentid.setBounds(143, 56, 151, 19);
		panel_1.add(txtstudentid);
		txtstudentid.setColumns(10);
		
		txtstudentname = new JTextField();
		txtstudentname.setColumns(10);
		txtstudentname.setBounds(493, 56, 151, 19);
		panel_1.add(txtstudentname);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Student Name");
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1.setBounds(361, 49, 105, 28);
		panel_1.add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Roll No");
		lblNewLabel_1_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1_1.setBounds(361, 100, 105, 28);
		panel_1.add(lblNewLabel_1_1_2_1_1);
		
		txtrollno = new JTextField();
		txtrollno.setColumns(10);
		txtrollno.setBounds(493, 107, 151, 19);
		panel_1.add(txtrollno);
		
		JLabel lblNewLabel_1_1_2_1_2 = new JLabel("DOB");
		lblNewLabel_1_1_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1_2.setBounds(361, 152, 105, 28);
		panel_1.add(lblNewLabel_1_1_2_1_2);
		
		JLabel lblNewLabel_1_1_2_1_3 = new JLabel("Phone NO");
		lblNewLabel_1_1_2_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1_3.setBounds(361, 203, 105, 28);
		panel_1.add(lblNewLabel_1_1_2_1_3);
		
		txtphoneno = new JTextField();
		txtphoneno.setColumns(10);
		txtphoneno.setBounds(493, 210, 151, 19);
		panel_1.add(txtphoneno);
		
		JLabel lblNewLabel_1_1_2_1_4 = new JLabel("Teacher Name");
		lblNewLabel_1_1_2_1_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1_4.setBounds(361, 264, 105, 28);
		panel_1.add(lblNewLabel_1_1_2_1_4);
		
		txtteachername = new JTextField();
		txtteachername.setColumns(10);
		txtteachername.setBounds(493, 271, 151, 19);
		panel_1.add(txtteachername);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Shift");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_3.setBounds(28, 100, 105, 28);
		panel_1.add(lblNewLabel_1_1_3);
		
		JLabel lblNewLabel_1_1_4 = new JLabel("Gender");
		lblNewLabel_1_1_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_4.setBounds(28, 152, 105, 28);
		panel_1.add(lblNewLabel_1_1_4);
		
		JLabel lblNewLabel_1_1_2_1_2_1 = new JLabel("Email");
		lblNewLabel_1_1_2_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1_2_1.setBounds(28, 203, 105, 28);
		panel_1.add(lblNewLabel_1_1_2_1_2_1);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(143, 208, 151, 19);
		panel_1.add(txtemail);
		
		JLabel lblNewLabel_1_1_2_1_2_2 = new JLabel("Address");
		lblNewLabel_1_1_2_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1_2_2.setBounds(27, 264, 105, 28);
		panel_1.add(lblNewLabel_1_1_2_1_2_2);
		
		txtaddress = new JTextField();
		txtaddress.setColumns(10);
		txtaddress.setBounds(142, 269, 151, 19);
		panel_1.add(txtaddress);
		
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBounds(99, 283, 136, 65);
		frmStudentManagementSystem.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					String department,year,course,semester,studentid,studentname,shift,rollno,gender,dob,email,phoneno,address,teachername;
					
					
					department = combobox1.getSelectedItem().toString();
					year = combobox2.getSelectedItem().toString();
					course = combobox3.getSelectedItem().toString();
					semester = combobox4.getSelectedItem().toString();
					studentid = txtstudentid.getText();
					studentname = txtstudentname.getText();
					shift = combobox5.getSelectedItem().toString();
					rollno = txtrollno.getText();
					gender = combobox6.getSelectedItem().toString();
					SimpleDateFormat date = new SimpleDateFormat("dd/MMM/y");
					dob = date.format(dateChooser.getDate());
					email = txtemail.getText();
					phoneno = txtphoneno.getText();
					address = txtaddress.getText();
					teachername = txtteachername.getText();
					
					if(department.equals("") || year.equals("")  || course.equals("") || semester.equals("")  || studentid.equals("")  || studentname.equals("")  || shift.equals("") 
							|| rollno.equals("")  || gender.equals("")  ||  phoneno.equals("") || address.equals("")  || teachername.equals("") ) {
						JOptionPane.showMessageDialog(null,"Fields Cannot Be Empty");
					}
					else {
						
					
						try {
							pst = con.prepareStatement("insert into studentdata(department,year,course,semester,studentid,studentname,shift,"
									+ "rollno,gender,dob,email,phoneno,address,teachername) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
							pst.setString(1,department);
							pst.setString(2,year);
							pst.setString(3,course);
							pst.setString(4,semester);
							pst.setString(5,studentid);
							pst.setString(6,studentname);
							pst.setString(7,shift);
							pst.setString(8,rollno);
							pst.setString(9,gender);
							pst.setString(10,dob);
							pst.setString(11,email);
							pst.setString(12,phoneno);
							pst.setString(13,address);
							pst.setString(14,teachername);
							
							pst.executeUpdate();
							JOptionPane.showMessageDialog(null,"Record Added!!!");
							table_load();
							
							combobox1.setSelectedItem(department);
							combobox1.requestFocus();
							combobox1.setSelectedIndex(0);
							combobox2.setSelectedIndex(0);
							combobox3.setSelectedIndex(0);
							combobox4.setSelectedIndex(0);
							txtstudentid.setText("");
							txtstudentname.setText("");
							combobox5.setSelectedIndex(0);
							txtrollno.setText("");
							combobox6.setSelectedIndex(0);
							dateChooser.setDate(null);
							txtemail.setText("");
							txtphoneno.setText("");
							txtaddress.setText("");
							txtteachername.setText("");
						}
						catch (SQLException e1){
							 if (e1.getSQLState().startsWith("23")) {
							        JOptionPane.showMessageDialog(null, "Duplicate Student ID / Roll NO");
							    } 
							e1.printStackTrace();
						}
					}
					
				}
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String department,year,course,semester,studentid,studentname,shift,rollno,gender,dob,email,phoneno,address,teachername;
				
				
				department = combobox1.getSelectedItem().toString();
				year = combobox2.getSelectedItem().toString();
				course = combobox3.getSelectedItem().toString();
				semester = combobox4.getSelectedItem().toString();
				studentid = txtstudentid.getText();
				studentname = txtstudentname.getText();
				shift = combobox5.getSelectedItem().toString();
				rollno = txtrollno.getText();
				gender = combobox6.getSelectedItem().toString();
				SimpleDateFormat date = new SimpleDateFormat("dd/MMM/y");
				dob = date.format(dateChooser.getDate());
				email = txtemail.getText();
				phoneno = txtphoneno.getText();
				address = txtaddress.getText();
				teachername = txtteachername.getText();
				
				try {
					pst = con.prepareStatement("UPDATE studentdata set department=?,year=?,course=?,semester=?,studentid=?,"
							+ "studentname=?,shift=?,rollno=?,gender=?,dob=?,email=?,phoneno=?,address=?,teachername=? WHERE studentid=?");
					pst.setString(1,department);
					pst.setString(2,year);
					pst.setString(3,course);
					pst.setString(4,semester);
					pst.setString(5,studentid);
					pst.setString(6,studentname);
					pst.setString(7,shift);
					pst.setString(8,rollno);
					pst.setString(9,gender);
					pst.setString(10,dob);
					pst.setString(11,email);
					pst.setString(12,phoneno);
					pst.setString(13,address);
					pst.setString(14,teachername);
					pst.setString(15,studentid);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Updated Successfully!!!");
					table_load();
					
					combobox1.setSelectedItem(department);
					combobox1.requestFocus();
				}
				catch (SQLException e1){
					
					e1.printStackTrace();
				}	
				
			}
		
		});
		btnUpdate.setBounds(311, 283, 136, 65);
		frmStudentManagementSystem.getContentPane().add(btnUpdate);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnNewButton_1_1 = new JButton("Delete");
		btnNewButton_1_1.setBounds(512, 283, 136, 65);
		frmStudentManagementSystem.getContentPane().add(btnNewButton_1_1);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String studentid;
				studentid = txtstudentid.getText();
				try {
					pst = con.prepareStatement("delete from studentdata where studentid=?");
					pst.setString(1,studentid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Deleted Successfully!!!");
					table_load();
					
					
				}
				catch (SQLException e1){
					
					e1.printStackTrace();
				}	
				
			}
			
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				combobox1.setSelectedIndex(0);
				combobox2.setSelectedIndex(0);
				combobox3.setSelectedIndex(0);
				combobox4.setSelectedIndex(0);
				txtstudentid.setText("");
				txtstudentname.setText("");
				combobox5.setSelectedIndex(0);
				txtrollno.setText("");
				combobox6.setSelectedIndex(0);
				dateChooser.setDate(null);
				txtemail.setText("");
				txtphoneno.setText("");
				txtaddress.setText("");
				txtteachername.setText("");
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnReset.setBounds(211, 378, 136, 65);
		frmStudentManagementSystem.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnExit.setBounds(422, 378, 136, 65);
		frmStudentManagementSystem.getContentPane().add(btnExit);
		
		JButton btnNewButton_1 = new JButton("Log Out");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frmStudentManagementSystem.dispose();
				JFrame logout= new login();
				logout.setVisible(true);
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(1395, 10, 85, 21);
		frmStudentManagementSystem.getContentPane().add(btnNewButton_1);
		
		JMenuBar menuBar = new JMenuBar();
		frmStudentManagementSystem.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Fill Marks");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Marks Entry");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame studentmarks = new studentmarks();
				studentmarks.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Toppers List");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame toppers= new toppers();
				toppers.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
	}

	public void show() {
		// TODO Auto-generated method stub
		studentapp window = new studentapp();
		window.frmStudentManagementSystem.setVisible(true);
	}
}
