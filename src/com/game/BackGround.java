package com.game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
    private BufferedImage bgImage = null; // 当前场景的背景图片
    private int sort; // 记录当前是第几个场景
    private boolean flag; // 判断是不是最后一个场景
    private List<Obstacle> obstacleList = new ArrayList<>(); // 存放所有的障碍物
    private List<Enemy> enemyList = new ArrayList<>(); // 存放所有的敌人
    private BufferedImage gan = null; // 显示旗杆
    private BufferedImage tower = null; // 显示城堡
    private boolean isReach = false; // 是否到达旗杆位置
    private boolean isBase = false; // 旗子是否落地
    private BufferedImage line1 = null;
    private BufferedImage line2 = null;

    public BackGround() {}

    public BackGround(int sort, boolean flag) {
        this.sort = sort;
        this.flag = flag;

        if (flag) {
            bgImage = StaticValue.bg2;
        } else {
            bgImage = StaticValue.bg;
        }

        if (sort == -2) {
            initializeLevelStartScene();
        } else if (sort == -3) {
            initializeRoomSelectionScene();
        } else if (sort == 1) {
            initializeFirstLevel();
        } else if (sort == 2) {
            initializeSecondLevel();
        } else if (sort == 3) {
            initializeThirdLevel();
        }
    }

    private void initializeLevelStartScene() {
        createGroundAndBricks();
        line1 = StaticValue.line1;
        line2 = StaticValue.line2;
    }

    private void initializeRoomSelectionScene() {
        createGroundAndBricks();
    }

    private void initializeFirstLevel() {
        createGroundAndBricks();
        obstacleList.add(new Obstacle(150, 390, 7, this));
        for (int i = 120; i <= 150; i += 30) {
            obstacleList.add(new Obstacle(i, 300, 7, this));
        }
        for (int i = 300; i <= 570; i += 30) {
            if (i == 360 || i == 390 || i == 480 || i == 510 || i == 540) {
                obstacleList.add(new Obstacle(i, 300, 7, this));
            } else {
                obstacleList.add(new Obstacle(i, 300, 0, this));
            }
        }
        for (int i = 420; i < 450; i += 30) {
            obstacleList.add(new Obstacle(i, 240, 7, this));
        }
        createPipes();
        enemyList.add(new Enemy(580, 385, true, 1, this));
        enemyList.add(new Enemy(635, 420, true, 2, 328, 418, this));
    }

    private void initializeSecondLevel() {
        createGroundAndBricks();
        createPipes3();
        createPipes2();
        createSecondLevelBricks();
        enemyList.add(new Enemy(500, 385, true, 1, this));
        enemyList.add(new Enemy(200, 385, true, 1, this));
        enemyList.add(new Enemy(75, 420, true, 2, 328, 418, this));
        enemyList.add(new Enemy(635, 420, true, 2, 298, 388, this));
    }

    private void initializeThirdLevel() {
        createGroundAndBricks();
        int temp = 290;
        for (int i = 390; i >= 270; i -= 30) {
            for (int j = temp; j <= 410; j += 30) {
                obstacleList.add(new Obstacle(j, i, 7, this));
            }
            temp += 30;
        }
        temp = 60;
        for (int i = 390; i >= 360; i -= 30) {
            for (int j = temp; j <= 90; j += 30) {
                obstacleList.add(new Obstacle(j, i, 7, this));
            }
            temp += 30;
        }
        gan = StaticValue.gan;
        tower = StaticValue.tower;
        obstacleList.add(new Obstacle(515, 220, 8, this));
        enemyList.add(new Enemy(150, 385, true, 1, this));
    }

    private void createGroundAndBricks() {
        for (int i = 0; i < 27; i++) {
            obstacleList.add(new Obstacle(i * 30, 420, 1, this));
        }
        for (int j = 0; j <= 120; j += 30) {
            for (int i = 0; i < 27; i++) {
                obstacleList.add(new Obstacle(i * 30, 570 - j, 2, this));
            }
        }
    }

    private void createPipes() {
        for (int i = 360; i < 600; i += 25) {
            if (i == 360) {
                obstacleList.add(new Obstacle(620, i, 3, this));
                obstacleList.add(new Obstacle(645, i, 4, this));
            } else {
                obstacleList.add(new Obstacle(620, i, 5, this));
                obstacleList.add(new Obstacle(645, i, 6, this));
            }
        }
    }

    private void createPipes2() {
        for (int i = 360; i < 600; i += 25) {
            if (i == 360) {
                obstacleList.add(new Obstacle(60, i, 3, this));
                obstacleList.add(new Obstacle(85, i, 4, this));
            } else {
                obstacleList.add(new Obstacle(60, i, 5, this));
                obstacleList.add(new Obstacle(85, i, 6, this));
            }
        }
    }

    private void createPipes3() {
        for (int i = 320; i < 600; i += 25) {
            if (i == 320) {
                obstacleList.add(new Obstacle(620, i, 3, this));
                obstacleList.add(new Obstacle(645, i, 4, this));
            } else {
                obstacleList.add(new Obstacle(620, i, 5, this));
                obstacleList.add(new Obstacle(645, i, 6, this));
            }
        }
    }


    private void createSecondLevelBricks() {
        obstacleList.add(new Obstacle(300, 330, 0, this));
        for (int i = 270; i <= 330; i += 30) {
            if (i == 270 || i == 330) {
                obstacleList.add(new Obstacle(i, 360, 0, this));
            } else {
                obstacleList.add(new Obstacle(i, 360, 7, this));
            }
        }
        for (int i = 240; i <= 360; i += 30) {
            if (i == 240 || i == 360) {
                obstacleList.add(new Obstacle(i, 390, 0, this));
            } else {
                obstacleList.add(new Obstacle(i, 390, 7, this));
            }
        }
        obstacleList.add(new Obstacle(240, 300, 0, this));
        for (int i = 360; i <= 540; i += 60) {
            obstacleList.add(new Obstacle(i, 270, 7, this));
        }
    }

    // Getter methods
    public BufferedImage getBgImage() {
        return bgImage;
    }

    public int getSort() {
        return sort;
    }

    public boolean isFlag() {
        return flag;
    }

    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public BufferedImage getGan() {
        return gan;
    }

    public BufferedImage getTower() {
        return tower;
    }

    public boolean isReach() {
        return isReach;
    }

    public void setReach(boolean reach) {
        isReach = reach;
    }

    public boolean isBase() {
        return isBase;
    }

    public void setBase(boolean base) {
        isBase = base;
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public BufferedImage getLine1() {
        return line1;
    }

    public BufferedImage getLine2() {
        return line2;
    }
}
