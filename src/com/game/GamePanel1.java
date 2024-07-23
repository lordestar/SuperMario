package com.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel1 extends JPanel {
    // 存储所有的背景
    List<BackGround> allBg = new ArrayList<>();
    // 存储当前的背景
    BackGround nowBg = new BackGround();
    // 马里奥对象
    Mario mario1 = new Mario();
    Mario mario2 = new Mario();

    // 用于双缓存的变量
    private Image offScreenImage = null;

    public GamePanel1() {
        // 初始化图片
        StaticValue.init();
        // 初始化马里奥对象
        mario1 = new Mario(10, 355);
        mario2 = new Mario(10, 355);

        // 创建全部的场景
        for (int i = 1; i <= 3; i++) {
            allBg.add(new BackGround(i, i == 3 ? true : false));
        }

        // 设置第一个场景为当前场景
        nowBg = allBg.get(0);
        mario1.setBackGround(nowBg);
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        if (offScreenImage == null) {
            offScreenImage = createImage(800, 600);
        }
        Graphics graphics = offScreenImage.getGraphics();
        graphics.fillRect(0, 0, 800, 600);
        // 绘制背景
        graphics.drawImage(nowBg.getBgImage(), 0, 0, this);
        // 绘制敌人
        for (Enemy enemy : nowBg.getEnemyList()) {
            graphics.drawImage(enemy.getShow(), enemy.getX(), enemy.getY(), this);
        }
        // 绘制障碍物
        for (Obstacle ob : nowBg.getObstacleList()) {
            graphics.drawImage(ob.getShow(), ob.getX(), ob.getY(), this);
        }
        // 绘制城堡和旗杆
        graphics.drawImage(nowBg.getTower(), 620, 270, this);
        graphics.drawImage(nowBg.getGan(), 500, 220, this);
        graphics.drawImage(nowBg.getLine1(), 200, 100, this);
        graphics.drawImage(nowBg.getLine2(), 200, 20, this);
        // 绘制马里奥
        graphics.drawImage(mario1.getShow(), mario1.getX(), mario1.getY(), this);
        graphics.drawImage(mario2.getShow(), mario2.getX(), mario2.getY(), this);

        // 绘制分数
        Color c = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("黑体", Font.BOLD, 20));
        graphics.drawString("玩家1当前分数：" + mario1.getScore(), 100, 100);
        graphics.drawString("玩家2当前分数：" + mario2.getScore(), 450, 100);
        graphics.setColor(c);

        // 将缓冲区的图片绘制到窗口中
        g.drawImage(offScreenImage, 0, 0, this);
    }


}
