import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import java.awt.Component;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;

public class Game extends Canvas implements MouseListener, Runnable {
    public Board gameBoard;
    Graphics window;
    private boolean start;
    private int xPos;
    private int yPos;
    private Piece selectedPiece = null;
    private boolean move;
    private boolean whiteTurn;
    private boolean released = false;
    private boolean boardUpdate;
    private String log = "";
    public Game() {
        start = true;
        move = false;
        whiteTurn = true;
        boardUpdate = false;
        xPos = 0;
        yPos = 0;
        Graphics2D twoDGraph = (Graphics2D)window;
        addMouseListener(this);
        gameBoard = new Board();
        setBackground(Color.WHITE);
        setVisible(true);
        new Thread(this).start();
        addMouseListener(this);
    }

    public void update(Graphics window){ 
        paint(window);
    }

    public void mouseEntered(MouseEvent e) {
    } 

    public void mouseExited(MouseEvent e) {
    } 

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        released = true;
    }

    public void mouseClicked(MouseEvent e) {
        xPos = e.getX();
        yPos = e.getY();
        int colClicked = (int)(xPos/80);
        int rowClicked = (int)(yPos/80);
        if(selectedPiece==null)
        {
            if(gameBoard.getPieceValue(rowClicked,colClicked)!=0) {
                if(whiteTurn&&gameBoard.getPieceColor(rowClicked,colClicked)) {
                    selectedPiece = gameBoard.getRealBoard()[rowClicked][colClicked];
                }
                else if(whiteTurn==false&&gameBoard.getPieceColor(rowClicked,colClicked)==false)
                {
                    selectedPiece = gameBoard.getRealBoard()[rowClicked][colClicked];
                }
            }
        }
        else if(released) {
            if(selectedPiece.canMove(selectedPiece.getRow(),selectedPiece.getCol(),rowClicked,colClicked ,selectedPiece.getColor(),gameBoard))
            {
                if(selectedPiece.getPieceType()==6)
                {
                    if(colClicked-selectedPiece.getCol()==2) {
                        log = log + "0-0";
                    }
                    else if(selectedPiece.getCol()-colClicked==2) {
                        log = log + "0-0-0";
                    }
                } else {
                    if(selectedPiece.getPieceType()==2) {
                        log = log + "N";
                    }
                    else if(selectedPiece.getPieceType()==3) {
                        log = log + "B";
                    }
                    else if(selectedPiece.getPieceType()==4) {
                        log = log + "R";
                    }
                    else if(selectedPiece.getPieceType()==5) {
                        log = log + "Q";
                    }
                    else if(selectedPiece.getPieceType()==6) {
                        log = log + "K";
                    }
                    if(selectedPiece.getPieceType()==1) {
                        if(selectedPiece.getCol()!=colClicked) {
                            if(selectedPiece.getCol()==0) {
                                log = log + "axb" + Integer.toString(8-rowClicked);
                            }
                            else if(selectedPiece.getCol()==1) {
                                log = log+ "bx";
                                if(colClicked == 0) {
                                    log = log + "a";
                                }
                                else {
                                    log = log + "c";
                                }
                                log = log + Integer.toString(8-rowClicked);
                            }
                            else if(selectedPiece.getCol()==2) {
                                log = log+ "cx";
                                if(colClicked == 1) {
                                    log = log + "b";
                                }
                                else {
                                    log = log + "d";
                                }
                                log = log + Integer.toString(8-rowClicked);
                            }
                            else if(selectedPiece.getCol()==3) {
                                log = log+ "dx";
                                if(colClicked == 2) {
                                    log = log + "c";
                                }
                                else {
                                    log = log + "e";
                                }
                                log = log + Integer.toString(8-rowClicked);
                            }
                            else if(selectedPiece.getCol()==4) {
                                log = log+ "ex";
                                if(colClicked == 3) {
                                    log = log + "d";
                                }
                                else {
                                    log = log + "f";
                                }
                                log = log + Integer.toString(8-rowClicked);
                            }
                            else if(selectedPiece.getCol()==5) {
                                log = log+ "fx";
                                if(colClicked == 4) {
                                    log = log + "d";
                                }
                                else {
                                    log = log + "g";
                                }
                                log = log + Integer.toString(8-rowClicked);
                            }
                            else if(selectedPiece.getCol()==6) {
                                log = log+ "gx";
                                if(colClicked == 5) {
                                    log = log + "f";
                                }
                                else {
                                    log = log + "h";
                                }
                                log = log + Integer.toString(8-rowClicked);
                            }
                            else if(selectedPiece.getCol()==7) {
                                log = log + "hxg" + Integer.toString(8-rowClicked);
                            }
                        }
                        else {
                            if(colClicked==0) {
                                log = log + "a";
                            }
                            else if(colClicked==1) {
                                log = log + "b";
                            }
                            else if(colClicked==2) {
                                log = log + "c";
                            }
                            else if(colClicked==3) {
                                log = log + "d";
                            }
                            else if(colClicked==4) {
                                log = log + "e";
                            }
                            else if(colClicked==5) {
                                log = log + "f";
                            }
                            else if(colClicked==6) {
                                log = log + "g";
                            }
                            else if(colClicked==7) {
                                log = log + "h";
                            }
                            log = log + Integer.toString(8-rowClicked);
                        }
                        if(whiteTurn==true) {
                            if(rowClicked==0) {
                                Piece a = selectedPiece;
                                selectedPiece = new Queen(5,a.getRow(),a.getCol(),whiteTurn);
                                log = log + "=q";
                                gameBoard.getBoard()[a.getRow()][a.getCol()] = 0;
                                gameBoard.getRealBoard()[a.getRow()][a.getCol()] = new Empty();
                            }
                        }
                        else {
                            if(rowClicked==7) {
                                Piece a = selectedPiece;
                                selectedPiece = new Queen(5,a.getRow(),a.getCol(),whiteTurn);
                                log = log + "=q";
                                gameBoard.getBoard()[a.getRow()][a.getCol()] = 0;
                                gameBoard.getRealBoard()[a.getRow()][a.getCol()] = new Empty();
                            }
                        } 
                    }
                    else {
                        if(gameBoard.getPieceValue(rowClicked,colClicked)!=0) {
                            log = log + "x";
                        }
                        if(colClicked==0) {
                            log = log + "a";
                        }
                        else if(colClicked==1) {
                            log = log + "b";
                        }
                        else if(colClicked==2) {
                            log = log + "c";
                        }
                        else if(colClicked==3) {
                            log = log + "d";
                        }
                        else if(colClicked==4) {
                            log = log + "e";
                        }
                        else if(colClicked==5) {
                            log = log + "f";
                        }
                        else if(colClicked==6) {
                            log = log + "g";
                        }
                        else if(colClicked==7) {
                            log = log + "h";
                        }
                        log = log + Integer.toString(8-rowClicked); }
                }
                move = true;
                released = false;
            }
            else if(gameBoard.getPieceValue(rowClicked,colClicked)!=0) {
                if(gameBoard.getPieceColor(rowClicked,colClicked)==whiteTurn) {
                    selectedPiece = gameBoard.getPiece(rowClicked,colClicked);
                    released = false;
                }
            }
        }

        if(move) {
            gameBoard.setPiece(selectedPiece,rowClicked,colClicked);
            if(whiteTurn) {
                System.out.println();
            }
            System.out.print(log + "\t");
            log = "";
            boardUpdate = true;
            move = false;
            selectedPiece = null;
            whiteTurn = !whiteTurn;
            int kingCount = 0;
            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    if(gameBoard.getPieceValue(i,j)==6) {
                        if(gameBoard.getPieceColor(i,j)==whiteTurn) {
                            kingCount++;
                        }
                    }
                }
            }
            if(kingCount==0) {
                if(whiteTurn==true) {
                    System.out.println("Black wins");
                    gameBoard.reset();
                    whiteTurn = true;
                    start = true;
                } else {
                    System.out.println("White wins");
                    gameBoard.reset();
                    whiteTurn = true;
                    start = true;
                }
            }
        }
        repaint();
    }

    public void paint(Graphics window) {
        if(start) {
            System.out.println("Game Log:");
            System.out.println("White:\tBlack:"); }
        if(start||boardUpdate) {
            start = false;
            boardUpdate = false;
            gameBoard.drawTheBoard(window);
        }
        gameBoard.drawThePieces(window);
    }

    public void run()
    {
        try
        {
            while(true) {
                Thread.currentThread().sleep(8);
                repaint();
            }
        }catch(Exception e) {
        }
    }
}