/*
JDBC - �ڹ� ����� �����ͺ��̽��� �����ϴ� ����� ����Ų��.
Java Database Connectivity
java.sql ��Ű���� ������
*/

package org.sp.project0713.member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

class JoinForm extends JFrame implements ActionListener {
	JTextField t_id;
	JTextField t_name;
	JTextField t_phone;
	JButton bt_connect; //�����ͺ��̽� ���� ��ư
	JButton bt_regist; //��Ϲ�ư
	String url="jdbc:mysql://localhost:3306/javase?characterEncoding=utf8";
	String url2 ="jdbc:oracle:thin:@localhost:1521:XE";
	//Connection ��ü��? ���� ������ �ϸ� �� ���������� ������ ��ü
	Connection con=null;

	public JoinForm(){
		t_id				= new JTextField();
		t_name			= new JTextField();
		t_phone			= new JTextField();
		bt_connect		= new JButton("����");
		bt_regist		= new JButton("����");
		
		Dimension d = new Dimension(280, 40);
		t_id.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_phone.setPreferredSize(d);
	
		setLayout(new FlowLayout());				

		add(t_id);
		add(t_name);
		add(t_phone);
		add(bt_connect);
		add(bt_regist);

		setSize(300,400);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);

		//��ư��� ������ ����
		bt_connect.addActionListener(this);
		bt_regist.addActionListener(this);

		bt_regist.setEnabled(false);//��Ȱ��ȭ

		this.addWindowListener(new WindowAdapter(){

			public void windowClosing(WindowEvent e){
				//1)�����ִ� Connection �ݱ�
				if(con !=null){
					try{
						con.close();
					}catch(SQLException e2){
						e2.printStackTrace();
					}
				}

				//2)���μ����� ����
				System.exit(0);
			}
			
		});
	}

	//MySQL DB�� ������ �õ��غ���.
	public void connect(){
		//MySQL �帮�ƹ��� �ε��Ѵ�.
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("����̹� �ε� ����");

			//������ �õ��Ѵ�.

			
			

			//con=DriverManager.getConnection(url,"root","1234");
			con=DriverManager.getConnection(url2,"java", "1234");
			if(con==null){
				System.out.println("���ӽ���");
			}else{
				System.out.println("���Ӽ���");

				//���ӹ�ư ��Ȱ��ȭ +���Թ�ư Ȱ��ȭ
				bt_connect.setEnabled(false);
				bt_regist.setEnabled(true);

			}

		}catch(ClassNotFoundException e){
			System.out.println("����̹��� �������� �ʽ��ϴ�.");
		}catch(SQLException e){
			e.printStackTrace();
			
		}
	}

	//����ϱ�
	public void regist(){
		//�������� �����غ���
		String id= t_id.getText();
		String name=t_name.getText();
		String phone=t_phone.getText();
		String sql="insert into member(id, name, phone) values('"+id+"','"+name+"','"+phone+"')";

		//������ ������ ����ϴ� jdbc ��ü
		PreparedStatement pstmt=null;
		try{
			pstmt=con.prepareStatement(sql);

			//�غ�� �������� �����ϱ�
			//excuteUpdate() �޼���� DML�� ������ �� �ִµ� �̶� �������� 
			//�� �������࿡ ���� ������ ���� ���ڵ��� ���� ��ȯ�Ѵ�. ���� �����ڴ� 
			//�� ����� 0�̸� DML ������ �ȵǾ��ٴ� ���� �˼� �ִ�.
			int result=pstmt.executeUpdate();
			if(result>0){
				JOptionPane.showMessageDialog(this, "���Լ���");
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		
	}

	//����Ŭ�� insert �ϱ�
	public void registOracle(){
		PreparedStatement pstmt=null;
		String sql="insert into member (member_idx, id, name, phone)";
		sql=sql+" values (seq_member.nextval, 'batman', 'bat', '011')";
		try{
			pstmt=con.prepareStatement(sql);//������ �غ�
			int result=pstmt.executeUpdate();
			if(result>0){
				JOptionPane.showMessageDialog(this, "����Ŭ ��� ����");
			}

		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	public void actionPerformed(ActionEvent e){
		Object obj=e.getSource();
		if(obj==bt_connect){//���ӹ�ư�� ������
			connect();
		}else if(obj==bt_regist){//��Ϲ�ư�� ������
			registOracle();
		}
	}

	public static void main(String[] args) {
		new JoinForm();
	}
}