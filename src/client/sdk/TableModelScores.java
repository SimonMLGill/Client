package client.sdk;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon DonGill on 04-12-2015.
 */
public class TableModelScores extends AbstractTableModel {

    /**
     * creating ArrayList of scores and Array og the column names of my ArrayList
     */
    List<Score> scores;

    String[] columnNames = {"Bearer", "Score", "Game ID"};

    /**
     *
     * @param scrs ArrayList that is setting the TableModel
     */
    public TableModelScores(ArrayList<Score> scrs){
        scores = scrs;
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
        return 3;
    }

    /**
     * returns the number of rows of the tablemodel
     * @return
     */

    @Override
    public int getRowCount(){
        return scores.size();
    }

    /**
     * returns the specified value, an object, from a specific row and column
     * @param rowIndex the specified row
     * @param columnIndex the specified column
     * @return
     */

    public Object getValueAt(int rowIndex, int columnIndex){
        try {
            Score score = scores.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return score.getGame().getWinner().getUsername();
                case 1:
                    return score.getScore();
                case 2:
                    return score.getGame().getGameId();
                default:
                    return null;
            }
        } catch (NullPointerException n){
            n.printStackTrace();
        } return null;
    }

    /**
     *
     * @param score adding a new Score to the ArrayList
     */
    public void addScore(Score score){
        scores.add(score);
        fireTableDataChanged();

    }

    public void setScores(ArrayList<Score> scres){
        for (Score sc: scres){
            addScore(sc);
        }
    }


    public void removeGame(int id){
        scores.remove(id);
    }

}
