import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;



/**
 * Created by baris on 05.03.2017.
 */
public class Student {
    int studentId, minCredit, maxCredit,curCredit, numWantedCourses, numCompletedCourses, totalPoint ;
    ArrayList<Course> completedCourses;
    HashMap<Course, Classroom> currentCourses;
    HashMap<Course, Integer> wantedCourses;
    //TODO : constructor ??
    ArrayList<Course> availableCourses;

    public Student(int studentId, int minCredit, int maxCredit, int numWantedCourses, int numCompletedCourses, ArrayList<Course> completedCourses,  HashMap<Course, Integer> wantedCourses) {
        this.studentId = studentId;
        this.minCredit = minCredit;
        if (maxCredit == -1){
            this.maxCredit = Integer.MAX_VALUE;
        }else{
            this.maxCredit = maxCredit;
        }
        this.curCredit = 0;
        this.numWantedCourses = numWantedCourses;
        this.numCompletedCourses = numCompletedCourses;
        this.totalPoint = 0;
        this.completedCourses = completedCourses;
        this.currentCourses = new HashMap<>();
        this.wantedCourses = wantedCourses;
    }

    public boolean addCourse(Course course){
        for (int i = 0; i < course.preqList.size(); i++) {
            Course temp = course.preqList.get(i);
            if(!completedCourses.contains(temp)){
                return  false;
            }
        }

        //try all sections of class
        for (int i = 0; i < course.sections.size(); i++) {
            Classroom section = course.sections.get(i);
            boolean clashes = false;
            for (Map.Entry<Course, Classroom> entry : currentCourses.entrySet()){
                clashes = clashes || section.clashes(entry.getValue());
            }

            if(!clashes && section.capacity > 0 && curCredit + course.credit <= maxCredit){
                section.capacity--;
                currentCourses.put(course, section);
                curCredit += course.credit;
                if (wantedCourses.containsKey(course)){
                    totalPoint += wantedCourses.get(course);
                }else{
                    totalPoint += 5;
                }
                return true;
            }
        }

        return false;


    }

}
