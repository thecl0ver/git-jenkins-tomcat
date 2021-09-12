package com.example.jenkins.commands;

import com.example.jenkins.dao.UserDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCommandTest {
    DeleteCommand deleteCommand;
    Map<String, String[]> paramMap;

    @BeforeEach
    void setUp() {
        deleteCommand = new DeleteCommand(new UserDao());
        paramMap = new HashMap<>();
        paramMap.put("login", new String[]{"fever_d"});
    }

    @Test
    void execute() {
        assertEquals(0, deleteCommand.execute(paramMap));
    }
}