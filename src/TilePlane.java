import java.util.ArrayList;
import java.util.List;

public class TilePlane {
    private ArrayList<WangTile[]> planeArr = new ArrayList<WangTile[]>();
    final private int FEED_LENGTH; //TODO potentially should be both dynamic? bc ⊥
    //2d array, but we'll make each arr columns to make it like the feed over time across the arrays
    TilePlane(int feedLength){
        this.FEED_LENGTH = feedLength;
    }

    public ArrayList<WangTile[]> getPlaneArr() {
        return planeArr;
    }

    public int getColumns(){
        return planeArr.size();
    }

    public void addColumn(){
        planeArr.add(new WangTile[FEED_LENGTH]);
    }

    public WangTile getTileAt(int x, int y){
        return planeArr.get(x)[y];
    }


    public boolean isValidPlacement(WangTile tile, int x, int y) {
        if (planeArr == null || planeArr.isEmpty()) {
            return true;
        } else {
            WangTile above;
            WangTile below;
            WangTile prev;

            if (planeArr.size() < x) {
                above = null;
                below = null;
            }else {
                if(getTileAt(x, y - 1) == null){
                    above = null;
                }else{
                    above = getTileAt(x, y - 1);
                }
                if(getTileAt(x, y + 1) == null){
                    below = null;
                }else{
                    below = getTileAt(x, y + 1);
                }
            }
            if (x==0) {
                prev = null;
            }else {
                prev = getTileAt(x-1, y);
            }

            return tile.hasValidConections(above, below, prev);
        }
    }
   /* */
    public void addTile(WangTile tile,  int x, int y){
        //todo
        if (planeArr == null || planeArr.size() <= 0 ) {
            addColumn();
        }
        WangTile[] column = planeArr.get(x);
        column[y] = tile;
        planeArr.set(x, column);
    }

    public String planeString(){
        String planeString = "";
        for (WangTile[] column : planeArr) {
            for (WangTile tile : column) {
                if(tile != null) {
                    planeString += tile.tileString() + "\n";//todo make good, this only works bc we only have 1 column
                }
            }
        }
        return  planeString;
    }
}
