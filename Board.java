import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Board {
    public int[][] chessBoard;
    public int[][] colors;
    public int[][] squares;
    public Piece[][] realBoard;
    Empty a = new Empty();
    Empty b = new Empty();
    private Piece holder = new Empty();
    public Board() {
        chessBoard = new int [][] {
            {4,2,3,5,6,3,2,4},
            {1,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1},
            {4,2,3,5,6,3,2,4}
        };
        colors = new int[][] {
            {2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
        };
        realBoard = new Piece[][] {
            {a,b,a,b,a,b,a,b},
            {b,a,b,a,b,a,b,a},
            {a,b,a,b,a,b,a,b},
            {b,a,b,a,b,a,b,a},
            {a,b,a,b,a,b,a,b},
            {b,a,b,a,b,a,b,a},
            {a,b,a,b,a,b,a,b},
            {b,a,b,a,b,a,b,a},
        };
        squares = new int[][]{
            {1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1},
        };

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++)
            {
                if(chessBoard[i][j]!=0) {
                    if(chessBoard[i][j]==1) {
                        if(colors[i][j]==1) {
                            realBoard[i][j] = new Pawn(1,j,i,true,false);
                        }
                        else if(colors[i][j]==2) {
                            realBoard[i][j] = new Pawn(1,j,i,false,false);
                        }
                    }
                    else if(chessBoard[i][j]==2) {
                        if(colors[i][j]==1) {
                            realBoard[i][j] = new Knight(2,j,i,true);
                        }
                        else if(colors[i][j]==2) {
                            realBoard[i][j] = new Knight(2,j,i,false);
                        }
                    }
                    else if(chessBoard[i][j]==3) {
                        if(colors[i][j]==1) {
                            realBoard[i][j] = new Bishop(3,j,i,true);
                        }
                        else if(colors[i][j]==2) {
                            realBoard[i][j] = new Bishop(3,j,i,false);
                        }
                    }
                    else if(chessBoard[i][j]==4) {
                        if(colors[i][j]==1) {
                            realBoard[i][j] = new Rook(4,j,i,true);
                        }
                        else if(colors[i][j]==2)
                        {
                            realBoard[i][j] = new Rook(4,j,i,false);
                        }
                    }
                    else if(chessBoard[i][j]==5) {
                        if(colors[i][j]==1) {
                            realBoard[i][j] = new Queen(5,j,i,true);
                        }
                        else if(colors[i][j]==2) {
                            realBoard[i][j] = new Queen(5,j,i,false);
                        }
                    }
                    else if(chessBoard[i][j]==6) {
                        if(colors[i][j]==1) {
                            realBoard[i][j] = new King(6,j,i,true);
                        }
                        else if(colors[i][j]==2) {
                            realBoard[i][j] = new King(6,j,i,false);
                        }
                    }
                }
            }
        }
    }

    public void setPiece(int piece, int newRow, int newCol, int oldRow, int oldCol, boolean thereIsAPieceThere)
    {
        chessBoard[newRow][newCol] = piece; chessBoard[oldRow][oldCol] = 0;
    }

    public void setPiece(Piece selected, int newRow, int newCol) {
        Empty holder = new Empty();
        setPiece(selected.getPieceType(), newRow, newCol, selected.getRow(), selected.getCol(),false);
        realBoard[selected.getRow()][selected.getCol()] = holder;
        realBoard[newRow][newCol] = selected;
        colors[selected.getRow()][selected.getCol()] = 0;
        if(selected.getColor())
        {
            colors[newRow][newCol] = 1;
        }
        else {
            colors[newRow][newCol] = 2;
        }
        selected.setCol(newCol);
        selected.setRow(newRow);
    }

    public int getPieceValue(int row, int col) {
        return chessBoard[row][col];
    }

    public boolean getPieceColor(int row, int col)//must check piece value != 0 before calling this method or there will be an error as blank squares are neither white or black
    {
        if(colors[row][col]==1)
            return true;
        else
            return false;
    }

    public int[][] getBoard() {
        return chessBoard;
    }

    public Piece getPiece(int row, int col) {
        return realBoard[row][col];
    }

    public void drawTheBoard(Graphics g) {
        BufferedImage img;
        for(int k = 0; k < 8; k++) {
            for(int l = 0; l < 8; l++) {
                if(squares[k][l]==1) {
                    try {
                        img = ImageIO.read(new File("/Users/Bright/Desktop/Two_Player_Chess/images/whiteSquare.png"));
                        g.drawImage(img,l*80,k*80,80,80,null);
                    } catch(IOException ex) {
                        System.out.println(ex);
                    }
                }
                else {
                    try {
                        img = ImageIO.read(new File("/Users/Bright/Desktop/Two_Player_Chess/images/greenSquare.png"));
                        g.drawImage(img,l*80,k*80,80,80,null);
                    } catch(IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
        }
    }

    public void drawThePieces(Graphics g) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                realBoard[i][j].draw(g);
            }
        }
    }

    public Piece[][] getRealBoard() {
        return realBoard;
    }

    public King getKing(boolean col) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(getPieceValue(i,j)==6) {
                    if(getPieceColor(i,j)==col) {
                        return (King)(realBoard[i][j]);
                    }
                }
            }
        }
        return null;
    }

    public void reset() {
        chessBoard = new int[][] { 
            {4,2,3,5,6,3,2,4},
            {1,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1},
            {4,2,3,5,6,3,2,4}
        };
        colors = new int[][] {
            {2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
        };
        realBoard = new Piece[][] {
            {a,b,a,b,a,b,a,b},
            {b,a,b,a,b,a,b,a},
            {a,b,a,b,a,b,a,b},
            {b,a,b,a,b,a,b,a},
            {a,b,a,b,a,b,a,b},
            {b,a,b,a,b,a,b,a},
            {a,b,a,b,a,b,a,b},
            {b,a,b,a,b,a,b,a},
        };
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(chessBoard[i][j]!=0) {
                    if(chessBoard[i][j]==1) {
                        if(colors[i][j]==1) {
                            realBoard[i][j] = new Pawn(1,j,i,true,false);
                        }
                        else if(colors[i][j]==2) {
                            realBoard[i][j] = new Pawn(1,j,i,false,false);
                        }
                    }
                    else if(chessBoard[i][j]==2) {
                        if(colors[i][j]==1) {
                            realBoard[i][j] = new Knight(2,j,i,true);
                        }
                        else if(colors[i][j]==2) {
                            realBoard[i][j] = new Knight(2,j,i,false);
                        }
                    }
                    else if(chessBoard[i][j]==3) {
                        if(colors[i][j]==1) {
                            realBoard[i][j] = new Bishop(3,j,i,true);
                        }
                        else if(colors[i][j]==2) {
                            realBoard[i][j] = new Bishop(3,j,i,false);
                        }
                    }
                    else if(chessBoard[i][j]==4) {
                        if(colors[i][j]==1) {
                            realBoard[i][j] = new Rook(4,j,i,true);
                        }
                        else if(colors[i][j]==2) {
                            realBoard[i][j] = new Rook(4,j,i,false);
                        }
                    }
                    else if(chessBoard[i][j]==5) {
                        if(colors[i][j]==1) {
                            realBoard[i][j] = new Queen(5,j,i,true);
                        }
                        else if(colors[i][j]==2) {
                            realBoard[i][j] = new Queen(5,j,i,false);
                        }
                    }
                    else if(chessBoard[i][j]==6) {
                        if(colors[i][j]==1) {
                            realBoard[i][j] = new King(6,j,i,true);
                        }
                        else if(colors[i][j]==2) {
                            realBoard[i][j] = new King(6,j,i,false);
                        }
                    }
                }
            }
        }
    }
}