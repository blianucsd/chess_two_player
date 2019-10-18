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

public class Queen extends Piece {
    public BufferedImage image;
    public Queen(int piece, int col, int row, boolean color) {
        super(piece, col, row, color);
        if(color) {
            try {
                image = ImageIO.read(new File("/Users/Bright/Desktop/Two_Player_Chess/images/whiteQueen.png"));
            } catch(IOException ex) {
                System.out.println(ex);
            }
        } else {
            try {
                image = ImageIO.read(new File("/Users/Bright/Desktop/Two_Player_Chess/images/blackQueen.png"));
            } catch(IOException ex) {
                System.out.println(ex);
            }
        }
    }

    public boolean canMove(int startRow, int startCol, int endRow, int endCol, boolean white, Board chessBoard)
    {
        int deltaC, deltaR;
        deltaC = Math.abs(startCol-endCol);
        deltaR = Math.abs(startRow-endRow);
        int squares;
        if(endRow==startRow&&endCol==startCol) {
            return false;
        }
        if(endRow>startRow&&endCol>startCol) {//diagonal bottom right
            if(deltaC!=deltaR) {
                return false;
            }
            squares = endCol - startCol;
            for(int i = 1; i <= squares; i++) {
                if(chessBoard.getPieceValue(startRow+i,startCol+i)!=0) {
                    if(chessBoard.getPieceColor(startRow+i,startCol+i)==white) {
                        return false;
                    }
                    else if(startRow+i!=endRow&&startCol+i!=endCol) {
                        return false;
                    }
                } 
            }
        }
        else if(endRow>startRow&&endCol<startCol) {
            if(deltaC!=deltaR) {
                return false;
            }
            squares = endRow - startRow;
            for(int i = 1; i <= squares; i++) {
                if(chessBoard.getPieceValue(startRow+i,startCol-i)!=0) {
                    if(chessBoard.getPieceColor(startRow+i,startCol-i)==white) {
                        return false;
                    }
                    else if(startRow+i!=endRow&&startCol-i!=endCol) {
                        return false;
                    }
                }
            }
        }
        else if(endRow<startRow&&endCol<startCol) {
            if(deltaC!=deltaR) {
                return false;
            }
            squares = startCol - endCol;
            for(int i = 1; i <= squares; i++) {
                if(chessBoard.getPieceValue(startRow-i,startCol-i)!=0) {
                    if(chessBoard.getPieceColor(startRow-i,startCol-i)==white) {
                        return false;
                    }
                    else if(startRow-i!=endRow&&startCol-i!=endCol) {
                        return false;
                    }
                }
            }
        }
        else if(endRow<startRow&&endCol>startCol) {
            if(deltaC!=deltaR) {
                return false;
            }
            squares = endCol - startCol;
            for(int i = 1; i <= squares; i++) {
                if(chessBoard.getPieceValue(startRow-i,startCol+i)!=0) {
                    if(chessBoard.getPieceColor(startRow-i,startCol+i)==white) {
                        return false;
                    }
                    else if(startRow-i!=endRow&&startCol+i!=endCol) {
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
        else if(endRow>startRow) {
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
        else if(endCol<startCol) {
            squares = startCol-endCol;
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