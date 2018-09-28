package dao;

import pojo.Student;

public interface StudentDao {
    public Student getStudentById(int id);
    public Student getHouseByNameAndSurname(String name);
    public boolean addStudent(Student house);
    public boolean updateStudent(Student house);
    public boolean deleteStudentById(int houseId);
}
