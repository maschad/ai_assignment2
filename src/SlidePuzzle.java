import javax.swing.*;

import org.jpl7.JPL;
import org.jpl7.Query;
import org.jpl7.Term;


/**
 * Created by chad on 11/13/16.
 */
public class SlidePuzzle {
    //============================================= method main
    public static void main(String[] args) {
    	Query.hasSolution("use_module(library(jpl))"); // only because we call e.g. jpl_pl_syntax/1 below
		Term swi = Query.oneSolution("current_prolog_flag(version_data,Swi)").get("Swi");

        String t1 = "consult('puzzle.pl')";//Query prologue file
        System.out.println(t1 + " " + (Query.hasSolution(t1) ? "succeeded" : "failed"));//Check if successful


        JFrame window = new JFrame("Slide Puzzle");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new SlidePuzzleGUI());
        window.pack();  // finalize layout
        window.show();  // make window visible
        window.setResizable(false);
    }//end main
}
