package com.example.jenkins.commands;

import com.example.jenkins.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class GetAllCommand extends Command{

    public GetAllCommand(UserDao userDao) {
        super("get", userDao);
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String[]> paramMap = req.getParameterMap();

        userDao.getAll();
    }
}
