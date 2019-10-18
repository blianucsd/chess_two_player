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

public class Bishop extends Piece {
    public BufferedImage image;
    
    public Bishop(int piece, int col, int row, boolean color) {
        super(piece,col,row,color);
        if(color) {
            try {
                image = ImageIO.read(new File("/Users/Bright/Desktop/Two_Player_Chess/images/whiteBishop.png"));
            } catch(IOException ex) {
                System.out.println(ex);
            }
        }
        else {
            try {
                image = ImageIO.read(new File("/Users/Bright/Desktop/Two_Player_Chess/images/blackBishop.png"));
            } catch(IOException ex) {
                System.out.println(ex);
            }
        }
    }

    public boolean canMove(int startRow, int startCol, int endRow, int endCol, boolean white, Board chessBoard)
    {
        int deltaR, deltaC;
        
        if(startRow==endRow||startCol==endCol) {
            return false;
        }
        else {
            deltaC = Math.abs(startCol-endCol); deltaR = Math.abs(startRow-endRow);
            if(deltaC!=deltaR)
            {
                return false;
            }
        }
        if(endCol>startCol&&endRow>startRow) {
            for(int i = 1; i <= deltaC; i++) {
                if(chessBoard.getPieceValue(startRow+i, startCol+i) != 0) {
                    if(chessBoard.getPieceColor(startRow+i, startCol+i)==white) {
                        return false;
                    }
                    else if(startCol+i!=endCol&&startRow+i!=endRow) {
                        return false;
                    }
                }
            }
        }
        else if(endCol>startCol&&endRow<startRow) {
            for(int i = 1; i <= deltaC; i++) {
                if(chessBoard.getPieceValue(startRow-i, startCol+i) != 0) {
                    if(chessBoard.getPieceColor(startRow-i, startCol+i)==white) {
                        return false;
                    }
                    else if(startCol+i!=endCol&&startRow-i!=endRow) {
                        return false;
                    }
                }
            }
        }
        else if(endCol<startCol&&endRow<startRow) {
            for(int i = 1; i <= deltaC; i++) {
                if(chessBoard.getPieceValue(startRow-i, startCol-i) != 0) {
                    if(chessBoard.getPieceColor(startRow-i, startCol-i)==white) {
                        return false;
                    }
                    else if(startCol-i!=endCol&&startRow-i!=endRow) {
                        return false;
                    }
                }
            }
        }
        else if(endCol<startCol&&endRow>startRow) {
            for(int i = 1; i <= deltaC; i++) {
                if(chessBoard.getPieceValue(startRow+i, startCol-i) != 0) {
                    if(chessBoard.getPieceColor(startRow+i, startCol-i)==white) {
                        return false;
                    }
                    else if(startCol-i!=endCol&&startRow+i!=endRow) {
                        return false;
                    }
                }
            }
        }
        return true;
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