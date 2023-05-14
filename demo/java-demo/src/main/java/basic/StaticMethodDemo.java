package basic;

/**
 * @author Steve Zou
 */
public class StaticMethodDemo {
    public static class GoodsAmountCalculator {
        public static double getAmount(double price, int num) {
            return price * num;
        }
    }

    public static void main(String[] args) {
        System.out.println(GoodsAmountCalculator.getAmount(2.3, 2)); // 输出4.6
        System.out.println(GoodsAmountCalculator.getAmount(3.5, 3)); // 输出10.5
    }
}
