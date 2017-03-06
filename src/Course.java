import java.util.ArrayList;
import java.util.Comparator;


/**
 * Created by baris on 05.03.2017.
 */
public class Course {
    String courseId;
    int credit;
    int preqNum;
    ArrayList<Course> preqList;
    ArrayList<Classroom> sections;

    public Course(String courseId,int credit, int preqNum, ArrayList<Course> preqList) {
        this.courseId = courseId;
        this.credit = credit;
        this.preqNum = preqNum;
        this.preqList = preqList;
        this.sections = new ArrayList<>();
    }

    public  Course(String courseId){
        this.courseId = courseId;
        credit = -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return courseId.equals(course.courseId);
    }

    @Override
    public int hashCode() {
        return courseId.hashCode();
    }

}
