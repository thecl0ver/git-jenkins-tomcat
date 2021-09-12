package com.example.jenkins.commands;

import com.example.jenkins.dao.UserDao;
import com.example.jenkins.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UpdateCommand extends Command{

    public UpdateCommand(UserDao userDao) {
        super("update",userDao);
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String[]> paramMap = req.getParameterMap();

        userDao.update(getUser(paramMap));
    }

    private User getUser(Map<String,String[]> paramMap) {
        User user = new User(
                getParamValue(paramMap.get("login")),
                getParamValue(paramMap.get("name")),
                getParamValue(paramMap.get("surname")),
                Integer.parseInt(getParamValue(paramMap.get("age"))),
                getParamValue(paramMap.get("email"))
        );

        return user;
    }

    private String getParamValue(String[] params) {
        return params[0];
    }
}
