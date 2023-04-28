package com.telegramsupportservicebot.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "telegram_id", nullable = false)
    private String telegramID;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "message", nullable = false)
    private String message;
}