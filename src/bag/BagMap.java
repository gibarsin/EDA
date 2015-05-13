package bag;

import map.singlemap.SingleMap;
import map.singlemap.closedhashing.SingleMapClosedImpl;

public class BagMap<T> implements Bag<T> {
    SingleMap<T,Integer> map;

    public BagMap() {
        map = new SingleMapClosedImpl<T, Integer>(10);
    }

    @Override
    public void add(T value) {
        Integer count = map.get(value);
        if(count == null)
            map.put(value, 1);
        else
            map.put(value, count+1);
    }

    @Override
    public int occurencesOf(T value) {
        Integer count = map.get(value);
        if(count == null)
            return 0;
        return count;
    }

    @Override
    public void remove(T value) {
        Integer count = map.get(value);

        if (count != null) {
            if (count == 1)
                map.remove(value);
            else
                map.put(value, count - 1);
        }
    }
}
