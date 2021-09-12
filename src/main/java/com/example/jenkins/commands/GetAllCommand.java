package com.example.jenkins.commands;

import com.example.jenkins.dao.UserDao;

import java.util.Map;

public class GetAllCommand extends Command{

    public GetAllCommand(UserDao userDao) {
        super("get", userDao);
    }

    @Override
    public int execute(Map<String, String[]> map) {
        userDao.getAll();
        return 0;
    }
}
