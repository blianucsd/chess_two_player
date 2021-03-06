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

public class Rook extends Piece {
    public BufferedImage image;
    public Rook(int piece, int col, int row, boolean color) {
        super(piece, col, row, color, false);
        
        if(color) {
            try {
                image = ImageIO.read(new File("/Users/Bright/Desktop/Two_Player_Chess/images/whiteRook.png"));
            } catch(IOException ex) {
                System.out.println(ex);
            }
        }
        else {
            try {
                image = ImageIO.read(new File("/Users/Bright/Desktop/Two_Player_Chess/images/blackRook.png"));
            } catch(IOException ex) {
                System.out.println(ex);
            }
        }
    }

    public boolean canMove(int startRow, int startCol, int endRow, int endCol, boolean white, Board chessBoard)
    {
        if(startRow!=endRow&&startCol!=endCol) {
            return false;
        }
        int squares;
        if(endRow==startRow&&endCol==startCol) {
            return false;
        }
        if(endRow>startRow) {
            squares = endRow-startRow;
            for(int i = 1; i <= squares; i++) {
                if(chessBoard.getPieceValue(startRow+i,startCol)!=0) {
                    if(chessBoard.getPieceColor(startRow+i,startCol)==white) {
                        return false;
                    }
                    else if(startRow+i!=endRow) {
                        return false;
                    }
                }
            }
        }
        else if(endRow<startRow) {
            squares = startRow-endRow;
            for(int i = 1; i <= squares; i++) {
                if(chessBoard.getPieceValue(startRow-i,startCol)!=0) {
                    if(chessBoard.getPieceColor(startRow-i,startCol)==white) {
                        return false;
                    }
                    else if(startRow-i!=endRow) {
                        return false;
                    }
                }
            }
        }
        else if(endCol>startCol) {
            squares = endCol-startCol;
            for(int i = 1; i <= squares; i++) {
                if(chessBoard.getPieceValue(startRow,startCol+i)!=0) {
                    if(chessBoard.getPieceColor(startRow,startCol+i)==white) {
                        return false;
                    }
                    else if(startCol+i!=endCol) {
                        return false;
                    }
                }
            }
        }
        else if(endCol<startCol) {
            squares = endRow-startRow;
            for(int i = 1; i <= squares; i++) {
                if(chessBoard.getPieceValue(startRow,startCol-i)!=0) {
                    if(chessBoard.getPieceColor(startRow,startCol-i)==white) {
                        return false;
                    }
                    else if(startCol-i!=endCol) {
                        return false;
                    }
                }
            }
        }
        
        setMoved(true);
        return true;
    }

    public boolean canCapture(int startRow, int startCol, int endRow, int endCol, boolean color, Board chessBoard)
    {
        if(canMove(startRow, startCol, endRow, endCol, color, chessBoard)) {
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