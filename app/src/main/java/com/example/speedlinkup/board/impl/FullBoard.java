package com.example.speedlinkup.board.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.speedlinkup.board.AbstractBoard;
import com.example.speedlinkup.utils.GameConf;
import com.example.speedlinkup.view.Piece;

/**
 * 创建矩形游戏区域
 */
public class FullBoard extends AbstractBoard {
    @Override
    protected List<Piece> createPieces(GameConf config, Piece[][] pieces) {
        // 创建一个Piece集合, 该集合里面存放初始化游戏时所需的Piece对象
        List<Piece> notNullPieces = new ArrayList<Piece>();
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                // 先构造一个Piece对象, 只设置它在Piece[][]数组中的索引值，
                // 所需要的PieceImage由其父类负责设置。
                Piece piece = new Piece(i, j);
                // 添加到Piece集合中
                notNullPieces.add(piece);
            }
        }
        return notNullPieces;
    }
}
