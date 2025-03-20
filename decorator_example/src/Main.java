public class Main {
    public static void main(String[] args) {
        Coffe coffe = new Coffe();
        SugarDecorator sugarCoffe = new SugarDecorator(coffe);
        MilkDecorator milkSugarCoffe = new MilkDecorator(sugarCoffe);
        System.out.println(milkSugarCoffe.serve());

        Beverage tea = new Tea();
        tea = new SugarDecorator(tea);
        tea = new HoneyDecorator(tea);
        tea = new SugarDecorator(tea);
        System.out.println(tea.serve());
    }
}