package client.sdk;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon DonGill on 30-11-2015.
 */



public class TableModelOpenGames extends AbstractTableModel {

    List<Game> games;

    String[] columnNames = {"Id", "Name"};


    public TableModelOpenGames(ArrayList<Game> gmes){
        games = gmes;
        fireTableStructureChanged();
    }

    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }

    @Override
    public int getColumnCount(){
        return 2;
    }

    @Override
    public int getRowCount(){
        return games.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex){
        Game game = games.get(rowIndex);
        switch (columnIndex){
            case 0: return game.getGameId();
            case 1: return game.getName();
            default:  return null;
        }
    }

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
