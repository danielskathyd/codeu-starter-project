package com.google.codeu.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles fetching all messages for the public feed.
 */
@WebServlet("/feed")
public class MessageFeedServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.getOutputStream().println("this will be my message feed");
    }
}

/**public List<Message> getAllMessages(){
        List<Message> messages = new ArrayList<>();

        Query query = new Query("Message")
                .addSort("timestamp", SortDirection.DESCENDING);
        PreparedQuery results = datastore.prepare(query);

        for (Entity entity : results.asIterable()) {
            try {
                String idString = entity.getKey().getName();
                UUID id = UUID.fromString(idString);
                String user = (String) entity.getProperty("user");
                String text = (String) entity.getProperty("text");
                long timestamp = (long) entity.getProperty("timestamp");

                Message message = new Message(id, user, text, timestamp);
                messages.add(message);
            } catch (Exception e) {
                System.err.println("Error reading message.");
                System.err.println(entity.toString());
                e.printStackTrace();
            }
        }

        return messages;
    }
 */