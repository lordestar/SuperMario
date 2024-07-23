package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//此类作废。。。。。。从开始菜单打开游戏界面会导致游戏界面无法正常显示

public class StartMenuPanel extends JFrame {
    // 游戏状态 0 游戏未开始，1 单人模式，2 双人模式
    private static int state = 0;
    private boolean start = false;
    private int selectedMode = 1;
    private final int width = 800;
    private final int height = 600;
    private final int initialPointerY = 340;
    private int pointerY = initialPointerY;

    private final Image select = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "/src/images/arrowhead.png");
    private final Image bg = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "/src/images/bg.png");
    private final Image title = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "/src/images/title1.png");

    public StartMenuPanel() {
        initUI();
    }

    private void initUI() {
        setTitle("超级玛丽联机版QwQ");
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(new KeyMonitor());
        setVisible(true);

        new StartThread().start();
    }

    private class StartThread extends Thread {
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        Image offScreenImage = createImage(width, height);
        Graphics gImage = offScreenImage.getGraphics();

        // 绘制背景、标题和文字
        gImage.drawImage(bg, 0, 0, null);
        gImage.drawImage(title, 230, 60, null);
        gImage.setColor(Color.BLACK);
        gImage.setFont(new Font("仿宋", Font.BOLD, 40));

        if (state == 0) {
            gImage.drawString("选择游戏模式", 265, 300);
            gImage.drawString("单人游戏", 300, 370);
            gImage.drawString("双人游戏", 300, 450);
            gImage.drawString("按1，2选择模式，按回车开始游戏", 100, 550);
            gImage.drawImage(select, 250, pointerY, null);
        } else if (state == 1 || state == 2) {
           if (state == 1){
               // 单人模式
               dispose();
               MyFrame myFrame = new MyFrame();
           }else {
               // 双人模式
               dispose();
           }
        }

        g.drawImage(offScreenImage, 0, 0, null);
    }

    private class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (!start) {
                switch (key) {
                    case KeyEvent.VK_1:
                        pointerY = initialPointerY;
                        selectedMode = 1;
                        break;
                    case KeyEvent.VK_2:
                        pointerY = initialPointerY + 81;
                        selectedMode = 2;
                        break;
                }
            }

            if (key == KeyEvent.VK_ENTER) {
                state = selectedMode;
                start = true;
                System.out.println("state:" + state);
            }
        }
    }

}


