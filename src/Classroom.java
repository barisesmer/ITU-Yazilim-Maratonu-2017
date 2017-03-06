/**
 * Created by baris on 05.03.2017.
 */
public class Classroom{
    int classroomNumber;
    Course course;
    int day;
    int beginHour;
    int endHour;
    int capacity;

    public Classroom(Course course, int classroomNumber, int day, int beginHour, int endHour, int capacity) {
        this.classroomNumber = classroomNumber;
        this.day = day;
        this.beginHour = beginHour;
        this.endHour = endHour;
        this.capacity = capacity;
        this.course = course;
    }

    public boolean clashes(Classroom anotherSection){
        if(day != anotherSection.day){
            return  false;
        }

        if(anotherSection.beginHour >= this.endHour || this.beginHour >= anotherSection.endHour){
            return false;
        }
        return true;
    }



}
