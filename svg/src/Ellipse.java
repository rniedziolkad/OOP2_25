public class Ellipse extends Shape{
    private final Point center;
    private final double rx, ry;

    public Ellipse(Point center, double rx, double ry, Style style) {
        super(style);
        this.center = center;
        this.rx = rx;
        this.ry = ry;
    }

    @Override
    public String toSvg() {
        return "<ellipse cx=\""+center.getX()+"\" cy=\""+center.getY()+"\"" +
                " rx=\""+rx+"\" ry=\""+ry+"\" "+style.toSvg()+" />";
    }

    @Override
    public String toString() {
        return "Ellipse{" +
                "center=" + center +
                ", rx=" + rx +
                ", ry=" + ry +
                '}';
    }
}
