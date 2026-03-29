import java.util.ArrayList;
import java.util.Objects;

public class TuringMachine {
    public static ArrayList<Object> feed;
    public static int pointer;
    private ArrayList<State> states;
    public final int STEP_LIMIT = 1; //to be edited as needed
    private final TileSetGenerator TILE_SET = new TileSetGenerator(this);
    private final TilePlane TILE_PLANE = new TilePlane(getFeed().size());
    private final Tiling TILING = new Tiling(TILE_PLANE, TILE_SET);

    public TuringMachine() {
        feed = new ArrayList<>();
        pointer = 0;
        this.states = new ArrayList<>();
    }
    public TuringMachine(ArrayList<Object> newFeed) {
        feed = newFeed;
        pointer = 0;
        this.states = new ArrayList<>();
    }

    public ArrayList<State> getStates(){
        return states;
    }

    public void addState(State newState){
        this.states.add(newState);
    }

    public ArrayList<Object> getFeed(){
        return feed;
    }

    public void runMachine(){
        int stepCounter = 0;
        State currentState =  states.getFirst();
        do{
            currentState = currentState.executeRule();
            //System.out.println(" " + feed.toString());
            if(stepCounter <= STEP_LIMIT){
                stepCounter++;
            }else{
                System.out.println("Machine doesn't halt within " + STEP_LIMIT + " steps");
                currentState = null;
            }
        }while(currentState != null);
    }

    public void runMachine(ArrayList<Object> newFeed){
        for (int i = 0; i < feed.size(); i++) {
            feed.set(i, newFeed.get(i));
        }
        runMachine();
    }

    public String feedString(){
        StringBuilder s = new StringBuilder();
        boolean first = true;
        for (Object item : feed){
            if (!item.equals("⊥")){
                if (first){
                    s.append(item);
                    first = false;
                }else {
                    s.append(", ").append(item);
                }
            }
        }
        return s.toString();
    }

}

