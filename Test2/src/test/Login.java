package test;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
	private JFrame login; // 登陆界面框
	private JPanel logBg;
	private JTextField name; // 用户名输入文本框
	private JPasswordField password; // 密码输入文本框
	private JButton logIn; // 登录按钮
	private JButton cancle; // 取消按钮

	Login() {
		login = new JFrame();
		name = new JTextField();
		password = new JPasswordField(6);
		logIn = new JButton("登陆");
		cancle = new JButton("注销");
		
		logBg=new BGPanel(new ImageIcon("1.jpg").getImage());
		
		JLabel HintName = new JLabel("用户名", JLabel.CENTER);
		
		JLabel HintPass = new JLabel("密码", JLabel.CENTER);
		login.setLocation(500, 250);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setSize(400, 400);
		// login.setUndecorated(true);
		login.setResizable(false);
		login.setLayout(null);
		// 自定义布局
		HintName.setForeground(Color.WHITE);
		HintPass.setForeground(Color.white);
		logBg.setSize(400, 400);
		
		HintName.setBounds(50, 100 - 5, 50, 40);
		HintPass.setBounds(50, 150 - 5, 50, 40);
		name.setBounds(100, 100, 180, 30);
		password.setBounds(100, 150, 180, 30);
		logIn.setBounds(100, 220, 60, 30);
		cancle.setBounds(200, 220, 60, 30);
		
		login.add(logBg);
		
		
		logBg.setLayout(null);
		
		logBg.add(name);
		logBg.add(password);
		logBg.add(HintPass);
		logBg.add(HintName);
		logBg.add(logIn);
		logBg.add(cancle);
		
//		login.add(name);
//		login.add(password);
//		login.add(HintPass);
//		login.add(HintName);
//		login.add(logIn);
//		login.add(cancle);
		login.setVisible(true);
	}

	boolean NaMached(String a) // 判断是否名字匹配
	{
		String NaStr = name.getText().toString();
		if (a.equals(NaStr)) {
			return true;
		} else {
			return false;
		}
	}
	public void setNameText(String a)//姓名输入框文字清空
	{
		this.name.setText(a);
	}
	public void setPassText(String a)//密码输入框文字清空
	{
		this.password.setText(a);
	}
	boolean PassMached(String a) // 判断密码是否匹配
	{
		String PassStr = new String(password.getPassword());
		if (a.equals(PassStr)) 
		{
			return true;
		} 
		else 
		{
			return false;
		}
	}

	public void LogAddAc(ActionListener a) //貌似可删除，暂留它一命
	{
		logIn.addActionListener(a);
	}

	public void cancelAddAc(ActionListener a) //貌似无用，且先看看
	{
		cancle.addActionListener(a);
	}

	public String getName() 	//获得输入用户名
	{
		String Name = name.getText().toString();
		return Name;
	}

	public String getPassword()	//获得密码框输入
	{
		String Pass = new String(password.getPassword());
		return Pass;
	}
	public void Hide()			//影藏界面
	{
		this.login.setVisible(false);
	}	
	public void Show()			//显示界面
	{
		this.logIn.setVisible(true);
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Login l = new Login();
		l.Show();
	}

}
