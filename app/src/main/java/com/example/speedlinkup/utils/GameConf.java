package com.example.speedlinkup.utils;

import android.content.Context;

/**
 * 保存游戏配置的对象
 */
public class GameConf {
    /**
     * X轴有几个方块
     */
    public static int PIECE_X_SUM = 16;
    /**
     * Y轴有几个方块
     */
    public static int PIECE_Y_SUM = 16;

    /**
     * 图形形状
     */
    private String TYPE = "FULL";
    /**
     * 图形大小 1小 2中 3大
     */
    private int SIZE = 1;
    /**
     * 从哪里开始画第一张图片出现的x座标
     */
    public final  static int BEGIN_IMAGE_X = 25;
    /**
     * 从哪里开始画第一张图片出现的x座标
     */
    public final  static int BEGIN_IMAGE_Y = 75;

    /**
     * 连连看的每个方块的图片的宽   启动的时候赋值
     */
    public static int PIECE_WIDTH;
    /**
     * 连连看的每个方块的图片的高   启动的时候赋值
     */
    public static int PIECE_HEIGHT;
    /**
     * 记录游戏的总事时间
     */
    public static int DEFAULT_TIME ;
    /**
     * Piece[][]数组第一维的长度
     */
    private int xSize;
    /**
     * Piece[][]数组第二维的长度
     */
    private int ySize;
    /**
     * Board中第一张图片出现的x座标
     */
    private int beginImageX;
    /**
     * Board中第一张图片出现的y座标
     */
    private int beginImageY;
    /**
     * 应用上下文
     */
    private Context context;

    /**
     * 提供一个参数构造器
     *
     * @param xSize       Piece[][]数组第一维长度
     * @param ySize       Piece[][]数组第二维长度
     * @param beginImageX Board中第一张图片出现的x座标
     * @param beginImageY Board中第一张图片出现的y座标
     * @param gameTime    设置每局的时间, 单位是豪秒
     * @param context     应用上下文
     */
    public GameConf(String TYPE, int SIZE, int xSize, int ySize, int beginImageX, int beginImageY, int gameTime, Context context) {
        this.TYPE = TYPE;
        this.SIZE = SIZE;
        this.xSize = xSize;
        this.ySize = ySize;
        this.beginImageX = beginImageX;
        this.beginImageY = beginImageY;
        this.context = context;
        DEFAULT_TIME = gameTime;
        PIECE_X_SUM = xSize;
        PIECE_Y_SUM = ySize;
    }

    /**
     * @return Piece[][]数组第一维的长度
     */
    public int getXSize() {
        return xSize;
    }

    /**
     * @return Piece[][]数组第二维的长度
     */
    public int getYSize() {
        return ySize;
    }

    /**
     * @return Board中第一张图片出现的x座标
     */
    public int getBeginImageX() {
        return beginImageX;
    }

    /**
     * @return Board中第一张图片出现的y座标
     */
    public int getBeginImageY() {
        return beginImageY;
    }

    /**
     * @return 应用上下文
     */
    public Context getContext() {
        return context;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public int getSIZE() {
        return SIZE;
    }

    public void setSIZE(int SIZE) {
        this.SIZE = SIZE;
    }
}
