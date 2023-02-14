package dao;

import model.Classroom;
import model.Student;
import service.Crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO implements Crud<Classroom> {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/baithi_module03?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    public ClassDAO() {
    }

    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        return connection;
    }

    public Classroom selectByName(String name) throws SQLException, ClassNotFoundException {
        Classroom classroom = null;
        Connection c = getConnection();
        PreparedStatement pr = c.prepareStatement("select * from class where name =?");
        pr.setString(1, name);
        ResultSet resultSet = pr.executeQuery();
        while (resultSet.next()) {
            name = resultSet.getString("name");
            int id = resultSet.getInt("id");
            classroom = new Classroom(id, name);
        }
        return classroom;
    }
    @Override
    public void create(Classroom classroom) {

    }

    @Override
    public List<Classroom> display() {
        List<Classroom> classrooms = new ArrayList<>();
        try {
            Connection c = getConnection();
            PreparedStatement pr = c.prepareStatement("select * from class");
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                classrooms.add(new Classroom(id, name));
            }
            c.close();
        }catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classrooms;
    }

    @Override
    public void update(Classroom classroom) {

    }

    @Override
    public void delete(int id) {

    }
}
