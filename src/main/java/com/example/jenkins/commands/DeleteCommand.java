package com.example.jenkins.commands;

import com.example.jenkins.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class DeleteCommand extends Command {

    public DeleteCommand(UserDao userDao) {
        super("delete",userDao);
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String[]> paramMap = req.getParameterMap();

        userDao.deleteByLogin(getParamValue(paramMap.get("login")));
    }

    private String getParamValue(String[] params) {
        return params[0];
    }
}
