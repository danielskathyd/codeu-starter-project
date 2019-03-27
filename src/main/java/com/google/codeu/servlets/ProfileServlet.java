package com.google.codeu.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.codeu.data.Datastore;
import com.google.codeu.data.User;

/**
 * Handles fetching and saving user data.
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

  private Datastore datastore;

  @Override
  public void init() {
    datastore = new Datastore();
  }

  /**
   * Responds with the "about me" section for a particular user.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    response.setContentType("text/html");

    String user = request.getParameter("user");

    if(user == null || user.equals("")) {
      // Request is invalid, return empty response
      return;
    }

    User userData = datastore.getUser(user);

    if(userData == null || userData.getInterests() == null) {
      return;
    }

    response.getOutputStream().println(userData.getName());
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
    if(u == null){
        String name = Jsoup.clean(request.getParameter("name"), Whitelist.simpleText());

        String city= Jsoup.clean(request.getParameter("city"), Whitelist.simpleText());
        String i = request.getParameter("interests");

        User user = new User(userEmail, name, city,i);
        datastore.storeUser(user);
    }
    else{
        String name = Jsoup.clean(request.getParameter("name"), Whitelist.simpleText());

        String city= Jsoup.clean(request.getParameter("city"), Whitelist.simpleText());
        String i = request.getParameter("interests");
        datastore.updateUser(userEmail, name, city, i);
    }

    response.sendRedirect("/profile.html?user=" + userEmail);
  }
}