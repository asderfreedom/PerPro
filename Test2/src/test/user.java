package test;

import java.util.Random;

public class user {
	private String userName;
	private int type;
	private int mNum;
	private int hNum;
	private String userPass;
	user()
	{
		userName="";
		type=-1;
		mNum=0;
		hNum=0;
		userPass="";
	}
	user(String a,int b)
	{
		userName=a;
		type=b;
	}
	user(String a,int b,String c)
	{
		userName=a;
		type=b;
		userPass=c;
	}
	public String getuserPass()
	{
		return this.userPass;
	}
	public String getuserName()
	{
		return this.userName;
	}
	public void setuserName(String a)
	{
		this.userName=a;
	}
	public int getType()
	{
		return this.type;
	}
	public void setType(int a)
	{
		this.type=a;
	}
	public String CtreateByBT()		//使用二叉树生成指定的等式
	{
		crePros a=new crePros();
		a.create(a.getRoot(), (new Random().nextInt(3)+2));
		a.PrimidOrder(a.getRoot(),4,this.type);
		return a.getStr()+"=";
	}
	public String Middle(String a)
	{
		String str=a;
		if(this.type>0)
		{
			Random rand=new Random();
			int flag=rand.nextInt(10)+1;
			switch(flag) 
			{
				case 9:str="√"+a;mNum++;break;
				case 10:str=str+"^2";mNum++;break;
				default:break;
			}
		}	
		return str;
	}
	public String High()
	{
		String str="";
		if(this.type>1)
		{
			Random rand=new Random();
			int flag=rand.nextInt(10)+1;
			switch(flag)
			{
				case 8:str="cos";hNum++;break;
				case 9:str="sin";hNum++;break;
				case 10:str="tan";hNum++;break;
				default:break;
			}
		}
		return str;
	}
	public String Create() 
	{
		String result = "";
		Random ra = new Random();
		int co = ra.nextInt(4) + 2;// 生成2-5之间的随机数
		int count = 1;// 用来记录是第几个数字
		int kuo = ra.nextInt(2) + 1;
		int end = -1;
		int begin = -1;
		if (kuo == 1) // 有括号
		{
			end = ra.nextInt(co - 1) + 2;
			begin = ra.nextInt(end - 1) + 1;
		}
		while (co > 1) {
			String str0="";
			if (begin != -1 && begin == count) {
				str0 += '(';
			}
			int j = ra.nextInt(100) + 1;
			str0 = str0 + j;
			if (end == count) {
				str0 += ')';
			}
			str0=Middle(str0);
			str0=High()+str0;
			count++;
			int k = ra.nextInt(4) + 1;
			switch (k) {
			case 1:
				str0 = str0 + '+';
				break;
			case 2:
				str0 = str0 + '-';
				break;
			case 3:
				str0 = str0 + '*';
				break;
			case 4:
				str0 = str0 + '/';
				break;
			default:
				break;
			}
			co--;
			result=result+str0;
		}
		int temp=ra.nextInt(100) + 1;
		if(mNum==0&this.type==1)
		{
			int cover=ra.nextInt(2)+1;
			if(cover==1)
				result=result+"√"+temp;
			else if(cover==2)
				result=result+temp+"^2";
		}
		else if(this.type==1)
		{
			result+=temp;
		}
		if(hNum==0&&this.type==2)
		{
			result=result+temp;
			int cover=ra.nextInt(3)+1;
			switch(cover)
			{
			case 1:result="sin"+result;break;
			case 2:result="cos"+result;break;
			case 3:result="tan"+result;break;
			default:break;
			}
		}
		else if(this.type==2)
		{
			result+=temp;
		}
		if(this.type==0)
		{
			result=result+temp;
		}
		if (end == count) {
			result += ')';
		}
		this.hNum=0;
		this.mNum=0;
		return result + '=';
	}
	public void print()
	{
		System.out.println("姓名："+this.userName);
		System.out.println("密码："+this.userPass);
		System.out.println("等级: "+this.type);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		user u1=new user("b1: ",0,"0");
		user u2=new user("b2: ",1,"0");
		user u3=new user("b3: ",2,"0");
		for(int i=0;i<10;i++)
		{
	//		System.out.print("1231");
			System.out.println(u1.getuserName()+u1.Create());
		}
		for(int i=0;i<10;i++)
		{
			System.out.println(u2.getuserName()+u2.Create());
		}
		for(int i=0;i<10;i++)
		{
			System.out.println(u3.getuserName()+u3.Create());
		}
	}

}
