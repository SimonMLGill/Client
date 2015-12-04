package client.sdk;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon DonGill on 04-12-2015.
 */
public class TableModelScores extends AbstractTableModel {
    List<Score> scores;

    String[] columnNames = {"Bearer", "Score", "Game ID"};


    public TableModelScores(ArrayList<Score> scrs){
        scores = scrs;
        fireTableStructureChanged();
    }

    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }

    @Override
    public int getColumnCount(){
        return 3;
    }

    @Override
    public int getRowCount(){
        return scores.size();
    }

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
