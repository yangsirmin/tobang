package org.example;
//构建五子棋界面GoBangframe类

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GoBangframe extends JPanel implements GoBangconfig {
    public Graphics g;//定义一支画笔
    public int[][] isAvail = new int[19][19];//定义一个二维数组来储存棋盘的落子情况(1黑2白)
    public ArrayList<ChessPosition> ChessPositonList = new ArrayList<>();//保存每一步的落子情况(保存isAvail下标)
    public int turn = 1;//1黑，2白
    JTextArea textA = new JTextArea("点击:开始新游戏(才能开始!!!)", 10, 15);
    JTextArea textB = new JTextArea(" 设计人:杨必超", 0, 15);
    Font f1 = new Font("黑体", Font.BOLD, 15);
    Font f2 = new Font("黑体", Font.BOLD, 15);


    public static void main(String args[]) {
        GoBangframe gf = new GoBangframe();//初始化一个五子棋界面的对象
        gf.initUI();//调用方法进行界面的初始化
    }

    public void initUI() {
        //初始化一个界面,并设置标题大小等属性
        JFrame jf = new JFrame();
        jf.setTitle("五子棋");
        jf.setSize(927, 800);
        jf.setLocationRelativeTo(null);//窗口显示位置
        jf.setDefaultCloseOperation(3);//关闭并退出
        //Constructs a new border layout with no gaps between components.
        jf.setLayout(new BorderLayout());//设置顶级容器JFrame为框架布局

        Dimension dim1 = new Dimension(150, 0);//设置右半部分的大小
        Dimension dim2 = new Dimension(140, 40);//设置右边按钮组件的大小
        Dimension dim3 = new Dimension(800, 0);//设置左半部分的大小

        //实现左边的界面，把GoBangframe的对象添加到框架布局的中间部分
        this.setPreferredSize(dim3);//设置下棋界面的大小
        this.setBackground(Color.GRAY);//设置下棋界面的颜色
        //这里的话直接把左边的画板添加上去，指明是在框架布局的中间版块
        //若放在其他版块会有一些小问题
        jf.add(this, BorderLayout.CENTER);//添加到框架布局的中间部分

        //实现右边的JPanel容器界面
        JPanel jp = new JPanel();
        jp.setPreferredSize(dim1);//设置JPanel的大小
        jp.setBackground(Color.white);//设置右边的界面颜色为白色
        jf.add(jp, BorderLayout.EAST);
        //Constructs a new FlowLayout with a centered alignment
        //and a default 5-unit horizontal and vertical gap.
        jp.setLayout(new FlowLayout());//设置JPanel为流式布局

        //添加作者
        textB.setFont(f2);
        jp.add(textB);

        //接下来我们需要把按钮等组件依次加到那个JPanel上面
        //设置按钮数组
        String[] butname = {"开始新游戏", "悔棋", "认输"};
        JButton[] button = new JButton[3];

        //依次把三个按钮组件加上去
        for (int i = 0; i < butname.length; i++) {
            button[i] = new JButton(butname[i]);
            button[i].setPreferredSize(dim2);
            jp.add(button[i]);
        }

        //添加文本框
        textA.setFont(f1);
        textA.setLineWrap(true);//设置自动换行
        jp.add(textA);

        //按钮监控类
        ButtonListener butListen = new ButtonListener(this);
        //对每一个按钮都添加状态事件的监听处理机制
        for (int i = 0; i < butname.length; i++) {
            button[i].addActionListener(butListen);//添加发生操作的监听方法
        }

        jf.setVisible(true);//显示窗口
    }


    //重写重绘方法,这里重写的是第一个大的JPanel的方法
    public void paint(Graphics g) {
        super.paint(g);

        //重绘出棋盘
        g.setColor(Color.black);
        //40
        for (int i = 0; i < row; i++) {
            g.drawLine(x, y + size * i, x + size * (column - 1), y + size * i);
        }
        for (int j = 0; j < column; j++) {
            g.drawLine(x + size * j, y, x + size * j, y + size * (row - 1));
        }

        //重绘出棋子
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (isAvail[i][j] == 1) {
                    int countx = size * i + 20;
                    int county = size * j + 20;
                    g.setColor(Color.black);
                    /**
                     * Params:
                     * x – the x coordinate of the upper left corner of the oval to be filled.
                     * y – the y coordinate of the upper left corner of the oval to be filled.
                     * width – the width of the oval to be filled.
                     * height – the height of the oval to be filled.
                     */
                    g.fillOval(countx - size / 2, county - size / 2, size, size);
                } else if (isAvail[i][j] == 2) {
                    int countx = size * i + 20;
                    int county = size * j + 20;
                    g.setColor(Color.white);
                    g.fillOval(countx - size / 2, county - size / 2, size, size);
                }
            }
        }
    }
}