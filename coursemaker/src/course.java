import java.net.StandardSocketOptions;
import java.util.Scanner;
public class Course {
    String courseCode;
    int rating;
    int startTime;
    int endTime;
    String days;
    String major;

    public Course(String courseCode, int startTime, int endTime, String days, int rating ) {
        this.courseCode = courseCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.rating = rating;
        this.days = days;
        this.major = courseCode; // need to use regular expression to get non number part of courseCode
    }

    public String toString(){
        return this.courseCode;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Course course1 = new Course("CMPU102", 0900, 1015, "MWF", 9);

        Course[] courseList;
        String response = "Y";
        while (response.equals("Y")) {
            // input for new course

            System.out.println("Enter courseCode (Ex: \"CMPU101\"): ");
            String courseCode = in.nextLine();
            System.out.println("Enter start Time (Ex: \"0900\"): ");
            int startTime = in.nextInt();
            // error check for startTime. Hour should be between 08 and 21. Minutes should be between 0 and 60.
            while (startTime < 0 || startTime/100 > 21 || startTime/100 < 8|| startTime%100 > 60 ){
                System.out.println("Please enter a valid time between 0800 and 2159 hours: ");
                startTime = in.nextInt();
            }


            System.out.println("Enter end Time (Ex: \"1015\"): ");
            int endTime = in.nextInt();
            while (endTime < 0 || endTime/100 > 23 || endTime/100 < 9|| endTime%100 > 60 ){
                System.out.println("Please enter a valid time between 0900 and 2359 hours: ");
                endTime = in.nextInt();
            }


            in.nextLine();
            System.out.println("Enter days of the week (Ex: \"MWF\")  (Monday - M, Tuesday - T, Wednesday - W, Thursday - R, Friday - F: ");
            String days = in.nextLine();
            System.out.println("Enter the priority rating for this course out of 10 (Ex: \"9\"): ");
            int rating = in.nextInt();

            Course newCourse = new Course(courseCode, startTime, endTime, days, rating);
            // courseList.add(newCourse);
            in.nextLine();
            System.out.println("To enter a course, enter Y/N: ");
            response = in.nextLine();

            System.out.println(newCourse);
        }
    }
}
