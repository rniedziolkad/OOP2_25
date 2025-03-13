
public class Polygon extends Shape{
    private final Point[] vertices;

    public static Polygon square(Segment diagonal, Style style){
        Segment[] perpendiculars = diagonal.perpendicularSegments(
                diagonal.getCenter(), diagonal.length()/2
        );
        return new Polygon(new Point[]{
                diagonal.getA(),
                perpendiculars[0].getB(),
                diagonal.getB(),
                perpendiculars[1].getB()
        }, style);
    }

    // konstruktor dokonuje głębokiej kopii tablicy
    public Polygon(Point[] vertices) {
        this(vertices, new Style("none", "black", 1));
    }
    // konstruktor kopiujący (głęboka kopia)
    public Polygon(Polygon other) {
        this(other.vertices, other.style);
    }

    public Polygon(Point[] vertices, Style style) {
        super(style);
        this.vertices = new Point[vertices.length];
        for(int i=0; i<vertices.length; i++){
            this.vertices[i] = new Point(vertices[i]);
        }
    }

    public void setPoint(int ix, int x, int y){
        this.vertices[ix].setX(x);
        this.vertices[ix].setY(y);
    }

    @Override
    public BoundingBox boundingBox(){
        if(vertices.length == 0){
            return null;
        }
        double minX = vertices[0].getX();
        double maxX = vertices[0].getX();
        double minY = vertices[0].getY();
        double maxY = vertices[0].getY();
        for(int i=1; i<vertices.length; i++){
            if(vertices[i].getX() < minX) minX = vertices[i].getX();
            if(vertices[i].getX() > maxX) maxX = vertices[i].getX();
            if(vertices[i].getY() < minY) minY = vertices[i].getY();
            if(vertices[i].getY() > maxY) maxY = vertices[i].getY();
        }
        return new BoundingBox(minX, minY, maxX-minX, maxY-minY);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Point p : vertices) {
            s.append(p.getX()).append(",").append(p.getY()).append(" ");
        }
        return s.toString().trim();
    }

    @Override
    public String toSvg() {
        return "<polygon points=\""+
                this
                +"\" "+style.toSvg()+" />";
    }
}
