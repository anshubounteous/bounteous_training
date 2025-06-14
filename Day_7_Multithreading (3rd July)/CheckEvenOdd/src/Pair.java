public class Pair<K, V>{
    private K key;
    private V value;
    
    public void add(K key, V value){
        this.key = key;
        this.value = value;
    }
    
    public V get(K key){
        return this.value;
    }


}
