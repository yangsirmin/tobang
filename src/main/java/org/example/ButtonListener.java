package org.example;

//设置按钮监听方法ButttonLitener类
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

//实现对JPanel的监听接口处理
public class ButtonListener implements GoBangconfig,ActionListener{
	public GoBangframe gf;
	static int q=0;
	public ButtonListener(GoBangframe gf) {
		this.gf=gf;//获取左半部分的画板
	}
	//当界面发生操作时进行处理
	public void actionPerformed(ActionEvent e) {
		//获取当前被点击按钮的内容，判断是不是"开始新游戏"这个按钮
		if(e.getActionCommand().equals("开始新游戏")) {
			//如果是开始新游戏的按钮，再为左半部分设置监听方法
			    q++;
			    gf.textA.setText("  开始新游戏!");
				frameListener fl=new frameListener();
			    fl.setGraphics(gf);//获取画笔对象
		        gf.addMouseListener(fl);//监听鼠标
		}
		//判断当前点击的按钮是不是悔棋
		else if(e.getActionCommand().equals("悔棋")) {
			if(gf.ChessPositonList.size()>1) {
				//把棋子数组相应的位置置为0；
				ChessPosition l = new ChessPosition();
				//获取最后一个棋子的对象信息
				l = gf.ChessPositonList.remove(gf.ChessPositonList.size()-1);
				//把相应的数组位置置为0
				gf.isAvail[l.Listi][l.Listj]=0;
				//把玩家还原为上一步的玩家
				if(gf.turn==1) gf.turn++;
				else gf.turn--;
				gf.textA.setText(" ");
				
				//直接调用gf的重绘方法，重绘方法的画笔应该是在棋盘页面还没生成的时候就要获取
				//调用repaint会自动调用paint方法，而且不用给参数
				gf.repaint();
			}
			else { 
				gf.textA.setText("   不能悔棋!");
				//System.out.println("不能悔棋!");
			}
		}
		else if(e.getActionCommand().equals("认输")) {
			if(gf.turn==1) gf.textA.setText("    白方赢!");//System.out.println("白方赢");
			else gf.textA.setText("    黑方赢!"); //System.out.println("黑方赢");
		}
	}
}