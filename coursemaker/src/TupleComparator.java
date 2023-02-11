import java.util.Comparator;

public class TupleComparator implements Comparator<Tuple<Double, Course>> {
//    class for Tuple Comparator
    @Override
    public int compare(Tuple<Double, Course> t1, Tuple<Double, Course> t2) {
        return t1.getFirst().compareTo(t2.getFirst());
    }
}