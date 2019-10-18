import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class King extends Piece {
    public boolean kingCapture = false;
    public boolean kingMoved = false;
    public boolean underCheck = false;
    public int r;
    public int c;
    public BufferedImage image;
    
    public King(int piece, int col, int row, boolean color) {
        super(piece, col, row, color);
        if(color) {
            try {
                image = ImageIO.read(new File("/Users/Bright/Desktop/Two_Player_Chess/images/whiteKing.png"));
            } catch(IOException ex) {
                System.out.println(ex);
            }
        }
        else {
            try {
                image = ImageIO.read(new File("/Users/Bright/Desktop/Two_Player_Chess/images/blackKing.png"));
            } catch(IOException ex) {
                System.out.println(ex);
            }
        }
    }

    public boolean canMove(int startRow, int startCol, int endRow, int endCol, boolean white, Board chessBoard)
    {
        if(startRow==endRow&&startCol==endCol) {
            return false;
        }
        if(chessBoard.getPieceValue(endRow,endCol)!=0) {
            return false;
        }
        if(white) {
            if(endRow>0&&endCol>0) {
                if(chessBoard.getPieceValue(endRow-1,endCol-1)==1) {
                    if(chessBoard.getPieceColor(endRow-1,endCol-1)!=white) {
                        return false;
                    }
                }
            }
            if(endRow>0&&endCol<7) {
                if(chessBoard.getPieceValue(endRow-1,endCol+1)==1) {
                    if(chessBoard.getPieceColor(endRow-1,endCol+1)!=white) {
                        return false;
                    }
                }
            }
            if(endCol-startCol==2&&endRow==startRow) {
                if(startRow==7&&endRow==7&&canICastle(startRow,startCol,startRow,startCol,white,chessBoard,true)&&chessBoard.getPieceValue(endRow,endCol+1)==4&&chessBoard.getPieceColor(endRow,endCol+1)==white&&chessBoard.getPiece(endRow,endCol+1).returnMoved()==false)
                {
                    chessBoard.setPiece(chessBoard.getPiece(7,7),7,5);
                    kingMoved = true;
                    return true;
                }
                else {
                    return false;
                }
            }
            else if(startCol-endCol==2&&endRow==startRow) {
                if(startRow==7&&endRow==7&&canICastle(startRow,startCol,startRow,startCol,white,chessBoard,false)&&chessBoard.getPieceValue(endRow,endCol-2)==4&&chessBoard.getPieceColor(endRow,endCol-2)==white&&chessBoard.getPiece(endRow,endCol-2).returnMoved()==false)
                {
                    chessBoard.setPiece(chessBoard.getPiece(7,0),7,3);
                    kingMoved = true;
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        else {
            if(endRow<7&&endCol>0) {
                if(chessBoard.getPieceValue(endRow+1,endCol-1)==1) {
                    if(chessBoard.getPieceColor(endRow+1,endCol-1)!=white) {
                        return false;
                    }
                }
            }
            if(endRow<7&&endCol<7) {
                if(chessBoard.getPieceValue(endRow+1,endCol+1)==1) {
                    if(chessBoard.getPieceColor(endRow+1,endCol+1)!=white) {
                        return false;
                    }
                }
            }
            if(endCol-startCol==2&&endRow==startRow) {
                if(startRow==0&&endRow==0&&canICastle(startRow,startCol,startRow,startCol,white,chessBoard,true)&&chessBoard.getPieceValue(endRow,endCol+1)==4&&chessBoard.getPieceColor(endRow,endCol+1)==white&&chessBoard.getPiece(endRow,endCol+1).returnMoved()==false) {
                    chessBoard.setPiece(chessBoard.getPiece(0,7),0,5);
                    kingMoved = true;
                    return true;
                }
                else {
                    return false;
                }
            }
            else if(startCol-endCol==2&&endRow==startRow) {
                if(startRow==0&&endRow==0&&canICastle(startRow,startCol,startRow,startCol,white,chessBoard,false)&&chessBoard.getPieceValue(endRow,endCol-2)==4&&chessBoard.getPieceColor(endRow,endCol-2)==white&&chessBoard.getPiece(endRow,endCol-2).returnMoved()==false) {
                    chessBoard.setPiece(chessBoard.getPiece(0,0),0,3);
                    kingMoved = true;
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        if(Math.abs(startRow-endRow)>1||Math.abs(startCol-endCol)>2) {
            return false;
        }
        if(Math.abs(endRow-startRow)<=1&&Math.abs(endCol-startCol)<=1) {
            if(chessBoard.getPieceValue(endRow, endCol)!=0) {
                if(chessBoard.getPieceColor(endRow, endCol)==white) {
                    return false;
                }
            }
            else {
                for(int i = 0; i < 8; i++) {
                    for(int j = 0; j < 8; j++) {
                        if(chessBoard.getPieceValue(i,j)!=0) {
                            if(chessBoard.getPieceColor(i,j)!=white) {
                                if(chessBoard.getPiece(i,j).canCapture(i,j,endRow,endCol,! white,chessBoard)==true)
                                {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        kingMoved = true;
        return true;
    }

    public boolean canCapture(int startRow, int startCol, int endRow, int endCol, boolean color, Board chessBoard)
    {
        if(Math.abs(endCol-startCol)<2&&Math.abs(endRow-startRow)<2) {
            if(chessBoard.getPieceValue(endRow,endCol)!=0) {
                if(chessBoard.getPieceColor(endRow,endCol)!=color) {
                    for(int i = 0; i < 8; i++) {
                        for(int j = 0; j < 8; j++) {
                            if(chessBoard.getPieceValue(i,j)!=0) {
                                if(chessBoard.getPieceColor(i,j)!=color) {
                                    if(chessBoard.getPiece(i,j).canCapture(i,j,endRow,endCol,!color,chessBoard)) {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean canICastle(int startRow, int startCol, int endRow, int endCol, boolean color, Board chessBoard, boolean kingSide)
    {
        if(kingMoved==false&&amIUnderCheck(color,chessBoard)==false) {
            if(kingSide&&color) {
                for(int i = 1; i < 3; i++) {
                    if(chessBoard.getPieceValue(startRow,startCol+i)!=0) {
                        return false;
                    }
                    for(int j = 0; j < 8; j++) {
                        for(int k = 0; k < 8; k++) {
                            if(chessBoard.getPieceValue(j,k)!=0) {
                                if(chessBoard.getPieceColor(j,k)!=color) {
                                    if(chessBoard.getPiece(j,k).canCapture(j,k,endRow,endCol+i,!color,chessBoard)) {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if(kingSide&&!color) {
                for(int i = 1; i < 3; i++) {
                    for(int j = 0; j < 8; j++) {
                        for(int k = 0; k < 8; k++) {
                            if(chessBoard.getPieceValue(j,k)!=0) {
                                if(chessBoard.getPieceColor(j,k)!=color) {
                                    if(chessBoard.getPiece(j,k).canCapture(j,k,endRow,endCol+i,!color,chessBoard))
                                    {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if(!kingSide&&color) {
                for(int i = 1; i < 3; i++) {
                    for(int j = 0; j < 8; j++) {
                        for(int k = 0; k < 8; k++) {
                            if(chessBoard.getPieceValue(j,k)!=0) {
                                if(chessBoard.getPieceColor(j,k)!=color) {
                                    if(chessBoard.getPiece(j,k).canCapture(j,k,endRow,endCol-i,!color,chessBoard))
                                    {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if(!kingSide&&!color) {
                for(int i = 1; i < 3; i++) {
                    for(int j = 0; j < 8; j++) {
                        for(int k = 0; k < 8; k++) {
                            if(chessBoard.getPieceValue(j,k)!=0) {
                                if(chessBoard.getPieceColor(j,k)!=color) {
                                    if(chessBoard.getPiece(j,k).canCapture(j,k,endRow,endCol-i,!color,chessBoard)) {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            return false;
        }
        return true;
    }

    public boolean inCheck(boolean white, Board chessBoard) {
        if(white) {
            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    if(chessBoard.getPieceValue(i,j)!=0) {
                        if(chessBoard.getPieceColor(i,j)!=white) {
                            if(chessBoard.getPiece(i,j).canCapture(i,j,getRow(),getCol(),!white,chessBoard))
                            {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        else {
            for(int k = 0; k < 8; k++) {
                for(int l = 0; l < 8; l++) {
                    if(chessBoard.getPieceValue(k,l)!=0) {
                        if(chessBoard.getPieceColor(k,l)!=white) {
                            if(chessBoard.getPiece(k,l).canCapture(k,l,getRow(),getCol(),!white,chessBoard))
                            {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean amIUnderCheck(boolean col, Board chessBoard) {
        if(inCheck(col,chessBoard)) {
            return true;
        }
        return false;
    }

    public void draw(Graphics g) {
        g.drawImage(image,80*getCol(),80*getRow(),80,80,null);
    }

    public boolean check(boolean color, Board b) {
        return false;
    }
}