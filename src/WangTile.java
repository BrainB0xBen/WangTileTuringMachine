public class WangTile {
    private Object top;
    private Object bottom;
    private Object left;
    private Object right;

    public WangTile(Object top, Object bottom, Object left, Object right) {
        setTop(top);
        setBottom(bottom);
        setLeft(left);
        setRight(right);
    }
    
    public Object getTop() {
        return top;
    }

    public void setTop(Object top) {
        this.top = top;
    }

    public Object getBottom() {
        return bottom;
    }

    public void setBottom(Object bottom) {
        this.bottom = bottom;
    }

    public Object getLeft() {
        return left;
    }

    public void setLeft(Object left) {
        this.left = left;
    }

    public Object getRight() {
        return right;
    }

    public void setRight(Object right) {
        this.right = right;
    }

    public boolean hasValidConections(WangTile above, WangTile below, WangTile previous) {
        boolean topTrue = (above == null) || (above.getBottom() == getTop());
        boolean bottomTrue = (below == null) || (below.getTop() == getBottom());
        boolean sideTrue = (previous == null) || (previous.getRight() == getLeft());
        return topTrue && bottomTrue && sideTrue;
    }

    public String tileString(){
        //String tileString = top.toString() + "`" + bottom.toString() + "`" + left.toString() + "`" + right.toString();
        String tileString
                = "   " + edgeString(top) + "\n"
                + edgeString(left) + " ✖️ " + edgeString(right) + "\n   "
                + edgeString(bottom);
        return tileString;
    }

    public String edgeString(Object edge){
        if(edge == null){
            return "X";
        }else {
            return edge.toString();
        }
    }
}
