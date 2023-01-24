public class course {
    String courseCode;
    int rating;
    int startTime;
    int endTime;

    public course(String courseA, int Time1, int Time2, String daysOfTheWeek, int priorityRating ) {
        courseCode = courseA;
        startTime = Time1;
        endTime = Time2;
        rating = priorityRating;
    }


    public static void main(String[] args) {
        course timeToDie = new course("CMPU102", 900, 1015, "MWF", 9);
        System.out.println(timeToDie.startTime);
    }
}