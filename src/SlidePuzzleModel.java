/**
 * Created by chad on 11/13/16.
 */

import java.util.Map;

import org.jpl7.JPL;
import org.jpl7.Query;
import org.jpl7.Term;

public class SlidePuzzleModel {




    private static final int ROWS = 3;
    private static final int COLS = 3;

    private Tile[][] _contents;  // All tiles.
    private Tile     _emptyTile; // The empty space.


    //================================================= constructor
    public SlidePuzzleModel() {
        _contents = new Tile[ROWS][COLS];
        reset();               // Initialize and shuffle tiles.
    }//end constructor


    //===================================================== getFace
    // Return the string to display at given row, col.
    String getFace(int row, int col) {
        return _contents[row][col].getFace();
    }//end getFace


    //======================================================= reset
    // Initialize and shuffle the tiles.
    public void reset() {
        for (int r=0; r<ROWS; r++) {
            for (int c=0; c<COLS; c++) {
                _contents[r][c] = new Tile(r, c, "" + (r*COLS+c+1));
            }
        }
        //--- Set last tile face to null to mark empty space
        _emptyTile = _contents[ROWS-1][COLS-1];
        _emptyTile.setFace(null);

        //-- Shuffle - Exchange each tile with random tile.
        for (int r=0; r<ROWS; r++) {
            for (int c=0; c<COLS; c++) {
                exchangeTiles(r, c, (int)(Math.random()*ROWS)
                        , (int)(Math.random()*COLS));
            }
        }
    }//end reset


    //==================================================== moveTile
    // Move a tile to empty position beside it, if possible.
    // Return true if it was moved, false if not legal.
    public boolean moveTile(int r, int c) {
        //--- It's a legal move if the empty cell is next to it.
        return checkEmpty(r, c, -1, 0) || checkEmpty(r, c, 1, 0)
                || checkEmpty(r, c, 0, -1) || checkEmpty(r, c, 0, 1);
    }//end moveTile


    //================================================== checkEmpty
    // Check to see if there is an empty position beside tile.
    // Return true and exchange if possible, else return false.
    private boolean checkEmpty(int r, int c, int rdelta, int cdelta) {
        int rNeighbor = r + rdelta;
        int cNeighbor = c + cdelta;
        //--- Check to see if this neighbor is on board and is empty.
        if (isLegalRowCol(rNeighbor, cNeighbor)
                && _contents[rNeighbor][cNeighbor] == _emptyTile) {
            exchangeTiles(r, c, rNeighbor, cNeighbor);
            return true;
        }
        return false;
    }//end checkEmpty


    //=============================================== isLegalRowCol
    // Check for legal row, col
    public boolean isLegalRowCol(int r, int c) {
        return r>=0 && r<ROWS && c>=0 && c<COLS;
    }//end isLegalRowCol


    //=============================================== exchangeTiles
    // Exchange two tiles.
    private void exchangeTiles(int r1, int c1, int r2, int c2) {
        Tile temp = _contents[r1][c1];
        _contents[r1][c1] = _contents[r2][c2];
        _contents[r2][c2] = temp;
    }//end exchangeTiles


    //=================================================== isGameOver
    public boolean isGameOver() {
        for (int r=0; r<ROWS; r++) {
            for (int c=0; c<ROWS; c++) {
                Tile trc = _contents[r][c];
                return trc.isInFinalPosition(r, c);
            }
        }

        //--- Falling thru loop means nothing out of place.
        return true;
    }//end isGameOver

    public void findSolution(){
        Query.hasSolution("use_module(library(jpl))"); // only because we call e.g. jpl_pl_syntax/1 below
        String t1 = "consult('puzzle.pl')";
        String state ="[";
        for (int r=0; r<ROWS; r++) {
            for (int c=0; c<COLS; c++) {
                state.concat(getFace(r, c)) + "/";
            }
        }
        state.concat("]");
        String q = "find_solution(" + state +",Moves)";
        String moves = Query.oneSolution(q).get("Moves");
        if(moves[1]).equals("right")){
            moveRight(getRow(_emptyTile),getCol(_emptyTile));
        }
        else if(moves[1].equals("left")){
            moveLeft(getRow(_emptyTile),getCol(_emptyTile));
        }
        else if(move[1].equals("up")){
            moveUp(getRow(_emptyTile),getCol(_emptyTile));
        }
        else if(move[1].equals("down")){
            moveDown(getRow(_emptyTile),getCol(_emptyTile));
        }

    }

    public moveRight(int r, int c){
        exchangeTiles(r,c,1,0);
    }//move a tile right

    public moveLeft(int r, int c){}
    public moveDown(int r, int c){}
    public moveUp(int r, int c){}


}//end class SlidePuzzleModel



////////////////////////////////////////////////////////// class Tile
// Represents the individual "tiles" that slide in puzzle.
class Tile {
    //============================================ instance variables
    private int _row;     // row of final position
    private int _col;     // col of final position
    private String _face;  // string to display
    //end instance variables


    //==================================================== constructor
    public Tile(int row, int col, String face) {
        _row = row;
        _col = col;
        _face = face;
    }//end constructor


    //======================================================== setFace
    public void setFace(String newFace) {
        _face = newFace;
    }//end getFace


    //======================================================== getFace
    public String getFace() {
        return _face;
    }//end getFace


    //=============================================== isInFinalPosition
    public boolean isInFinalPosition(int r, int c) {
        return r==_row && c==_col;
    }//end isInFinalPosition
 
    public int getRow(Tile t){
        return t._row;
    }//get row of a tile

    public int getCol(Tile t){
        return t.col;
    }//get column of a tile
}
