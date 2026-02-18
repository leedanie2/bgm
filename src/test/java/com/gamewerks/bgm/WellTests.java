package com.gamewerks.bgm;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import com.gamewerks.bgm.engine.Well;
import com.gamewerks.bgm.engine.Piece;
import com.gamewerks.bgm.engine.PieceKind;
import com.gamewerks.bgm.util.Position;


public class WellTests {
    private static boolean x = true;
    private static boolean o = false;

    /**
     * Tests whether Well is initialized correctly
     */
    @Test
    public void emptyWellTest() {
        boolean[][] grid = {
          {o, o, o, o},
          {o, x, x, o},
          {o, x, x, o},
          {o, o, o, o}
        };
        Well well = new Well(grid);
        assertFalse(well.isCompletedRow(0));
        assertFalse(well.isCompletedRow(1));
        assertFalse(well.isCompletedRow(2));
        assertFalse(well.isCompletedRow(3));
    }

    /**
     * Tests getWidth() and getHeight() after Well is initialized
     */
    @Test
    public void widthHeightTest() {
        boolean[][] grid = {
          {o, o, o, o},
          {o, x, x, o},
          {o, x, x, o},
          {o, o, o, o}
        };
        Well well = new Well(grid);
        assertEquals(4, well.getWidth());
        assertEquals(4, well.getHeight());
    }

    /**
     * Tests isValidPosition(row, col)
     */
    @Test
    public void isValidPositionTest() {
        boolean[][] grid = {
          {o, o, o, o},
          {o, x, x, o},
          {o, x, x, o},
          {o, o, o, o}
        };
        Well well = new Well(grid);
        assertTrue(well.isValidPosition(0, 0));
        assertTrue(well.isValidPosition(3, 0));
        assertTrue(well.isValidPosition(0, 3));
        assertTrue(well.isValidPosition(2, 2));
        
        assertFalse(well.isValidPosition(4,4));
        assertFalse(well.isValidPosition(4,1));
        assertFalse(well.isValidPosition(1,4));
    }


    /**
     * Tests collides() function on an empty well
     */
    @Test
    public void collidesTest() {
        boolean[][] grid = {
          {o, o, o, o, o},
          {o, o, o, o, o},
          {o, o, o, o, o},
          {o, o, o, o, o},
          {o, o, o, o, o},
          {o, o, o, o, o},
          {o, o, o, o, o}
        };
        Well well = new Well(grid);
        Piece Iblock = new Piece(PieceKind.I, new Position(0,0));
        assertTrue(well.collides(Iblock));
    }

    /**
     * Tests collides() function on a non-empty well
     */
    @Test
    public void collidesLayoutTest() {
        boolean[][] layout = {
          {o, o, o, o},
          {x, x, x, o},
          {o, o, x, o},
          {o, o, o, o},  
          {o, o, o, o},
          {o, o, o, o},
          {o, o, o, o}
        };
        Well well = new Well(4, 4);
        Piece Iblock = new Piece(PieceKind.I, new Position(0,0));
        assertTrue(well.collides(Iblock));
    }
    
    /**
     * Tests addToWell() function on an empty well
     */
    @Test
    public void addtoWellTest() {
        boolean[][] grid = {
          {o, o, o, o},
          {o, o, o, o},
          {o, o, o, o},
          {o, o, o, o},
        };
        Well well = new Well(grid);
        Piece Iblock = new Piece(PieceKind.I, new Position(3,0));
        well.addToWell(Iblock);
        for(int i = 0; i < 4; i++) {
          assertTrue(well.getGrid()[3][i]);
        }
    }
    
    /**
     * Tests deleteRow(n) function
     */
    @Test
    public void deleteRowTest() {
        boolean[][] grid = {
          {o, o, o, o},
          {o, o, o, o},
          {o, o, o, o},
          {x, x, x, x}
        };
        Well well = new Well(grid);
        well.deleteRow(3);

        for(int i = 0; i < 4; i ++) {
          assertFalse(well.getGrid()[3][i]);
        }
    }

    /**
     * Tests deleteRows(List<Integer> rows) function
     */
    @Test
    public void deleteRowsTest() {
        boolean[][] grid = {
          {o, o, o, o},
          {x, x, x, x},
          {o, o, o, o},
          {x, x, x, x}
        };
        Well well = new Well(grid);
        List<Integer> rows = new LinkedList<Integer>();
        rows.add(1);
        rows.add(3);
        well.deleteRows(rows);

        for(int i = 0; i < 4; i ++) {
          assertFalse(well.getGrid()[1][i]);
          assertFalse(well.getGrid()[3][i]);
        }
    }
    
    /**
     * Tests the completedRow()
function in the Well class     */
    @Test 
    public void completedRowTest(){
        boolean[][] grid = {
            {o, o, o, x},
            {o, o, x, x},
            {o, x, x, x},
            {x, x, x, x}
        };
        Well well = new Well(grid);
        for(int i = 0; i<3; i++){
          assertFalse(well.isCompletedRow(i));
        }
        assertTrue(well.isCompletedRow(3));
    }
    /**
     * Tests the completedRows() function in the Well class     
     */
    @Test 
    public void getCompletedRowsTest(){
        boolean[][] grid = {
            {x, x, x, x},
            {o, x, x, o},
            {x, o, o, x},
            {x, x, x, x}
        };
        Well well = new Well(grid);
        List<Integer> compRows = well.getCompletedRows();
        assertEquals(3, compRows.remove(0));
        assertEquals(0, compRows.remove(0));
    }
    /**
     * Tests the getGrid() function in the Well class     
     */
    @Test 
    public void getGridTest(){
        boolean[][] grid = {
            {o, x, x, o},
            {o, x, x, o},
            {o, x, x, o},
            {o, x, x, o}
        };
        Well well = new Well(grid);
        for(int i = 0; i< 4; i++){
          assertFalse(well.getGrid()[i][0]);
          assertTrue(well.getGrid()[i][1]);
          assertTrue(well.getGrid()[i][2]);
          assertFalse(well.getGrid()[i][3]);
        }
    }
}