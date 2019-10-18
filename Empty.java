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

public class Empty extends Piece
{
    private BufferedImage image;
    private int col,row;
    public Empty()
    {
        super(0);
    }
    
    public void setCol(int c)
    {
        col = c;
    }
    
    public void setRow(int r)
    {
        row = r;
    }
    
    public boolean canCapture(int x, int y, int x2, int y2, boolean col, Board chessBoard)
    {
        return false;
    }
    
    public boolean canMove(int startRow, int startCol, int endRow, int endCol, boolean color, Board chessBoard)
    {
        return false;
    }
    
    public void draw(Graphics g)
    {
        //g.drawImage(image,80*col,80*row,80,80,null);
    }
    
    public boolean check(boolean color, Board b) {
        return false;
    }
}
