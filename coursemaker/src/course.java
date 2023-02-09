import java.net.StandardSocketOptions;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class Course {
//    Data fields for the Course data type
    String courseCode;
    int rating;
    int startTime;
    int endTime;
    double credit;
    String days;
    String major;

    public Course(String courseCode, int startTime, int endTime, String days, int rating, double credit ) {
//        data constructor for course data type with various data as arguments for data fields.
        this.courseCode = courseCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.rating = rating;
        this.days = days;
        this.major = extractNonNumeric(courseCode);
        this.credit = credit;
    }

    public String toString(){
// toString method returns the courseCode for a course if called to print or something.
        return this.courseCode;
    }

    public static String extractNonNumeric(String input) {
//        returns the non-numeric part of a string to extract the major of a course code using regex
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll("");
    }

    public static Course addCourse() {
        Scanner in = new Scanner(System.in);
// method to add a course by taking in input from the user and returning a new course created with the given data

        System.out.println("Enter courseCode (Ex: \"CMPU101\"): ");
        String courseCode = in.nextLine();
        System.out.println("Enter start Time (Ex: \"0900\"): ");
        int startTime = in.nextInt();
        // error check for startTime. Hour should be between 08 and 21. Minutes should be between 0 and 60.
        while (startTime < 0 || startTime / 100 > 21 || startTime / 100 < 8 || startTime % 100 > 60) {
            System.out.println("Please enter a valid time between 0800 and 2159 hours: ");
            startTime = in.nextInt();
        }
        System.out.println("Enter the course credit");
        double credit = in.nextDouble();
        System.out.println("Enter end Time (Ex: \"1015\"): ");
        int endTime = in.nextInt();
        while (endTime < 0 || endTime / 100 > 23 || endTime / 100 < 9 || endTime % 100 > 60) {
            System.out.println("Please enter a valid time between 0900 and 2359 hours: ");
            endTime = in.nextInt();
        }

        in.nextLine();
        System.out.println("Enter days of the week (Ex: \"MWF\")  (Monday - M, Tuesday - T, Wednesday - W, Thursday - R, Friday - F: ");
        String days = in.nextLine();
        System.out.println("Enter the priority rating for this course out of 10 (Ex: \"9\"): ");
        int rating = in.nextInt();
        return new Course(courseCode, startTime, endTime, days, rating, credit);

    }

        public static void main(String []args ) {
            Scanner in = new Scanner(System.in);
            List<Tuple<Integer, String>> courseList = new ArrayList<>();
            String response = "Y";
            while (response.equals("Y")) {
                // input for new course
                Course newCourse = addCourse();
                System.out.println("To enter a course, enter Y/N: ");
                response = in.nextLine();
                courseList.add(new Tuple<>(newCourse.rating, newCourse.courseCode));
                System.out.println(newCourse + " successfully added");
            }
            for (Tuple<Integer, String> t : courseList) {
                System.out.println(t.getSecond() + " priority rating: " + t.getFirst());
            }
            Collections.sort(courseList, new TupleComparator());
            Collections.reverse(courseList);
            System.out.println();
            System.out.println("The course list sorted according to priority rating is");
            for (Tuple<Integer, String> t : courseList) {
                System.out.println(t.getSecond() + " priority rating: " + t.getFirst());
            }
        }
}



