package com.example.jenkins.commands;

import com.example.jenkins.dao.UserDao;

import java.util.Map;

public class DeleteCommand extends Command {

    public DeleteCommand(UserDao userDao) {
        super("delete",userDao);
    }

    @Override
    public int execute(Map<String, String[]> map) {
        return userDao.deleteByLogin(getParamValue(map.get("login")));
    }

    private String getParamValue(String[] params) {
        return params[0];
    }
}
