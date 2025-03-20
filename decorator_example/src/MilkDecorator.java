public class MilkDecorator extends BeverageDecorator {
    public MilkDecorator(Beverage decoratedBeverage) {
        super(decoratedBeverage);
    }
    @Override
    public String serve() {
        return super.serve() + " + mleko";
    }
}
