import java.awt.*;
import java.awt.event.*; 
import java.awt.image.*; 
import java.io.*;
import javax.imageio.*; 
import javax.swing.*; 
import java.awt.Graphics; 
import javax.swing.JPanel;

public abstract class Piece {
    public int typeOfPiece; 
    public int rowIndex;
    public int colIndex;
    public boolean isWhite; 
    private boolean sameColor; 
    private boolean hasMoved;
    
    public Piece() {
        sameColor = false; 
    }

    public Piece(int piece) {
        typeOfPiece = piece;
        sameColor = false; 
    }

    public Piece(int piece, int x, int y, boolean white) {
        typeOfPiece = piece;
        colIndex = x;
        rowIndex = y;
        isWhite = white;
        sameColor = false;
    }

    public Piece(int piece, int x, int y, boolean white, boolean moved) {
        typeOfPiece = piece;
        colIndex = x;
        rowIndex = y;
        isWhite = white;
        sameColor = false;
        hasMoved = moved;
    }

    public void setCol(int col) {
        colIndex = col; 
    }

    public void setRow(int row) {
        rowIndex = row; 
    }

    public void setPosition(int row, int col) {
        rowIndex = row;
        colIndex = col; 
    }

    public int getRow() {
        return rowIndex; 
    }

    public int getCol() {
        return colIndex; 
    }

    public boolean getColor() {
        return isWhite; 
    }

    public int getPieceType() {
        return typeOfPiece; 
    }

    public void setMoved(boolean didMove)
    {
        hasMoved = didMove;
    }

    public boolean returnMoved() {
        return hasMoved; 
    }

    public abstract boolean canMove(int startRow, int startCol, int endRow, int endCol, boolean color, Board chessBoard);

    public abstract boolean canCapture(int startRow, int startCol, int endRow, int endCol, boolean color, Board chessBoard);

    public abstract void draw(Graphics g);

    public abstract boolean check(boolean color, Board b);
}