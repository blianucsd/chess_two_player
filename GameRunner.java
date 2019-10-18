import javax.swing.JFrame;
import java.awt.Component;

public class GameRunner extends JFrame {
    public GameRunner() {
        setSize(640,660);
        Game run = new Game();
        ((Component)run).setFocusable(true);
        getContentPane().add(run);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        GameRunner test = new GameRunner();
    }
}