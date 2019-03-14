package com.google.codeu.servlets;

import com.google.codeu.data.Datastore;
import com.google.codeu.data.Message;
import com.google.gson.Gson;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles fetching chart data.
 */
@WebServlet("/messagechart")
public class ChartServlet extends HttpServlet {

  private Datastore datastore;

  @Override
  public void init() {
    datastore = new Datastore();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");

    List<Message> msgList = datastore.getMessages(null);
    Gson gson = new Gson();
    String json = gson.toJson(msgList);
    response.getWriter().println(json);
  }
}
