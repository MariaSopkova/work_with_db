package pojo;

public class Group {
    int id;
    int course;
    House house;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Group(int id, int course, House house) {
        this.id = id;
        this.course = course;
        this.house = house;
    }

    public Group(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", course=" + course +
                ", house=" + house.toString() +
                '}';
    }
}
