import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
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

        // tworzenie stylu
        Style style = new Style("red", "black", 2.0);
        Polygon poly = new Polygon(arr, style);
        System.out.println(poly);
        Polygon polyCopy = new Polygon(poly);
        System.out.println(polyCopy);

        poly.setPoint(1, 0, 40);
        System.out.println("Poly: "+poly);
        System.out.println("Kopia: "+polyCopy);

        System.out.println(polyCopy.toSvg());


        SvgScene scene = new SvgScene();
        scene.addShape(poly);
        scene.addShape(polyCopy);
        polyCopy.setPoint(0, -50, -50);
        polyCopy.setPoint(1, 0, 0);
        polyCopy.setPoint(2, -100, -30);

        Polygon square = new Polygon(new Point[]{
                new Point(70, 70),
                new Point(130, 70),
                new Point(130, 130),
                new Point(70, 130)
        });
        scene.addShape(square);

        Segment diag = new Segment(
                new Point(100, 100),
                new Point(140, 140)
        );
        // tworzenie kwadratu
        Polygon square2 = Polygon.square(diag, style);
        System.out.println("square2: "+square2.toSvg());
        scene.addShape(square2);

        // tworzenie elipsy
        Ellipse ellipse = new Ellipse(new Point(-50, -50), 40, 30, style);
        System.out.println(ellipse);
        scene.addShape(ellipse);

        /* TODO: klasa Text dziedzicząca po Shape (element <text> svg)
        *  - String text
        *  - x, y (lewy górny róg, można użyć Point)
        *  - textLength (przy okazji szerokość obiektu, do BoundingBox)
        *  - fontSize (przy okazji wysokość obiektu)
        *  + konstruktor
        *  + toSvg() <text> </text> z atrybutami:
        *    - x, y, textLength, font-size, style (z klasy Shape)
        *  + boundingBox()
        * */

        System.out.println("Scena svg:");
        System.out.println(scene.toSvg());
        System.out.println(polyCopy.boundingBox());
        scene.save("rysunek.svg");
    }


}