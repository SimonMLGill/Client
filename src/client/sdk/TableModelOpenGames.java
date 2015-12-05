package client.sdk;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon DonGill on 30-11-2015.
 */



public class TableModelOpenGames extends AbstractTableModel {
    /**
     * creating ArrayList of games and Array og the column names of my ArrayList
     */
    List<Game> games;

    String[] columnNames = {"Id", "Name"};
    /**
     *
     * @param gmes ArrayList that is setting the TableModel
     */

    public TableModelOpenGames(ArrayList<Game> gmes){
        games = gmes;
        fireTableStructureChanged();
    }
    /**
     *
     * @param column returns a string of the column name for a specified column
     * @return
     */
    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }
    /**
     * getting the number column in the tableModel, which is specified to 3
     * @return
     */
    @Override
    public int getColumnCount(){
        return 2;
    }
    /**
     * returns the number of rows of the tablemodel
     * @return
     */
    @Override
    public int getRowCount(){
        return games.size();
    }
    /**
     * returns the specified value, an object, from a specific row and column
     * @param rowIndex the specified row
     * @param columnIndex the specified column
     * @return
     */
    public Object getValueAt(int rowIndex, int columnIndex){
        Game game = games.get(rowIndex);
        switch (columnIndex){
            case 0: return game.getGameId();
            case 1: return game.getName();
            default:  return null;
        }
    }
    /**
     *
     * @param game adding a new Score to the ArrayList
     */
    public void addGame(Game game){
        games.add(game);
        fireTableDataChanged();

    }

    public void setGames(ArrayList<Game> gms){
        for (Game gm: gms){
            addGame(gm);
        }
    }


    public void removeGame(int id){
        games.remove(id);
    }

}
