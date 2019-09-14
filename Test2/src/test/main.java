package test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Main {
	private int mark;            //临时添加，找机会改，记录当前出卷类型
	private Login log;
	private JFrame Home;			//主界面
	
	private JPanel HBg;				//主界面背景设置
	
	private JLabel userInfo;		//用户姓名
	private JTextField count;		//试卷题目数
	private JTextField userType;	//出卷类型
	private JButton setUserType;	//修改用户类型
	private JButton setCount;		//修改试卷题目数量
	private JTextArea PreView;		//预览框
	Main()
	{
		this.mark=-1;
		/*构件初始化*/
		this.log=new Login();
		this.HBg=new BGPanel(new ImageIcon("2.jpg").getImage());
		
		this.Home=new JFrame();
		this.Home.setSize(800,800);
		this.Home.setLayout(null);
		this.userInfo=new JLabel();
		this.count=new JTextField();
		this.userType=new JTextField();
		this.setUserType=new JButton();
		this.setCount=new JButton();
		this.PreView=new JTextArea();
		
		/*界面布局*/
		this.HBg.setSize(800, 800);
		
		
		this.Home.setLocation(300,10);
		this.userInfo.setBounds(30, 30, 100, 30);
		
		
		this.userInfo.setForeground(Color.white);
		//this.userInfo.setText(a);
		this.count.setBounds(30, 80, 50, 30);
		this.setCount.setBounds(100, 80, 80, 30);
		this.userType.setBounds(30,130,50,30);
		this.setUserType.setBounds(100, 130, 80, 30);
		this.PreView.setBounds(200, 30, 550, 720);
		JScrollPane scroll = new JScrollPane(PreView); 		//添加滚动条
		scroll.setHorizontalScrollBarPolicy( 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(200, 30, 550, 720);
		
		
		JPanel LeftBelow=new BGPanel(new ImageIcon("5.jpg").getImage());
		LeftBelow.setBounds(0, 200,200,600);
		this.HBg.add(LeftBelow);
		
		this.PreView.setEditable(false);
		this.setCount.setText("生成");
		this.setUserType.setText("切换");
		this.Home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.HBg.setLayout(null);
		this.HBg.add(this.userInfo);
		this.HBg.add(this.count);
		this.HBg.add(userType);
		this.HBg.add(this.setCount);
		this.HBg.add(this.setUserType);
		this.HBg.add(scroll);
		
		
		this.Home.add(HBg);
//		this.Home.add(this.userInfo);
//		this.Home.add(this.count);
//		this.Home.add(userType);
//		this.Home.add(this.setCount);
//		this.Home.add(this.setUserType);
//		this.Home.add(scroll);
		//this.Home.add(new JScrollPane(this.PreView));
		this.Home.setVisible(false);
	}
	public void setHomeTrue()		//主界面可见
	{
		this.Home.setVisible(true);
	}
	public void setHomeFalse()		//主界面不可见
	{
		this.Home.setVisible(false);
	}
	public void SUserInfo(String a)	//设置用户信息显示
	{
		this.userInfo.setText(a);
	}
	public  static List<user> getUsers()	//得到用户列表
	{
		Fileio fo=new Fileio("");			//工具类，用于文件的读写操作
		List<user> users=new ArrayList<user>();
		Document doc=fo.Fread("teachers.xml");
		NodeList UserList = doc.getElementsByTagName("usa");
		for(int i=0;i<UserList.getLength();i++)
		{
			String na = doc.getElementsByTagName("name").item(i).getFirstChild().getNodeValue();
			String pa=doc.getElementsByTagName("pass").item(i).getFirstChild().getNodeValue();
			String ty=doc.getElementsByTagName("mark").item(i).getFirstChild().getNodeValue();
			int typ=Integer.parseInt(ty);
			user us=new user(na,typ,pa);
			users.add(us);
		}
		return users;
	}
	public void writeToPreView(List<String>a,String b)		//预览框处理
	{
		this.PreView.setText(""); 				//初始化
		PreView.append("\t\t" + b + "\r\n");
		for(int i=0;i<a.size();i++)
		{
			String str=a.get(i);
			PreView.append("("+(i+1)+"). "+str+"\n\n");
		}
	}
	public void logAddAction(List<user> users)		//登陆按钮添加登陆事件
	{
		this.log.LogAddAc(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				boolean flag=false;
				for(int i=0;i<users.size();i++)
				{
					String nameStr=users.get(i).getuserName();
					int tyInt=users.get(i).getType();
					if(log.NaMached(nameStr)&&log.PassMached(users.get(i).getuserPass()))
					{
						flag=true;
						Home.setVisible(true);
						String str=nameStr;
						switch(tyInt)
						{
							case 0:str=str+"\t 小学";mark=0;break;
							case 1:str=str+"\t 初中";mark=1;break;
							case 2:str=str+"\t 高中";mark=2;break;
							default:break;
						}
						userInfo.setText(str);
						log.Hide();
					}
				}
				if(!flag)	
				{
					JOptionPane.showMessageDialog(null, "用户名或密码错误", " 请输入正确的用户名、密码", JOptionPane.ERROR_MESSAGE);
					log.setNameText("");
					log.setPassText("");
				}
			}	
			
		});
	}
	public void CanAddAction()			//注销按钮添加注销事件
	{
		this.log.cancelAddAc(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				System.exit(0);			//退出整个程序
			}
			
		});
	}
	public void SUTAddAction()		//切换用户按钮添加切换事件
	{
		this.setUserType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String str=userType.getText();		//获得切换类型
				userType.setText("");
				if(!str.equals(""))
				{
					if(str.equals("小学"))
					{
						mark=0;
						userInfo.setText(log.getName()+"\t小学");
					}
					else if(str.equals("初中"))
					{
						mark=1;
						userInfo.setText(log.getName()+"\t初中");
					}
					else if(str.equals("高中"))
					{
						mark=2;
						userInfo.setText(log.getName()+"\t高中");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "输入错误", " 请输入小学、初中和高中三个选项中的一个", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});
	}
	public void SCountAddAction()		//生成按钮添加生成事件
	{
		this.setCount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String countStr=count.getText();
				int num=1;
				
				System.out.print(countStr);
				
				if(!countStr.equals(""))
				{
					num=Integer.parseInt(countStr);		//获得输入的题目数目
				}
				if(num==-1)				//-1,退出登录，回到登陆界面 
				{
					setHomeFalse();
					log.Show();
				}
				else if(num<10||num>30)		//错误输入，弹出提示信息
				{
					JOptionPane.showMessageDialog(null, "输入错误", " 请输入10-30之内的数据或者-1",
							JOptionPane.ERROR_MESSAGE);
				}
				else						//生成试卷
				{
					Fileio f1=new Fileio("");
					String loc=log.getName()+"/store.xml";
					List<String>pros=new ArrayList<String>();
					List<String>Cpros=new ArrayList<String>();
					pros=f1.getList(loc, "question");		//获得题库中的所有题目
					user u1=new user(log.getName(),mark);
					for(int i=0;i<num;i++)
					{
						//String str=u1.Create();
						String str=u1.CtreateByBT();			//获得生成的题目
						for(int j=0;j<pros.size();j++)
						{
							String s1=pros.get(j);		//发现重复，跳过
							if(str.equals(s1))
							{
								i--;
								continue;
							}
						}
						Cpros.add(str);					//没有重复，添加到生成列表中					
					}
					String StoLoc=log.getName();
					String title="";
					Date date = new Date();
					SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
					String ToUrl = Format.format(date);
					switch(mark)
					{
						case 0:StoLoc=StoLoc+"/小学/"+ToUrl;title="小学";break;
						case 1:StoLoc=StoLoc+"/初中/"+ToUrl;title="初中";break;
						case 2:StoLoc=StoLoc+"/高中/"+ToUrl;title="高中";break;
						default:break;
					}
					try {
						f1.Writetxt(StoLoc+".txt", Cpros, log.getName()+"的"+title+"试卷");
						f1.Writexml(log.getName()+"/store.xml", Cpros);
						writeToPreView(Cpros,log.getName()+"的"+title+"试卷");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		});
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Fileio fo=new Fileio("");			//工具类，用于文件的读写操作
		List<user> users=Main.getUsers();	//获得所有用户信息
		for(int i=0;i<users.size();i++)
		{
			users.get(i).print();
		}
		Main m1=new Main();
		m1.logAddAction(users);
		m1.CanAddAction();
		m1.SCountAddAction();
		m1.SUTAddAction();
	}

}
