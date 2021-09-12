package com.example.jenkins.commands;

import com.example.jenkins.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {
    protected String name;
    protected UserDao userDao;

    public Command(String name, UserDao userDao) {
        this.name = name;
        this.userDao = userDao;
    }

    public String getName() {
        return name;
    }

    public abstract void execute(HttpServletRequest req, HttpServletResponse resp);
}
