public abstract class Shape {
    // protected pozwala na dostęp z klas dziedziczących
    protected Style style;

    public Shape(Style style) {
        this.style = style;
    }

    public abstract String toSvg();
    public abstract BoundingBox boundingBox();
}
