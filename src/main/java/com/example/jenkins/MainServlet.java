package com.example.jenkins;
import com.example.jenkins.commands.*;
import com.example.jenkins.dao.UserDao;
import com.example.jenkins.service.ApplicationPropertiesReader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/jenkins/*"})
public class MainServlet extends HttpServlet {

    private UserDao userDao;
    private Set<Command> commands;



    // TODO rewrite this
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            Class.forName(ApplicationPropertiesReader.getProperty("driver.name"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connection =
                    DriverManager.getConnection(ApplicationPropertiesReader.getProperty("db.url"),
                            ApplicationPropertiesReader.getProperty("db.user"),
                            ApplicationPropertiesReader.getProperty("db.password"));
            this.userDao = new UserDao(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        commands = new HashSet<>();
        commands.add(new AddCommand(userDao));
        commands.add(new DeleteCommand(userDao));
        commands.add(new UpdateCommand(userDao));
        commands.add(new GetAllCommand(userDao));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO return all users
        String params = req.getParameterMap()
                .entrySet()
                .stream()
                .map(entry -> {
                    String param = String.join(" and ", entry.getValue());
                    return entry.getKey() + " = " + param;
                })
                .collect(Collectors.joining("\n"));

        //Date date = new Date();
        //req.setAttribute("date", date.toString());
        //req.getRequestDispatcher("user.jsp").forward(req, resp);

        resp.getWriter().write("Method doPost\n" + "\nParms:\n" + params);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req, resp);

    }

    // TODO rewrite this
    @Override
    public void destroy() {
        userDao.close();
    }

    private void action(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameterMap().get("action")[0];

        commands.forEach(command -> {if (action.equals(command.getName())) command.execute(req, resp);});
    }
}
