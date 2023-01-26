import java.util.Scanner;
public class Course {
    Scanner in = new Scanner(System.in);
    String courseCode;
    int rating;
    int startTime;
    int endTime;

    public Course(String courseCode, int startTime, int endTime, String days, int rating ) {
        this.courseCode = courseCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.rating = rating;
        this.days = days;
    }


    public static void main(String[] args) {
        course timeToDie = new Course("CMPU102", 900, 1015, "MWF", 9);
        System.out.println(timeToDie.startTime);
    }
}