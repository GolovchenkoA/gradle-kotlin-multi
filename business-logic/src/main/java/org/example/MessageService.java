package org.example;

import org.example.MessageModel;

public class MessageService {
    public static void print(MessageModel msg) {
        System.out.println(msg.getText());
    }
}