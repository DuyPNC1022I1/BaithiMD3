package servlet;

import dao.ClassDAO;
import dao.StudentDAO;
import model.Student;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/server")
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO;
    private ClassDAO classDAO;

    @Override
    public void init() {
        studentDAO = new StudentDAO();
        classDAO = new ClassDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "showCreate":
                showCreateForm(request, response);
                break;
            case "showUpdate":
                showUpdate(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            default:
                display(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    create(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                try {
                    update(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "searchByName":
                searchByName(request, response);
                break;
            default:
                display(request, response);
                break;
        }
    }

    protected void display(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentDAO.display();
        request.setAttribute("students", students);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/display.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("classrooms", classDAO.display());
        RequestDispatcher rd = request.getRequestDispatcher("/create.jsp");
        rd.forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException {
        int phoneNumber = 0;
        int birthday = 0;
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String classroom = request.getParameter("classroom");
        try {
            phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));
            birthday = Integer.parseInt(request.getParameter("birthday"));
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }
        studentDAO.create(new Student(name, birthday, address, phoneNumber, email, classDAO.selectByName(classroom)));
        response.sendRedirect("/server");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.delete(id);
        response.sendRedirect("/server");
    }

    private void showUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentDAO.selectById(id);
        request.setAttribute("student", student);
        request.setAttribute("classrooms", classDAO.display());
        RequestDispatcher rd = request.getRequestDispatcher("/update.jsp");
        rd.forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        int phoneNumber = 0;
        int birthday = 0;
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String classroom = request.getParameter("classroom");
        try {
            phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));
            birthday = Integer.parseInt(request.getParameter("birthday"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        studentDAO.update(new Student(id, name, birthday, address, phoneNumber, email, classDAO.selectByName(classroom)));
        response.sendRedirect("/server");
    }

    private void searchByName(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("searchByName");
        boolean flag = false;
        List<Student> studentByName = new ArrayList<>();
        for (int i = 0; i < studentDAO.display().size(); i++) {
            if (studentDAO.display().get(i).getName().toUpperCase().contains(name.toUpperCase())) {
                studentByName.add(studentDAO.display().get(i));
                flag = true;
            }
        }
        request.setAttribute("flag", flag);
        request.setAttribute("classrooms", classDAO.display());
        request.setAttribute("studentByName", studentByName);
        RequestDispatcher rd = request.getRequestDispatcher("/resultSearch.jsp");
        rd.forward(request, response);
    }


}

