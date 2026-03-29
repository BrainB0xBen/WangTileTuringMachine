import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TuringMachine TM = binaryPlusOne();



        TM.runMachine();


        TileSetGenerator tsg = new TileSetGenerator(TM);
        TilePlane tilePlane = new TilePlane(TM.getFeed().size() +1);
        Tiling tiling = new Tiling(tilePlane, tsg);
        //System.out.println(tsg.getTileSet().size());
        //System.out.println(tsg.getTileSetString());
        //System.out.println(tilePlane.planeString());
    }

    public static TuringMachine binaryPlusOne() {
        TuringMachine TM = new TuringMachine(new ArrayList<>(List.of(1,0,1)));
        //make states
        State stateA = new State("A");
        State stateB = new State("B");
        //set rules for State A
        stateA.addRule(new Rule(0,0,1, stateA));
        stateA.addRule(new Rule(1,1,1, stateA));
        stateA.addRule(new Rule(null,null,-1, stateB));
        //set rules for State B
        stateB.addRule(new Rule(0,1,0, null));
        stateB.addRule(new Rule(1,0,-1, stateB));
        stateB.addRule(new Rule(null,1,0, null));
        //add states to TM
        TM.addState(stateA);
        TM.addState(stateB);
        return TM;
    }

    public static TuringMachine singleStateTester() {
        TuringMachine TM = new TuringMachine(new ArrayList<>(List.of(1,0,1)));
        //make states
        State stateA = new State("A");
        //set rules for State A
        stateA.addRule(new Rule(0,0,0, null));
        stateA.addRule(new Rule(1,0,1, stateA));
        stateA.addRule(new Rule(null,null,0, null));
        //add states to TM
        TM.addState(stateA);

        return TM;
    }


}