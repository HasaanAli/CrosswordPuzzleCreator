/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

/**
 *
 * @author hasaan
 */
public class Settings {
    private GridSize gridSize;
    private int boxSize;

    private class GridSize {
        int columns, rows;
        public GridSize() {
            columns=rows=0;
        }
    }
    
}
