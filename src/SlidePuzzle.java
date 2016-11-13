import javax.swing.*;

/**
 * Created by chad on 11/13/16.
 */
public class SlidePuzzle {
    //============================================= method main
    public static void main(String[] args) {
        JFrame window = new JFrame("Slide Puzzle");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new SlidePuzzleGUI());
        window.pack();  // finalize layout
        window.show();  // make window visible
        window.setResizable(false);
    }//end main
}
