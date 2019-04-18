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

import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;

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

        response.setContentType("application/json");
        String user = request.getParameter("user");
        
        if (user == null || user.equals("")) {
          response.getWriter().println("[]");
          return;
        }
    

    User userData = datastore.getUser(user);
    

    if(userData == null || userData.getInterests() == null) {
        return;
    }
    
    List<String> s = new ArrayList<String>();
    userData.getCity();
    s.add(userData.getName());
    s.add(userData.getCity());
    s.add(userData.getInterestsString());
    s.add(userData.getLat());
    s.add(userData.getLon());

    Gson gson = new Gson();
    String json = gson.toJson(s);
    System.out.println(json);
    response.getWriter().println(json);;
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
    
    String name = Jsoup.clean(request.getParameter("name"), Whitelist.simpleText());

    String city= Jsoup.clean(request.getParameter("city"), Whitelist.simpleText());
    String i = request.getParameter("interests");
    if( i == null){
      i = "";
    }
    Double lon= request.getParameter("lon");
    Double lat= request.getParameter("lat");

    User user = new User(userEmail, name, city,i, lon, lat);

    datastore.storeUser(user);
   

    response.sendRedirect("/profile.html?user=" + userEmail);
  }
}