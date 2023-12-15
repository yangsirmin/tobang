package org.example;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
 
//实现对GoBangframe下棋界面的监听接口处理
public class frameListener implements GoBangconfig,MouseListener{
	public GoBangframe gf;
	//public int turn;//判断当前轮到谁了，1表示黑方，2表示白方
	//动态数组对象的实例化
	//public ArrayList<ChessPosition>ChessPositonList=new ArrayList<ChessPosition>();
	
	public void setGraphics(GoBangframe gf) {
		this.gf=gf;
		//清除棋盘的全部信息
		for(int i=0;i<row;i++) 
			for(int j=0;j<column;j++) {
				gf.isAvail[i][j]=0;
				
			}
		gf.ChessPositonList.clear();
		gf.turn=1;
		gf.repaint();//调用paint或者update
	}
	
	
	  public void mouseClicked(MouseEvent e) {
		  int x=e.getX();
		  int y=e.getY();
		  //计算棋子要落在棋盘的哪个交叉点上
		  int countx=(x/40)*40+20;
		  int county=(y/40)*40+20;
		  Graphics g=gf.getGraphics();
		  
		 // System.out.println(gf.isAvail[0][0]);
		  
		  if(gf.isAvail[(countx-20)/40][(county-20)/40]!=0) {
			  if(ButtonListener.q>1) {
				  return;
			  }
			  gf.textA.setText("此处已经有棋子了，请下在其它地方");
			  //System.out.println("此处已经有棋子了，请下在其它地方");
		  }
		  else {
			  //计算棋盘上棋子在数组中相应的位置
			  int colu=(countx-20)/40;
			  int ro=(county-20)/40;
			  
			  if(gf.turn==1) {
				  //先获取要落的地方
				  g.setColor(Color.black);
				  //落子
				  g.fillOval(countx-size/2, county-size/2, size, size);
				  //设置当前位置已经有棋子了,棋子为黑子
				  gf.isAvail[colu][ro]=1;
				  //把当前所下的棋子位置保存在动态数组中
				  gf.ChessPositonList.add(new ChessPosition(colu,ro));
				  gf.turn++;
				  gf.textA.setText("黑方已下:白方手");
				  //判断是否已经出现五颗棋子了
				  //列判断
				  //首先界定数组范围，防止越界
				  int imin=colu-4,imax=colu+4;
				  if(imin<0) imin=0;
				  if(imax>18) imax=18;
				  int count1=0;//判断相连的黑棋子数
				  for(int i=imin;i<=imax;i++) {
					  if(gf.isAvail[i][ro]==1) count1++;
					  //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
					  else count1=0;
					  //
					  if(count1==5) { 
						  gf.textA.setText("    黑方赢!");
						  return;
					  }
				  }
				  //行判断
				  //首先界定数组范围，防止越界
				  int jmin=ro-4,jmax=ro+4;
				  if(jmin<0) jmin=0;
				  if(jmax>18) jmax=18;
				  int count2=0;//判断相连的棋子数
				  for(int j=jmin;j<=jmax;j++) {
					  if(gf.isAvail[colu][j]==1) count2++;
					  //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
					  else count2=0;
					  //
					  if(count2==5) {
						  gf.textA.setText("    黑方赢!");
						  return;
					  }
					  
				  }
				  //左对角线判断
				  //首先界定数组范围，防止越界
				  int count3=0;//判断相连的棋子数
				  for(int i=-4;i<=4;i++) {
					  if((colu+i>=0)&&(ro+i>=0)&&(colu+i<=18)&&(ro+i<=18)) {
						  if(gf.isAvail[colu+i][ro+i]==1) count3++;
							//如果出现了其他棋子，或者是没有棋子时，就重新开始计数
						  else count3=0;
						  if(count3==5) {
							  gf.textA.setText("    黑方赢!");
							  return;
						  }
					  }
				  }
				  //右对角线判断
				  int count4=0;//判断相连的棋子数
				  for(int i=-4;i<=4;i++) {
					  if((colu+i>=0)&&(ro-i>=0)&&(colu+i<=18)&&(ro-i<=18)) {
						  //System.out.print("count4:"+count4);
						  if(gf.isAvail[colu+i][ro-i]==1) count4++;
							//如果出现了其他棋子，或者是没有棋子时，就重新开始计数
						  else count4=0;
						  if(count4==5) {
							  gf.textA.setText("    黑方赢!");
							  return;
						  }
					  }
				  }
			  }
			  //白棋
			  else {
				  g.setColor(Color.white);
				  g.fillOval(countx-size/2, county-size/2, size, size);
				  //设置当前位置已经有棋子了，棋子为白子
				  gf.ChessPositonList.add(new ChessPosition(colu,ro));
				  gf.isAvail[colu][ro]=2;
				  gf.turn--;//置1(为黑棋)
				  gf.textA.setText("白方已下:黑方手");
				  //列判断
				  //首先界定数组范围，防止越界
				  int imin=colu-4,imax=colu+4;
				  if(imin<0) imin=0;
				  if(imax>18) imax=18;
				  int count1=0;//判断相连的白棋子数
				  for(int i=imin;i<=imax;i++) {
					  if(gf.isAvail[i][ro]==2) count1++;
					//如果出现了其他棋子，或者是没有棋子时，就重新开始计数
					  else count1=0;
					  if(count1==5) {
						  gf.textA.setText("    白方赢!");
						  return;
					  }
				  }
				  //行判断
				  //首先界定数组范围，防止越界
				  int jmin=ro-4,jmax=ro+4;
				  if(jmin<0) jmin=0;
				  if(jmax>18) jmax=18;
				  int count2=0;//判断相连的棋子数
				  for(int j=jmin;j<=jmax;j++) {
					  if(gf.isAvail[colu][j]==2) count2++;
					  //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
					  else count2=0;
					  if(count2==5) {
						  gf.textA.setText("    白方赢!");
						  return;
					  } 
				  }
				  //左对角线判断
				  //首先界定数组范围，防止越界
				  int count3=0;//判断相连的棋子数
				  for(int i=-4;i<=4;i++) {
					  if((colu+i>=0)&&(ro+i>=0)&&(colu+i<=18)&&(ro+i<=18)) {
						  if(gf.isAvail[colu+i][ro+i]==2) count3++;
						  //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
						  else count3=0;
						  if(count3==5) {
							  gf.textA.setText("    白方赢!");
							  return;
						  }
					  }
				  }
				  //右对角线判断
				  int count4=0;//判断相连的棋子数
				  for(int i=-4;i<=4;i++) {
					  if((colu+i>=0)&&(ro-i>=0)&&(colu+i<=18)&&(ro-i<=18)) {
						  if(gf.isAvail[colu+i][ro-i]==2) count4++;
						  //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
						  else count4=0;
						  if(count4==5) {
							  gf.textA.setText("    白方赢!");
							  return;
						  }
					  }
				  }
			  }
		  }
	  }
	  
	  public void mousePressed(java.awt.event.MouseEvent e) {
	  }
	  public void mouseReleased(java.awt.event.MouseEvent e) {
	  }
	  public void mouseEntered(java.awt.event.MouseEvent e) {
	  }
	  public void mouseExited(java.awt.event.MouseEvent e) {
	  }
}