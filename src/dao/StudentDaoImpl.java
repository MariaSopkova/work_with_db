package dao;

import connectionManager.ConnectionManager;
import connectionManager.ConnectionManagerJDNCImpl;
import pojo.Group;
import pojo.House;
import pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDaoImpl implements StudentDao {
    private static ConnectionManager connectionManager = ConnectionManagerJDNCImpl.getInstanse();
    private static GroupDao groupDao = new GroupDaoImpl();
    @Override
    public Student getStudentById(int id) {

        try {
            Connection connection = connectionManager.getConnection();
            try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM students WHERE students.id = ?"
            )){
                statement.setInt(1, id);
                return getStudent(statement.executeQuery());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Student getStudent(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {

            Group group = groupDao.getGroupById(resultSet.getInt("group"));
            Student student = new Student(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname"), group);
            return student;
        }
        return null;
    }
    @Override
    public Student getHouseByNameAndSurname(String name) {
        return null;
    }

    @Override
    public boolean addStudent(Student house) {
        return false;
    }

    @Override
    public boolean updateStudent(Student house) {
        return false;
    }

    @Override
    public boolean deleteStudentById(int houseId) {
        return false;
    }
}
