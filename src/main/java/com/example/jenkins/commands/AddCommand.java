package com.example.jenkins.commands;

import com.example.jenkins.dao.UserDao;
import com.example.jenkins.entity.User;

import java.util.Map;

public class AddCommand extends Command{


    public AddCommand(UserDao userDao) {
        super("add", userDao);
    }

    @Override
    public int execute(Map<String, String[]> map) {
        return userDao.add(getUser(map));
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
