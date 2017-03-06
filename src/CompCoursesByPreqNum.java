import java.util.Comparator;

/**
 * Created by baris on 05.03.2017.
 */

public class CompCoursesByPreqNum implements Comparator<Course> {

    @Override
    public int compare(Course o1, Course o2) {
        if (o1.preqNum > o2.preqNum){
            return -1;
        }else if (o1.preqNum < o2.preqNum){
            return 1;
        }else{
            return 0;
        }
    }
}