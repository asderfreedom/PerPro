package test;

import java.util.Random;

class Node
{
	private int content;
	private Node lNode;
	private Node rNode;
	public Node()
	{
		this.content=0;
		this.lNode=null;
		this.rNode=null;
	}
	public Node(int a)
	{
		this.content=a;
		this.lNode=null;
		this.rNode=null;
	}
	public int getContent()
	{
		return this.content;
	}
	public void addl(Node a)
	{
		this.lNode=a;
	}
	public void addr(Node a)
	{
		this.rNode=a;
	}
	public void setContent(int a)
	{
		this.content=a;
	}
	public Node getl()
	{
		return this.lNode;
	}
	public Node getr()
	{
		return this.rNode;
	}
}
public class crePros {
	private Node root;		//根节点
	private String str;
	crePros()
	{
		this.root=new Node();
		this.str="";
	}
	public Node getRoot()
	{
		return this.root;
	}
	public  void create(Node a,int num)//,int co		//随机运算符二叉树生成
	{
		String str="";
		Random ra=new Random();
		int t=ra.nextInt(num+1);
		int r=num-t;
		//a.setContent(ra.nextInt(co)+1);
		a.setContent(ra.nextInt(4)+1);
		Node ln=new Node();
		Node rn=new Node();
		if(t>0)
		{
			//ln.setContent(ra.nextInt(co)+1);
			//ln.setContent(ra.nextInt(4)+1);
			a.addl(ln);
			//create(ln,t-1,co);
			create(ln,t-1);
		}
		if(r>0)
		{
			//rn.setContent(ra.nextInt(co)+1);
			//rn.setContent(ra.nextInt(4)+1);
			a.addr(rn);
			//create(rn,r-1,co);
			create(rn,r-1);
		}
	}
	public String getStr()
	{
		return this.str;
	}
	public void setString()
	{
		this.str="";
	}
	public void PrimidOrder(Node a,int co,int ty)		//根据运算符遍历生成合格的运算表达式
	{
		int u1=new Random().nextInt(6)+1;
		if(a==null)
		{	
			if(ty==1||ty==2)
			{
				if(u1==1)
				{
					System.out.print("√");
					str=str+"√";
				}
			}
			if(ty==2)
			{
				switch(u1)
				{
					case 1:str=str+"sin";break;
					case 2:str=str+"cos";break;
					case 3:str=str+"tan";break;
					default:break;
				}			
			}
			int u=new Random().nextInt(100)+1;
			str=str+u;
			if((ty==1||ty==2)&&u1==5)
			{
				str=str+"^2";
				System.out.print("^2");
			}
			System.out.print(u);
			return ;
		}
		if(a.getl()!=null&&comLM(a.getl().getContent(),a.getContent()))
		{
			if(ty==1||ty==2)
			{
				if(u1==1)
				{
					System.out.print("√");
					str=str+"√";
				}
			}
			str=str+"(";
		}
		PrimidOrder(a.getl(),co,ty);
		if(a.getl()!=null&&comLM(a.getl().getContent(),a.getContent()))
		{
			str=str+")";
			if(ty==1&&u1==5)
			{
				str=str+"^2";
				System.out.print("^2");
			}
		}
		if(co==4)				//小学四个，比例为1 1 1 1
		{
			switch(a.getContent())
			{
				case 1:str=str+"+";System.out.print("+");break;
				case 2:str=str+"-";System.out.print("-");break;
				case 3:str=str+"*";System.out.print("*");break;
				case 4:str=str+"/";System.out.print("/");break;
				default:break;
			}
		}
		
		
		if(a.getr()!=null&&comMR(a.getContent(),a.getr().getContent()))
		{
			if(ty==1||ty==2)
			{
				if(u1==1)
				{
					System.out.print("√");
					str=str+"√";
				}
			}
			str=str+"(";
		}	
		PrimidOrder(a.getr(),co,ty);
		if(a.getr()!=null&&comMR(a.getContent(),a.getr().getContent()))
		{
			str=str+")";
			if(ty==1&&u1==5)
			{
				str=str+"^2";
				System.out.print("^2");
			}
		}
	}
	boolean comLM(int l,int m)		//比较左中的运算符判断是否添加括号,平级或高级不加，低级加括号
	{
		if(l<=2&&m>=3)
			return true;
		return false;
	}
	boolean comMR(int m,int r)		//比较在中右运算符判断是否添加括号，中间为-与除特殊判断，其他只要中间比右边运算符高级，就会加
	{
		if(m>r)
			return true;
		else if(m==r&&(m==2||m==4))
			return true;
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		crePros c1=new crePros();
		c1.create(c1.getRoot(),3);
		//String str="";
		for(int i=0;i<10;i++)
		{
			c1.PrimidOrder(c1.getRoot(),4,0);
			System.out.println("\n"+c1.getStr());
			c1.setString();
		}
		
	}

}
