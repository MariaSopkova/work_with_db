package test;

import dao.StudentDao;
import dao.StudentDaoImpl;
import pojo.Student;

public class StudentTest {
    public static void main(String[] args) {
        getById(1);
    }

    public static void getById(int id){
        StudentDao studentDao = new StudentDaoImpl();
        Student student =  studentDao.getStudentById(id);
        System.out.println(student);
    }
}
