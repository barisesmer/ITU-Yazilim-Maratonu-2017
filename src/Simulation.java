import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by baris on 05.03.2017.
 */
public class Simulation {
    static ArrayList<Course> courses = new ArrayList<>();
    static ArrayList<Classroom> classrooms = new ArrayList<>();
    static ArrayList<Student> students = new ArrayList<>();
    public static void  main(String[] args) throws IOException {
        String input = "big";
        FastReader sc = new FastReader("./in/" + input);

        int L = sc.nextInt();
        for (int i = 0; i < L; i++) {
            String line = sc.nextLine();
            String[] tokens = line.split(" ");
            String courseId = tokens[0];
            int credit = Integer.parseInt(tokens[1]);
            int preqNum = Integer.parseInt(tokens[2]);
            ArrayList<Course> preqList = new ArrayList<>();
            if(preqNum != 0){
                line = sc.nextLine();
                tokens = line.split(" ");
                for (int j = 0; j < preqNum; j++) {
                    Course c = new Course(tokens[j]);
                    if(!courses.contains(c)){
                        courses.add(c);
                    }
                    preqList.add(courses.get(courses.indexOf(c)));
                }
            }
            Course course = new Course(courseId,credit, preqNum, preqList);
            if (!courses.contains(course)){
                courses.add(course);
            }else{
                courses.set(courses.indexOf(course), course);
            }
        }
        //classrooms
        int X = sc.nextInt();
        for (int i = 0; i < X; i++) {
            String line = sc.nextLine();
            String[] tokens = line.split(" ");
            int classroomNumber = Integer.parseInt(tokens[0]);
            String courseId = tokens[1];
            Course course = new Course(courseId);
            int day = Integer.parseInt(tokens[2]);
            int startHour = Integer.parseInt(tokens[3]);
            int endHour = Integer.parseInt(tokens[4]);
            int capacity = Integer.parseInt(tokens[5]);

            Classroom classroom = new Classroom(courses.get(courses.indexOf(course)) , classroomNumber, day,startHour, endHour, capacity);
            classroom.course.sections.add(classroom);
            classrooms.add(classroom);
        }

        //students
        int S = sc.nextInt();
        for (int i = 0; i < S; i++) {
            int studentId = sc.nextInt();
            int minCredit = sc.nextInt();
            int maxCredit = sc.nextInt();
            int numCompletedCourse= sc.nextInt();
            int numWantedCourses = sc.nextInt();
            ArrayList<Course> completedCourses = new ArrayList<>();
            HashMap<Course, Integer> wantedCourses = new HashMap<>();
            if (numCompletedCourse != 0){
                String line = sc.nextLine();
                String[] tokens = line.split(" ");
                for (int j = 0; j < numCompletedCourse; j++) {
                    completedCourses.add(courses.get(courses.indexOf(new Course(tokens[j]))));
                }
            }
            for (int j = 0; j < numWantedCourses; j++) {
                String line = sc.nextLine();
                String[] tokens = line.split(" ");
                String courseId = tokens[0];
                int value = Integer.parseInt(tokens[1]);
                wantedCourses.put(courses.get(courses.indexOf(new Course(courseId))), value);
            }
            students.add(new Student(studentId, minCredit, maxCredit, numWantedCourses, numCompletedCourse, completedCourses,wantedCourses ));
        }

        Collections.sort(courses, new CompCoursesByPreqNum());
        Collections.sort(students, new CompareStudentsByMinCredit());
        for (int i = 0; i < students.size(); i++) {
            Student st = students.get(i);
            st.wantedCourses = (HashMap<Course, Integer>) MapUtil.sortByValue(st.wantedCourses);
            for (Map.Entry<Course, Integer> entry : st.wantedCourses.entrySet())
            {
                st.addCourse(entry.getKey());
            }
        }

        for (int i = 0; i < students.size(); i++) {
            Student st = students.get(i);
            for (int j = 0; j < courses.size(); j++) {
                if (st.curCredit >= st.minCredit){
                    break;
                }
                Course course = courses.get(j);
                st.addCourse(course);

            }
        }


        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            for (int j = 0; j < courses.size(); j++) {
                Course course = courses.get(j);
                student.addCourse(course);
            }
        }



        //OUTPUT
        
        ArrayList<Student> scheduledStudents = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            Student st = students.get(i);
            if (st.currentCourses.size() > 0){
                scheduledStudents.add(st);
            }
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./output/" + input));
        bufferedWriter.write(scheduledStudents.size() + "");
        bufferedWriter.newLine();
        for (int i = 0; i < scheduledStudents.size(); i++) {
            Student student = scheduledStudents.get(i);
            bufferedWriter.write(student.studentId + " " + student.currentCourses.size() + " ");
            for (Map.Entry<Course, Classroom> entry : student.currentCourses.entrySet())
            {
                bufferedWriter.write(entry.getValue().classroomNumber + " ");
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();

        }
        bufferedWriter.close();
    }
}
