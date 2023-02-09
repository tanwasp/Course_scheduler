import java.util.Comparator;

public class TupleComparator implements Comparator<Tuple<Integer, String>> {
//    class for Tuple Comparator
    @Override
    public int compare(Tuple<Integer, String> t1, Tuple<Integer, String> t2) {
        return t1.getFirst().compareTo(t2.getFirst());
    }
}