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

public class Pawn extends Piece {
    public boolean moved = false;
    public BufferedImage image;
    
    public Pawn(int piece, int col, int row, boolean color, boolean move) {
        super(piece, col, row, color);
        moved = move;
        if(color) {
            try {
                image = ImageIO.read(new File("/Users/Bright/Desktop/Two_Player_Chess/images/whitePawn.png"));
            } catch(IOException ex) {
                System.out.println(ex); 
            }
        }
        else {
            try {
                image = ImageIO.read(new File("/Users/Bright/Desktop/Two_Player_Chess/images/blackPawn.png"));
            } catch(IOException ex) {
                System.out.println(ex); 
            }
        }
    }

    public void setMove(boolean move) {
        moved = move;
    }

    public boolean checkMoved() {
        return moved;
    }

    public boolean canMove(int startRow, int startCol, int endRow, int endCol, boolean white, Board chessBoard)
    {
        boolean capturing = canCapture(startRow, startCol, endRow, endCol, white, chessBoard);
        if(capturing) {
            return true; 
        }
        else if(startCol!=endCol) {
            return false; 
        }
        if(white==true) {
            if(endRow>=startRow) {
                return false;
            }
            if(moved==true&&((startRow-endRow)>1)) {
                return false;
            }
            if(moved==false&&((startRow-endRow)>2)) {
                return false;
            }
            if(moved==false&&(startRow-endRow)==2) {
                if(chessBoard.getPieceValue(startRow-1,endCol)!=0|| chessBoard.getPieceValue(startRow-2,endCol)!=0){
                    return false;
                }
                else {
                    moved = true; return true;
                }
            }
            if(moved==true&&chessBoard.getPieceValue(endRow,endCol)!=0) {
                return false;
            }
        }
        else{
            if(endRow<=startRow) {
                return false;
            }
            if(moved==true&&((endRow-startRow)>1)) {
                return false;
            }
            if(moved==false&&((endRow-startRow)>2)) {
                return false;
            }
            if(moved==false&&(endRow-startRow)==2) {
                if(chessBoard.getPieceValue(startRow+1,endCol)!=0|| chessBoard.getPieceValue(startRow+2,endCol)!=0){
                    return false;
                }
                else {
                    moved = true;
                    return true;
                }
            }
            if(moved==true&&chessBoard.getPieceValue(endRow,endCol)!=0) {
                return false;
            }
        }
        if(capturing==false&&startCol!=endCol) {
            return false;
        }
        if(chessBoard.getPieceValue(endRow, endCol)!=0&&capturing==false) {
            return false;
        }
        
        moved = true;
        return true; 
    }

    public boolean canCapture(int startRow, int startCol, int endRow, int endCol, boolean white, Board chessBoard)
    {
        if(white) {
            if(startCol>0&&startRow>0) {
                if(endRow==startRow-1&&endCol==startCol-1) {
                    if(chessBoard.getPieceValue(endRow,endCol)!=0) {
                        if(chessBoard.getPieceColor(endRow,endCol)!=white) {
                            return true;
                        }
                    }
                }
            }
            if(startCol<7&&startRow>0) {
                if(endRow==startRow-1&&endCol==startCol+1) {
                    if(chessBoard.getPieceValue(endRow,endCol)!=0) {
                        if(chessBoard.getPieceColor(endRow,endCol)!=white) {
                            return true;
                        }
                    }
                }
            }
        }
        else {
            if(startCol>0&&startRow<7) {
                if(endRow==startRow+1&&endCol==startCol-1) {
                    if(chessBoard.getPieceValue(endRow,endCol)!=0) {
                        if(chessBoard.getPieceColor(endRow,endCol)!=white) {
                            return true;
                        }
                    }
                }
            }
            if(startCol<7&&startRow<7) {
                if(endRow==startRow+1&&endCol==startCol+1) {
                    if(chessBoard.getPieceValue(endRow,endCol)!=0) {
                        if(chessBoard.getPieceColor(endRow,endCol)!=white) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean promote(int endRow, boolean white) {
        if(white) {
            if(endRow==0) {
                return true;
            }
        }
        else {
            if(endRow==7) {
                return true;
            }
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