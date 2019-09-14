package test;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

//JPanel 画笔描绘背景图
public class BGPanel extends JPanel {

	 private static final long serialVersionUID = -6352788025440244338L;  
     
	    private Image image = null;
	    
		public BGPanel(Image image) {
			this.image = image;
		}
	 
		// 固定背景图片，允许这个JPanel可以在图片上添加其他组件
		protected void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
