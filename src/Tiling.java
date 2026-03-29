import java.util.ArrayList;

public class Tiling {
    private TilePlane tilePlane;
    private TileSetGenerator tileSet;
    
    public  Tiling(TilePlane tilePlane,  TileSetGenerator tileSet){
        setTilePlane(tilePlane);
        setTileSet(tileSet);
    }
    
    public TileSetGenerator getTileSet(){
        return tileSet;
    }
    
    public void setTileSet(TileSetGenerator tileSet){
        this.tileSet = tileSet;
        placeInitialTiles();
    }
    
    public TilePlane getTilePlane(){
        return tilePlane;
    }
    
    public void setTilePlane(TilePlane tilePlane){
        this.tilePlane = tilePlane;
        
    }
    
    public void placeInitialTiles(){
        ArrayList<Object> initialFeed = tileSet.getMachine().getFeed();
        Object symbol;
        symbol = tileSet.edgeLable(tileSet.getMachine().getStates().getFirst().name,initialFeed.getFirst());
        placeTile(getTileForStartingSymbol(symbol), 0, 0);
        for(int i = 1; i < initialFeed.size(); i++){
            symbol = initialFeed.get(i);
            placeTile(getTileForStartingSymbol(symbol), 0, i);
        }
    }

    public WangTile getTileForStartingSymbol(Object symbol){
        for (WangTile tile : tileSet.getTileSet()){
            boolean isInitial = tile.getTop() == null && tile.getBottom() == null && tile.getLeft() == null;
            if(isInitial&& symbol.equals(tile.getRight())){
                return tile;
            }
        }
        return null;
    }

    public WangTile getTileForLeftSymbol(Object symbol, int x, int y){
        for (WangTile tile : tileSet.getTileSet()){
            if(tile.getLeft().equals(symbol) && tilePlane.isValidPlacement(tile, x, y)){
                return tile;
            }
        }
        return null;
    }

    public void placeTile(WangTile tile, int x, int y){
        if (tilePlane.isValidPlacement(tile, x, y)) {
            //System.out.println(tile.tileString());
            tilePlane.addTile(tile, x, y);
        }
    }

    public boolean placeTiles(boolean tiling, int step){
        if(tiling){
            int head = TuringMachine.pointer;
            WangTile tileAtHead = tilePlane.getTileAt(step, head);
            Object headPrev = tileAtHead.getTop();
            //find next tile at head
            //WangTile newHeadTile =
            //find tiles in direction of move first

        }
        return placeTiles(tiling, step+1);
    }

}
