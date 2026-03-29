import java.util.ArrayList;

public class TileSetGenerator {
    private  ArrayList<WangTile> tileSet;
    private TuringMachine machine;

    public TileSetGenerator(TuringMachine machine) {
        setMachine(machine);
        this.tileSet = generateTileSet();
    }

    public ArrayList<WangTile> getTileSet() {
        return tileSet;
    }

    public void setMachine(TuringMachine machine) {
        this.machine = machine;
    }

    public TuringMachine getMachine() {
        return machine;
    }

    public ArrayList<WangTile> generateTileSet() {//TODO Finish (and probably change whats been done so far
        ArrayList<WangTile> tileSet = new ArrayList<>();


        if (machine != null && machine.getStates() != null) {

            ArrayList<State> states = machine.getStates();



            for  (State state : states) {
                String name = state.getName();
                ArrayList<Rule> Rules =  state.getRules();
                for  (Rule rule : Rules) {
                    tileSet = addTile(tileSet, new WangTile(null, null, null, rule.getRead()));
                    tileSet = addTile(tileSet, new WangTile(null, null, null, edgeLable(name, rule.getRead())));

                    tileSet = addTile(tileSet, (new WangTile(name, null, rule.getRead(), edgeLable(name,  rule.getRead()))));
                    tileSet = addTile(tileSet, (new WangTile(null, name, rule.getRead(), edgeLable(name,  rule.getRead()))));

                    String next;
                    if(rule.getGoTo() == null){
                        next = "H";
                    }else {
                        next = rule.getGoTo().getName();
                    }
                    if(rule.getMove()>0){
                        tileSet = addTile(tileSet, (new WangTile(null, next, edgeLable(name, rule.getRead()), rule.getWrite())));
                    }else if(rule.getMove()<0){
                        tileSet = addTile(tileSet, (new WangTile(next, null, edgeLable(name, rule.getRead()), rule.getWrite())));
                    }else {
                        tileSet = addTile(tileSet, (new WangTile(null, null, edgeLable(name, rule.getRead()), rule.getWrite())));
                    }
                }
            }
            
            
        }
        
        

        return tileSet;
    }
    
    
    public ArrayList<WangTile> addTile(ArrayList<WangTile> tileSet, WangTile tile) {
        boolean newTile = true;
        for (WangTile t : tileSet) {
            if (t.equals(tile)) {
                newTile = false;
                break;
            }
        }
        if (newTile) {tileSet.add(tile);}
        return tileSet;
    }

    public String edgeLable(String stateName, Object tapeSymbol){
        return stateName+tapeSymbol;
    }

    public String getTileSetString(){
        String tileSetString = "-------------\n";
        for  (WangTile tile : tileSet){
            tileSetString += tile.tileString() + "\n-------------\n";
        }

        return tileSetString;
    }
}
