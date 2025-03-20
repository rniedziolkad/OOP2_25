public abstract class BeverageDecorator implements Beverage {
    private Beverage decoratedBeverage;

    public BeverageDecorator(Beverage decoratedBeverage) {
        this.decoratedBeverage = decoratedBeverage;
    }

    @Override
    public String serve() {
        if (decoratedBeverage == null)
            return "";
        return decoratedBeverage.serve();
    }
}
