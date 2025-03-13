import static java.lang.Math.pow;

public class Segment {
    private final Point a;
    private final Point b;

    public Segment(Point a, Point b) {
        this.a = new Point(a);      // wykorzystujemy konstrukor kopiujący
        this.b = new Point(b);
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public double length(){
        return Math.sqrt(pow(a.getX()-b.getX(), 2) + pow(a.getY()-b.getY(), 2));
    }

    // zwraca dwa możliwe odcinki prostopadłe zaczynające się w punkcie origin
    public Segment[] perpendicularSegments(Point origin){
        return perpendicularSegments(origin, length());
    }

    public Segment[] perpendicularSegments(Point origin, double length){
        double dx = b.getX() - a.getX();
        double dy = b.getY() - a.getY();
        dx = dx/length() * length;
        dy = dy/length() * length;

        return new Segment[]{
                new Segment(origin, new Point(origin.getX()+dx, origin.getY()-dy)),
                new Segment(origin, new Point(origin.getX()-dx, origin.getY()+dy))
        };
    }


    public static Segment maxSegment(Segment[] arr){
        if (arr.length == 0)
            return null;

        Segment max = arr[0];
        for(int i=1; i < arr.length; i++){
            if(arr[i].length() > max.length()){
                max = arr[i];
            }
        }
        return max;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    public Point getCenter() {
        return new Point((a.getX()+b.getX())/2, (a.getY()+b.getY())/2);
    }
}
