package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MyFrame extends JFrame implements KeyListener {
    private GamePanel1 gamePanel;

    public MyFrame() {
        // 设置窗口
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("超级玛丽联机版QwQ");

        // 初始化游戏面板
        gamePanel = new GamePanel1();
        // this.getContentPane().add(gamePanel,BorderLayout.CENTER);
        this.add(gamePanel);

        // 添加键盘监听
        this.addKeyListener(this);


        this.setVisible(true);


        // 启动线程
        new StartThread().start();

        // 播放音乐
        try {
            new Music(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class StartThread extends Thread {
        public void run() {
            while (true) {
                gamePanel.repaint();

                try {
                    Thread.sleep(16);

                    // 到达最右边进入下一个场景
                    if (gamePanel.mario1.getX() >= 775) {
                        gamePanel.nowBg = gamePanel.allBg.get(gamePanel.nowBg.getSort());
                        gamePanel.mario1.setBackGround(gamePanel.nowBg);
                        gamePanel.mario1.setX(10);
                        gamePanel.mario1.setY(355);
                    }

                    // 判断马里奥是否死亡
                    if (gamePanel.mario1.isDead()) {
                        JOptionPane.showMessageDialog(MyFrame.this, "你死了QAQ~");
                        System.exit(0);
                    }
                    // 判断游戏是否结束
                    if (gamePanel.mario1.isOK()) {
                        JOptionPane.showMessageDialog(MyFrame.this, "恭喜你成功通关~");
                        System.exit(0);
                    }
                    } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Mario2到达最右边进入下一个场景
                try {
                    if (gamePanel.mario2.getX() >= 775) {
                        gamePanel.nowBg = gamePanel.allBg.get(gamePanel.nowBg.getSort());
                        gamePanel.mario2.setBackGround(gamePanel.nowBg);
                        gamePanel.mario2.setX(10);
                        gamePanel.mario2.setY(355);
                    }

                    // 判断马里奥是否死亡
                    if (gamePanel.mario2.isDead()) {
                        JOptionPane.showMessageDialog(MyFrame.this, "你死了QAQ~");
                        System.exit(0);
                    }
                    // 判断游戏是否结束
                    if (gamePanel.mario2.isOK()) {
                        JOptionPane.showMessageDialog(MyFrame.this, "恭喜你成功通关~");
                        System.exit(0);
                    }
                } catch (HeadlessException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    // 重写 KeyListener 中的方法
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        // 移动
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            gamePanel.mario1.rightMove();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            gamePanel.mario1.leftMove();
        }
        // 跳跃
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            gamePanel.mario1.jump();
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            gamePanel.mario2.rightMove();
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            gamePanel.mario2.leftMove();
        }
        // 跳跃
        if (e.getKeyCode() == KeyEvent.VK_W) {
            gamePanel.mario2.jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            gamePanel.mario1.rightStop();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            gamePanel.mario1.leftStop();
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            gamePanel.mario2.rightStop();
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            gamePanel.mario2.leftStop();
        }
    }

//    public static void main(String[] args) {
//        new MyFrame();
//    }
}

