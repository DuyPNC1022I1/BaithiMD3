package servlet;

import dao.ClassDAO;
import dao.StudentDAO;
import model.Student;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
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
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int birthday = Integer.parseInt(request.getParameter("birthday"));
        String address = request.getParameter("address");
        int phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));
        String classroom = request.getParameter("classroom");

        studentDAO.create(new Student(name, birthday, address, phoneNumber, email, classDAO.selectByName(classroom)));
        response.sendRedirect("/server");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.delete(id);
        response.sendRedirect("/server");
    }


}

