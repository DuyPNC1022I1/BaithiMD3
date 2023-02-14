package dao;

import model.Classroom;
import model.Student;
import service.Crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements Crud<Student> {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/baithi_module03?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";
    ClassDAO classDAO;

    public StudentDAO() {
        classDAO= new ClassDAO();
    }

    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        return connection;
    }

    @Override
    public void create(Student student) {
        try {
            Connection c = getConnection();
            PreparedStatement pr = c.prepareStatement("insert into student(name, birthday, address, phonenumber, email, class) values (?, ?, ?, ?, ?, ?)");
            pr.setString(1, student.getName());
            pr.setInt(2, student.getBirthday());
            pr.setString(3, student.getAddress());
            pr.setInt(4, student.getPhoneNumber());
            pr.setString(5, student.getEmail());
            pr.setString(6, student.getClassroom().getName());

            pr.executeUpdate();
            c.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> display() {
        List<Student> students = new ArrayList<>();
        try {
            Connection c = getConnection();
            PreparedStatement pr = c.prepareStatement("select * from student");
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int birthday = resultSet.getInt("birthday");
                String address = resultSet.getString("address");
                int phoneNumber = resultSet.getInt("phonenumber");
                String email = resultSet.getString("email");
                Classroom classroom = classDAO.selectByName(resultSet.getString("class"));

                students.add(new Student(id, name, birthday, address, phoneNumber, email, classroom));

            }
            c.close();
        }catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(int id) {
        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("delete from student where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            c.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
