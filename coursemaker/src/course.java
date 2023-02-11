import java.net.StandardSocketOptions;
import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Course {
//    Data fields for the Course data type
    String courseCode;
    double rating;
    int startTime;
    int endTime;
    double credit;
    String days;
    String major;

    public Course(String courseCode, int startTime, int endTime, String days, double rating, double credit ) {
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
        int startTime = 0;
        System.out.println("Enter start Time (Ex: \"0900\"): ");
        // error check for startTime. Hour should be between 08 and 21. Minutes should be between 0 and 60.
        while (true) {
            try {
                startTime = in.nextInt();
                if (!(startTime < 0 || startTime / 100 > 21 || startTime / 100 < 8 || startTime % 100 > 60)) {
                    break;
                }
                else{
                    System.out.println("Please enter a valid time between 0800 and 2159 hours: ");
                }
            } catch (InputMismatchException e){
                System.out.println("Please enter a valid data type");
                in.nextLine();
            }
        }

        System.out.println("Enter end Time (Ex: \"1015\"): ");
        int endTime = 0;
        while (true) {
            try {
                endTime = in.nextInt();
                if (!(endTime < 0 || endTime / 100 > 23 || endTime / 100 < 9 || endTime % 100 > 60)) {
                    break;
                }
                else{
                    System.out.println("Please enter a valid time between 0900 and 2359 hours: ");
                }
            } catch (InputMismatchException e){
                System.out.println("Please enter a valid data type");
                in.nextLine();
            }
        }


        System.out.println("Enter the course credit");
        double credit = 0;
        while (true) {
            try {
                credit = in.nextDouble();
                if (credit == 0.5 || credit == 1) {
                    break;
                }
                else{
                    System.out.println("Please enter a credit number for this course (0.5 or 1)");
                }
            } catch (InputMismatchException e){
                System.out.println("Please enter a valid data type");
                in.nextLine();
            }
        }
        in.nextLine();
        System.out.println("Enter days of the week (Ex: \"MWF\")  (Monday - M, Tuesday - T, Wednesday - W, Thursday - R, Friday - F: ");
        // Check for errors in input days
        String days = in.nextLine();

        System.out.println("Enter the priority rating for this course out of 10 (Ex: \"9.5\"): ");
        double rating = 0;
        while (true) {
            try {
                rating = in.nextDouble();
                if (rating >= 0 && rating <= 10) {
                    break;
                }
                else{
                    System.out.println("Please enter a valid priority number between 0 and 10");
                }
            } catch (InputMismatchException e){
                System.out.println("Please enter a valid data type");
                in.nextLine();
            }
        }
        return new Course(courseCode, startTime, endTime, days, rating, credit);
    }
    public static Boolean goodCourse(Course course1, Course course2){
        if ((course2.startTime >= course1.startTime && course2.startTime <= course1.endTime) || (course2.endTime >= course1.startTime && course2.endTime <= course1.endTime)) {
            for (int i = 0; i < course2.days.length(); i++) {
                if (course1.days.contains(Character.toString(course2.days.charAt(i)))) {
                    return false;
                }
            }
        }
        return true;
    }

        public static void main(String []args ) {
            Scanner in = new Scanner(System.in);
            List<Tuple<Double, Course>> courseList = new ArrayList<>();
            String response = "Y";
            while (response.equals("Y")) {
                // input for new course
                Course newCourse = addCourse();
                courseList.add(new Tuple<>(newCourse.rating, newCourse));
                System.out.println(newCourse + " successfully added");
                System.out.println("To enter a course, enter Y/N: ");
                response = in.nextLine();
            }
//            for (Tuple<Integer, Course> t : courseList) {
//                System.out.println(t.getSecond() + " priority rating: " + t.getFirst());
//            }
            Collections.sort(courseList, new TupleComparator());
            Collections.reverse(courseList);
            System.out.println();
//            System.out.println("The course list sorted according to priority rating is");
//            for (Tuple<Integer, Course> t : courseList) {
//                System.out.println(t.getSecond() + " priority rating: " + t.getFirst());
//            }


            List<Course> schedule = new ArrayList<>();
            schedule.add(courseList.get(0).getSecond());
            double totalCredits = courseList.get(0).getSecond().credit;
            int i = 1;
            while (totalCredits < 4.5 && i < courseList.size()){
                if (goodCourse(courseList.get(i-1).getSecond(), courseList.get(i).getSecond())){
                    schedule.add(courseList.get(i).getSecond());
                    totalCredits += courseList.get(i).getSecond().credit;
                }
                i++;
            }

//            for (int i = 1; i < courseList.size(); i++){
//                if (goodCourse(courseList.get(i-1).getSecond(), courseList.get(i).getSecond())){
//                    schedule.add(courseList.get(i).getSecond());
//                }
//            }

            System.out.println("Here is the total course list");
            for (Tuple<Double, Course> t : courseList) {
                System.out.println(t.getSecond());
            }

            for (Course t : schedule){
                System.out.println(t);
            }
            if (totalCredits < 3.5){
                System.out.println("Please enter more courses.");
            }
        }
}