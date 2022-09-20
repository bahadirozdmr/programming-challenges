import java.util.*;

public class LRUCache {

    private final int size;
    private final Map<Integer,Integer> valueMap= new HashMap<>();
    private final Map<Integer,Integer> countMap= new HashMap<>();
    private final TreeMap<Integer, List<Integer>> frequencyMap=new TreeMap<>();
    public LRUCache(int size) {
        this.size=size;
    }
    //put /get ok
    // now manage size property
    public void put(int key,int value) {
        if (!valueMap.containsKey(key) && size > 0) {
            if (valueMap.size() == size) {
                int lowestCount = frequencyMap.firstKey();
                int keytoDelete = frequencyMap.get(lowestCount).remove(0);
                if (frequencyMap.get(lowestCount).size() == 0) {
                    frequencyMap.remove(lowestCount);
                }
                valueMap.remove(keytoDelete);
                countMap.remove(keytoDelete);
            }
            valueMap.put(key, value);
            countMap.put(key, 1);
            frequencyMap.computeIfAbsent(key, k -> new LinkedList<>()).add(key);
        } else if (size > 0) {
            valueMap.put(key, value);
            updatelist(key);
        }
    }

    public int get(int key){
        if(!valueMap.containsKey(key) || size ==0 ) {
            return -1;
        }
        //get current frequency & remove from frequency map
        updatelist(key);
        return valueMap.get(key);
    }

    private void updatelist(int key) {
        int frequency=countMap.get(key);
        frequencyMap.get(frequency).remove(key);
        if(frequencyMap.get(frequency).size() == 0) {
            frequencyMap.remove(key);
        }
        frequencyMap.computeIfAbsent(frequency+1,k-> new LinkedList<>()).add(key);
        countMap.put(key,frequency+1);
    }
}
