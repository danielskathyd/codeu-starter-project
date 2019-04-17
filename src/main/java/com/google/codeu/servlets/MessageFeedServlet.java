package com.google.codeu.servlets;
<<<<<<< HEAD
import java.io.IOException;
=======

import java.io.IOException;
import java.util.List;

>>>>>>> 45541d9c1cddb6b91408f9447bf075d67f7d0f00
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
/**
 * Handles fetching all messages for the public feed.
 */


 @WebServlet("/feed")
public class MessageFeedServlet extends HttpServlet{

=======
import com.google.codeu.data.Datastore;
import com.google.codeu.data.Message;
import com.google.gson.Gson;

/**
 * Handles fetching all messages for the public feed.
 */
@WebServlet("/feed")
public class MessageFeedServlet extends HttpServlet{

    private Datastore datastore;

    @Override
    public void init() {
        datastore = new Datastore();
    }

    /**
     * Responds with a JSON representation of Message data for all users.
     */
>>>>>>> 45541d9c1cddb6b91408f9447bf075d67f7d0f00
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

<<<<<<< HEAD
        response.getOutputStream().println("this will be my message feed");
    }
}
=======
        response.setContentType("application/json");

        List<Message> messages = datastore.getAllMessages();
        Gson gson = new Gson();
        String json = gson.toJson(messages);

        response.getOutputStream().println(json);
    }
}
>>>>>>> 45541d9c1cddb6b91408f9447bf075d67f7d0f00
