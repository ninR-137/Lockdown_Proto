package main;
import static main.Constants.*;

public class SelectorHandler {
    private int columnIndex;
    private int Xcenter, Ycenter;
    private int rx, ry;
    private boolean isClicked = false;

    public SelectorHandler(int columnIndex){
        this.columnIndex = columnIndex;
        setVariables();
    }

    public void setVariables(){
        Xcenter = selectorPaddingLeft + gridWidth/2;
        Ycenter = paddingTop + ((columnIndex * gridHeight) - (gridHeight/2));
        rx = selectorPaddingLeft;
        ry = paddingTop + (columnIndex * gridHeight);
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


    public int getXcenter() {
        return Xcenter;
    }

    public int getYcenter() {
        return Ycenter;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public boolean isClicked(){
        return isClicked;
    }

}
