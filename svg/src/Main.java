
public class Main {
    public static void main(String[] args) {
        Point p1 = new Point(30 ,40);
        Point p2 = new Point();
        p2.setY(40);
        System.out.println(p1);
        System.out.println(p2);

        Segment seg = new Segment(p1, p2);
        System.out.println(seg);
        p2.setX(60);
        System.out.println(seg);

        Point[] arr = {p1, p2, p1};
        arr[2] = new Point(30, 0);
//        p2.setX(0);

        Polygon poly = new Polygon(arr);
        System.out.println(poly);
        Polygon polyCopy = new Polygon(poly);
        System.out.println(polyCopy);

        poly.setPoint(1, 0, 40);
        System.out.println("Poly: "+poly);
        System.out.println("Kopia: "+polyCopy);

        System.out.println(polyCopy.toSvg());
    }


}