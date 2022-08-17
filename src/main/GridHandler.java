package main;
import static main.Constants.*;

public class GridHandler {
    private int Xcenter;
    private int Ycenter;
    private int rowCount;
    private int columnCount;
    private int rx, ry;
    private boolean isClicked = false;

    private boolean isOccupied = false;

    public GridHandler(int rowIndex, int columnIndex){
        this.rowCount = rowIndex + 1;
        this.columnCount = columnIndex + 1;
        rx = paddingLeft + (rowIndex * gridWidth);
        ry = paddingTop + (columnIndex * gridHeight);
        setCenter();
    }


    //NOT YET TESTED
    private void setCenter(){
        Xcenter = paddingLeft + ((rowCount * gridWidth) - (gridWidth/2));
        Ycenter = paddingTop + ((columnCount * gridHeight) - (gridHeight/2));
    }

    public void setCollision(int px, int py){
        // above the bottom
        isClicked = px >= rx &&         // right of the left edge AND
                px <= rx + gridWidth &&    // left of the right edge AND
                py >= ry &&         // below the top AND
                py <= ry + gridHeight;
    }

    public void setCollision(boolean isClicked){
        this.isClicked = isClicked;
    }

    public void setOccupied(boolean isOccupied){
        this.isOccupied = isOccupied;
    }


    public int getXcenter() {
        return Xcenter;
    }

    public int getYcenter() {
        return Ycenter;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public boolean isClicked(){
        return isClicked;
    }

    public boolean isOccupied() {
        return isOccupied;
    }


}
