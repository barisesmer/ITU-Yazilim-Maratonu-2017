import java.util.Comparator;

/**
 * Created by baris on 05.03.2017.
 */
class CompareStudentsByMinCredit implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        if (o1.minCredit > o2.minCredit){
            return -1;
        }else if (o1.minCredit< o2.minCredit){
            return 1;
        }else{
            return 0;
        }
    }
}