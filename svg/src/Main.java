
public class Main {
    public static void main(String[] args) {
        Point p = new Point();
        p.x = 20.0;
        p.y = 20.0;
        System.out.println("P= "+p);
        System.out.println(p.toSvg());

        p.translate(5, -5);
        System.out.println("translate: "+p);

        System.out.println("translated: "+p.translated(3, 3));
        Point translated = p.translated(3, 3);
        System.out.println("translated 2: "+translated);
    }
}