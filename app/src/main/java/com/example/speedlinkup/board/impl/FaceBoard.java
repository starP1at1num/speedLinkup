package com.example.speedlinkup.board.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.speedlinkup.board.AbstractBoard;
import com.example.speedlinkup.utils.GameConf;
import com.example.speedlinkup.view.Piece;

/**
 * 创建脸型游戏区域
 */
public class FaceBoard extends AbstractBoard {
    @Override
    protected List<Piece> createPieces(GameConf config, Piece[][] pieces) {
        // 创建一个Piece集合, 该集合里面存放初始化游戏时所需的Piece对象
        List<Piece> notNullPieces = new ArrayList<Piece>();
        if (config.getSIZE() == 1) {
            notNullPieces = getSmallPieces(pieces);
        } else if (config.getSIZE() == 2) {
            notNullPieces = getMiddlePieces(pieces);
        } else if (config.getSIZE() == 3) {
            notNullPieces = getLargePieces(pieces);
        }
        return notNullPieces;
    }


    //脸形小型
    private List<Piece> getSmallPieces(Piece[][] pieces) {
        // 创建一个Piece集合, 该集合里面存放初始化游戏时所需的Piece对象
        List<Piece> notNullPieces = new ArrayList<Piece>();
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                // 加入判断, 符合一定条件才去构造Piece对象, 并加到集合中
                if ((j == 0 && (2 <= i && i <= 4))
                        || (j == 1 && (1 <= i && i <= 5))
                        || (j == 2) || (j == 4) || (j == 5)
                        || (j == 3 && (i != 1 && i != 2 && i != 4 && i != 5))
                        || (j == 6 && (i != 2 && i != 4))
                        || (j == 7 && (i != 3 && (1 <= i && i <= 5)))
                        || (j == 8 && (2 <= i && i <= 4))
                        || (j == 9 && i == 3)) {
                    Piece piece = new Piece(i, j);
                    // 添加到Piece集合中
                    notNullPieces.add(piece);
                }
            }
        }
        return notNullPieces;
    }

    //脸形中型
    private List<Piece> getMiddlePieces(Piece[][] pieces) {
        // 创建一个Piece集合, 该集合里面存放初始化游戏时所需的Piece对象
        List<Piece> notNullPieces = new ArrayList<Piece>();
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                // 加入判断, 符合一定条件才去构造Piece对象, 并加到集合中
                if ((j == 0 && (3 <= i && i <= 5))
                        || (j == 1 && (2 <= i && i <= 6))
                        || (j == 2 && (1 <= i && i <= 7))
                        || (j == 3) || (j == 5) || (j == 6) || (j == 7)
                        || (j == 4 && (i == 0 || i == 4 || i == 8))
                        || (j == 8 && (i != 2 && i != 6))
                        || (j == 9 && (i == 1 || i == 2 || i == 6 || i == 7))
                        || (j == 10 && (2 <= i && i <= 6))
                        || (j == 11 && i == 4)) {
                    Piece piece = new Piece(i, j);
                    // 添加到Piece集合中
                    notNullPieces.add(piece);
                }
            }
        }
        return notNullPieces;
    }

    //脸形大型
    private List<Piece> getLargePieces(Piece[][] pieces) {
        // 创建一个Piece集合, 该集合里面存放初始化游戏时所需的Piece对象
        List<Piece> notNullPieces = new ArrayList<Piece>();
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                // 加入判断, 符合一定条件才去构造Piece对象, 并加到集合中
                if ((j == 0 && (4 <= i && i <= 6))
                        || (j == 1 && (3 <= i && i <= 7))
                        || (j == 2 && (2 <= i && i <= 8))
                        || (j == 3 && (1 <= i && i <= 9))
                        || (j == 4) || (j == 6) || (j == 7) || (j == 8)
                        || (j == 5 && (i == 0 || i == 5 || i == 10))
                        || (j == 9 && (i != 2 && i != 8))
                        || (j == 10 && (i != 0 && i != 2 && i != 7 && i != 10))
                        || (j == 11 && (i == 2 || i == 3 || i == 7 || i == 8))
                        || (j == 12 && (3 <= i && i <= 7))) {
                    Piece piece = new Piece(i, j);
                    // 添加到Piece集合中
                    notNullPieces.add(piece);
                }
            }
        }
        return notNullPieces;
    }
}
