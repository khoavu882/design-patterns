package sort;

/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 1:56 PM
 */

public class StrategyExample {
    public static void main(String[] args) {
        SortContext context = new SortContext();

        context.setStrategy(new QuickSort());
        context.executeSort(new int[]{3, 1, 4});

        context.setStrategy(new BubbleSort());
        context.executeSort(new int[]{5, 2, 8});
    }
}
