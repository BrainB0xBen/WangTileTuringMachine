public class Rule {
    private Object read;
    private Object write;
    private int move;
    private State goTo;

    public Rule(Object in, Object write, int move, State goTo) {
        setRead(in);
        setWrite(write);
        setMove(move);
        setGoTo(goTo);
    }

    public Object getRead() {
        return read;
    }

    public void setRead(Object read) {
        if (read == null) { read = "⊥"; }
        this.read = read;
    }

    public Object getWrite() {
        return write;
    }

    public void setWrite(Object write) {
        if (write == null) { write = "⊥"; }
        this.write = write;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        if(move < 0){
            this.move = -1;
        }else if(move > 0){
            this.move = 1;
        }else {
            this.move = 0;
        }
    }

    public State getGoTo() {
        return goTo;
    }

    public void setGoTo(State goTo) {
        this.goTo = goTo;
    }
}
