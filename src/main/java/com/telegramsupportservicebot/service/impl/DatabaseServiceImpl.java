package com.telegramsupportservicebot.service.impl;

import com.telegramsupportservicebot.service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PGobject;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
@Slf4j
public class DatabaseServiceImpl implements DatabaseService {

    @Override
    public void saveMessage(Long userId, String userFirstName, String userMessage) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/telegrambotdb",
                    "postgres", System.getenv("DB_PASSWORD"));
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO messages (message) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            PGobject jsonObject = new PGobject();
            jsonObject.setType("json");
            jsonObject.setValue("{\n\t\"UserID\": " + "\"" + userId.toString() + "\"," +
                    "\n\t\"User First Name\": " + "\"" + userFirstName + "\"," +
                    "\n\t\"Message\": " + "\"" + userMessage + "\"\n}");
            preparedStatement.setObject(1, jsonObject);
            preparedStatement.executeUpdate();
            log.info("Message saved into the database");
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            log.error("Exception: ", exception);
        }
    }
}