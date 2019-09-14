package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Fileio {
	private String src1;// 文件路径源

	Fileio() {
		src1 = "";
	}

	Fileio(String src) {
		this.src1 = src;
	}

	public void setSrc(String src1) {
		this.src1 = src1;
	}

	public String getSrc() {
		return this.src1;
	}
	//参照https://www.cnblogs.com/longqingyang/p/5577937.html

	public Document Fread(String src) {
		File file = new File(src);
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//创建一个DocumentBuilderFactory的对象
			DocumentBuilder builder = factory.newDocumentBuilder();//创建DocumentBuilder对象
			Document doc = builder.parse(file);//读取xml文件
			return doc;
		} catch (Exception e) {
			System.err.println("读取该xml文件失败");
			e.printStackTrace();
		}
		return null;
	}

	public void Writexml(String loc, List<String> questions) throws Exception {
		File file = new File(loc);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(file);
		} catch (Exception e) {
			System.err.println("打开该xml文件失败");
			e.printStackTrace();
		}
		Element root = doc.getDocumentElement();
		for (int i = 0; i < questions.size(); i++) {
			Element question = doc.createElement("question");
			System.out.println(questions.get(i));
			question.setTextContent(questions.get(i));
			root.appendChild(question);
		}
		//写入xml文件
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		Source source = new DOMSource(doc);// 获取内存里面的数据（doc ==> document对象）
		Result result = new StreamResult(file);// 获取磁盘上的文件
		transformer.transform(source, result);// 将 XML==>Source 转换为 Result
	}

	public void Writetxt(String loc, List<String> s, String title) throws Exception {
		File file = new File(loc);
		FileWriter fw = new FileWriter(file);
		BufferedWriter out = new BufferedWriter(fw);
		out.write("\t\t" + title + "\r\n");
		for (int i = 0; i < s.size(); i++) {
			out.write(i + 1 + ".      ");
			out.write(s.get(i) + "\r\n\n");
		}
		out.flush();
		out.close();
	}
	public List<String> getList(String loc,String sonTag)
	{
		List<String> str=new ArrayList<String>();
		File file=new File(loc);
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		Document doc=null;
		try 
		{
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(file);
		} 
		catch (Exception e) {
			System.err.println("打开该xml文件失败");
			e.printStackTrace();
		}
		//获得loc文件中所有的sonTag节点组成集合
		NodeList nl=doc.getElementsByTagName(sonTag);
		for(int i=0;i<nl.getLength();i++)
		{
			String re=doc.getElementsByTagName(sonTag).item(i).getFirstChild().getNodeValue();
			System.out.println(re);
			str.add(re);
		}
		return str;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fileio f1=new Fileio("");
		List<String>pros=new ArrayList<String>();
		pros=f1.getList("张三1/store.xml", "question");
		for(int i=0;i<pros.size();i++)
		{
			System.out.println(pros.get(i));
		}
	}

}
