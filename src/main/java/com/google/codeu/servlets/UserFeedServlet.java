package com.google.codeu.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.codeu.data.Datastore;
import com.google.codeu.data.Message;
import com.google.codeu.data.User;
import com.google.gson.Gson;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * Handles fetching all users for the public feed.
 */
@WebServlet("/users")
public class UserFeedServlet extends HttpServlet {

  private Datastore datastore;

  @Override
  public void init() {
    datastore = new Datastore();
  }

    /**
     * Responds with a JSON representation of data for all users.
     */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException {

    response.setContentType("application/json");

    List<User> users = datastore.getAllUsers();
    Gson gson = new Gson();
    String json = gson.toJson(users);

    response.getOutputStream().println(json);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    UserService userService = UserServiceFactory.getUserService();
    if (!userService.isUserLoggedIn()) {
      response.sendRedirect("/index.html");
      return;
    }

    String userEmail = userService.getCurrentUser().getEmail();
    User u = datastore.getUser(userEmail);


    String search = Jsoup.clean(request.getParameter("Search"), Whitelist.simpleText());

    //TODO: save search

    System.out.println("SEARCH: " + search);

    response.sendRedirect("/index.html?user=" + userEmail);
  }
}
