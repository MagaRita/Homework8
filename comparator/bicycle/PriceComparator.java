package comparator.bicycle;

import model.Bicycle;

import java.util.Comparator;

public class PriceComparator implements Comparator<Bicycle> {

  /*  private boolean desc;

    public PriceComparator(boolean desc){
        this.desc = desc;
    }
   */

    @Override
    public int compare(Bicycle o1, Bicycle o2) {
       // if(desc)
       //     return o2.getPrice() - o1.getPrice();
       // else
            return o1.getPrice() - o2.getPrice();

    }
}
