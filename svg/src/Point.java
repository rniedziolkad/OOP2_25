public class Point {
    public double x;
    public double y;

    @Override
    public String toString(){
        return "("+x+", "+y+")";
    }

    public String toSvg(){
        return "<circle r=\"5\" cx=\""+x+"\" cy=\""+y+"\" fill=\"black\" />";
    }

    public void translate(double dx, double dy){
        x += dx;
        this.y += dy;
    }

    public Point translated(double dx, double dy){
        Point newPoint = new Point();
        newPoint.x = x + dx;
        newPoint.y = this.y + dy;

        return newPoint;
    }
}
