package src;
import java.util.ArrayList;
public class MaxHeapByQuantity {

    private ArrayList<ProductSummary> heap = new ArrayList<>();

    public void insert(ProductSummary ps) {
        heap.add(ps);
        heapifyUp(heap.size() - 1);
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap.get(index).getTotalQuantity() >
                heap.get(parent).getTotalQuantity()) {

                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    public ProductSummary getMax() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    private void swap(int i, int j) {
        ProductSummary temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}   