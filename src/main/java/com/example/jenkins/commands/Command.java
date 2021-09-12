package com.example.jenkins.commands;

import com.example.jenkins.dao.UserDao;

import java.util.Map;

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

    public abstract int execute(Map<String, String[]> map);
}
