import java.util.ArrayList;
import java.util.List;

public class State{
    public String name;
    public ArrayList<Rule> rules;

    public State(String name, ArrayList<Rule> rules){
        setName(name);
        setRules(rules);
    }
    public State(String name){
        setName(name);
        setRules(new ArrayList<Rule>());
    }

    public void setName(String name){
        this.name = name;
    }
    public void setRules(ArrayList<Rule> rules){
        this.rules = rules;
    }
    public String getName(){
        return this.name;
    }
    public ArrayList<Rule> getRules(){
        return this.rules;
    }

    public void addRule(Rule rule){
        this.rules.add(rule);
    }

    public State executeRule(){
        String debugString = TuringMachine.feed.toString() + " at " + TuringMachine.pointer +": ";

        State nextState = null;
        Object input;
        boolean addStart = false;
        boolean addEnd = false;

        if(TuringMachine.pointer < 0){
            input = "⊥";
            addStart = true;
        }else if(TuringMachine.pointer >= TuringMachine.feed.size()){
            input = "⊥";
            addEnd = true;
        }
        else {
            input = TuringMachine.feed.get(TuringMachine.pointer);
        }

        for (Rule rule : rules) {
            if (rule.getRead().equals(input)) {
                if (addEnd) {
                    TuringMachine.feed.addLast(rule.getWrite());
                }else if (addStart) {
                    TuringMachine.feed.addFirst(rule.getWrite());
                    TuringMachine.pointer = 0;
                } else{
                    TuringMachine.feed.set(TuringMachine.pointer, rule.getWrite());
                }
                debugString += input + " → " + rule.getWrite();
                TuringMachine.pointer += rule.getMove();
                nextState = rule.getGoTo();
            }
        }
        //System.out.println(debugString);

        return nextState;
    }
}