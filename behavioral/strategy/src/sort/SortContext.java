package sort;

/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 1:56 PM
 */

class SortContext {
    private SortStrategy strategy;

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeSort(int[] arr) {
        strategy.sort(arr);
    }
}
