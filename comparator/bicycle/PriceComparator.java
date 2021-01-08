package comparator.bicycle;

import model.Bicycle;

import java.util.Comparator;

public class PriceComparator implements Comparator<Bicycle> {

    /**
     * Comparing by the price.
     * @param o1
     * @param o2
     * @return
     */

    @Override
    public int compare(Bicycle o1, Bicycle o2) {
            return o1.getPrice() - o2.getPrice();

    }
}
