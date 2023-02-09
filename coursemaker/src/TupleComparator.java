import java.util.Comparator;

public class TupleComparator implements Comparator<Tuple<Integer, Course>> {
//    class for Tuple Comparator
    @Override
    public int compare(Tuple<Integer, Course> t1, Tuple<Integer, Course> t2) {
        return t1.getFirst().compareTo(t2.getFirst());
    }
}