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

public class Knight extends Piece {
    public BufferedImage image;
    public Knight(int piece, int col, int row, boolean color) {
        super(piece, col, row, color);
        if(color) {
            try {
                image = ImageIO.read(new File("/Users/Bright/Desktop/Two_Player_Chess/images/whiteKnight.png"));
            } catch(IOException ex) {
                System.out.println(ex);
            }
        }
        else {
            try {
                image = ImageIO.read(new File("/Users/Bright/Desktop/Two_Player_Chess/images/blackKnight.png"));
            } catch(IOException ex) {
                System.out.println(ex); 
            }
        }
    }

    public boolean canMove(int startRow, int startCol, int endRow, int endCol, boolean white, Board chessBoard)
    {
        if(endRow>=0&&endRow<=7) {
            if(endCol>=0&&endCol<=7) {
                if(endCol-startCol==2&&endRow-startRow==1) {
                    if(chessBoard.getPieceValue(endRow, endCol)!=0) {
                        if(getColor()!=chessBoard.getPieceColor(endRow, endCol)) {
                            return true;
                        }
                    } 
                    else {
                        return true;
                    }
                }
                if(endCol-startCol==2&&endRow-startRow==-1) {
                    if(chessBoard.getPieceValue(endRow, endCol)!=0) {
                        if(getColor()!=chessBoard.getPieceColor(endRow, endCol)) {
                            return true;
                        }
                    }
                    else {
                        return true;
                    }
                }
                if(endCol-startCol==-2&&endRow-startRow==1) {
                    if(chessBoard.getPieceValue(endRow, endCol)!=0) {
                        if(getColor()!=chessBoard.getPieceColor(endRow, endCol)) {
                            return true;
                        }
                    }
                    else {
                        return true;
                    }
                }
                if(endCol-startCol==-2&&endRow-startRow==-1) {
                    if(chessBoard.getPieceValue(endRow, endCol)!=0) {
                        if(getColor()!=chessBoard.getPieceColor(endRow, endCol)) {
                            return true; }
                    } else {
                        return true; }
                }
                if(endCol-startCol==1&&endRow-startRow==2) {
                    if(chessBoard.getPieceValue(endRow, endCol)!=0) {
                        if(getColor()!=chessBoard.getPieceColor(endRow, endCol)) {
                            return true;
                        }
                    }
                    else {
                        return true;
                    }
                }
                if(endCol-startCol==-1&&endRow-startRow==2) {
                    if(chessBoard.getPieceValue(endRow, endCol)!=0) {
                        if(getColor()!=chessBoard.getPieceColor(endRow, endCol)) {
                            return true;
                        }
                    }
                    else {
                        return true;
                    }
                }
                if(endCol-startCol==1&&endRow-startRow==-2) {
                    if(chessBoard.getPieceValue(endRow, endCol)!=0) {
                        if(getColor()!=chessBoard.getPieceColor(endRow, endCol)) {
                            return true;
                        }
                    }
                    else {
                        return true;
                    }
                }
                if(endCol-startCol==-1&&endRow-startRow==-2) {
                    if(chessBoard.getPieceValue(endRow, endCol)!=0) {
                        if(getColor()!=chessBoard.getPieceColor(endRow, endCol)) {
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean canCapture(int startRow, int startCol, int endRow, int endCol, boolean color, Board chessBoard)
    {
        return canMove(startRow, startCol, endRow, endCol, color, chessBoard);
    }

    public void draw(Graphics g) {
        g.drawImage(image,80*getCol(),80*getRow(),80,80,null);
    }

    public boolean check(boolean color, Board b) {
        return false;
    }
}