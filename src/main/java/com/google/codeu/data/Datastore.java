/*
 * Copyright 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.codeu.data;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.FetchOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** Provides access to the data stored in Datastore. */
public class Datastore {
  private DatastoreService datastore;

  public Datastore() {
    datastore = DatastoreServiceFactory.getDatastoreService();
  }

  public void storeMessage(Message message) {
    Entity messageEntity = new Entity("Message", message.getId().toString());
    messageEntity.setProperty("user", message.getUser());
    messageEntity.setProperty("text", message.getText());
    messageEntity.setProperty("timestamp", message.getTimestamp());
    messageEntity.setProperty("recipient", message.getRecipient());
    messageEntity.setProperty("sentimentscore", message.getSentimentscore());
    datastore.put(messageEntity);
  }

  /**
   * Returns the total number of messages for all users.
   */
  public int getTotalMessageCount() {
    Query query = new Query("Message");
    PreparedQuery results = datastore.prepare(query);
    return results.countEntities(FetchOptions.Builder.withLimit(1000));
  }

  /**
   * Gets messages posted by a specific user.
   *
   * @return a list of messages posted by the user, or empty list if user has never posted.
   */
  public List<Message> getMessages(String recipient) {
    List<Message> messages = new ArrayList<>();
    Query query =
            new Query("Message")
                    .setFilter(new Query.FilterPredicate("recipient", FilterOperator.EQUAL, recipient))
                    .addSort("timestamp", SortDirection.DESCENDING);
    PreparedQuery results = datastore.prepare(query);

    for (Entity entity : results.asIterable()) {
      try {
        String idString = entity.getKey().getName();
        UUID id = UUID.fromString(idString);
        String user = (String) entity.getProperty("user");

        String text = (String) entity.getProperty("text");
        long timestamp = (long) entity.getProperty("timestamp");
        float sentimentScore = entity.getProperty("sentimentscore") ==
                null? (float) 0.0 : ((Double) entity.getProperty("sentimentscore")).floatValue();
        Message message = new Message(id, user, text, timestamp, recipient, sentimentScore);
        messages.add(message);
      } catch (Exception e) {
        System.err.println("Error reading message.");
        System.err.println(entity.toString());
        e.printStackTrace();
      }
    }
    return messages;
  }
  public List<Message> getAllMessages(){
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
        String recipient = (String) entity.getProperty("recipient");
        float sentimentScore = entity.getProperty("sentimentscore") ==
                null? (float) 0.0 : ((Double) entity.getProperty("sentimentscore")).floatValue();

        Message message = new Message(id, user, text, timestamp, recipient, sentimentScore);
        messages.add(message);
      } catch (Exception e) {
        System.err.println("Error reading message.");
        System.err.println(entity.toString());
        e.printStackTrace();
      }
    }
    return messages;
  }

  /** Stores the User in Datastore. */

  public void storeUser(User user) {
    Entity userEntity = new Entity("User", user.getEmail());
    userEntity.setProperty("email", user.getEmail());
    userEntity.setProperty("aboutMe", user.getAboutMe());
    userEntity.setProperty("name", user.getName());
    userEntity.setProperty("city", user.getCity());
    userEntity.setProperty("interests", user.getInterests());
    datastore.put(userEntity);
  }

  public void updateUser(String email, String name, String city, String interests){
    User u = getUser(email);
    u.setName(name);
    u.setCity(city);
    u.setInterests(interests);
  }

  /**
   * Returns the User owned by the email address, or
   * null if no matching User was found.
   */
  public User getUser(String email) {
    Query query = new Query("User")
            .setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, email));
    PreparedQuery results = datastore.prepare(query);
    Entity userEntity = results.asSingleEntity();
    if (userEntity == null) {
      return null;
    }
    //String aboutMe = (String) userEntity.getProperty("aboutMe");
    String name = "";
    String city = "";
    String interests = "";
    if(userEntity.getProperty("name") != null){
       name = (String) userEntity.getProperty("name");
    }
    if(userEntity.getProperty("city") != null){
      city = (String) userEntity.getProperty("city");
    }
    if(userEntity.getProperty("interests") != null){
      interests =  (String) userEntity.getProperty("interests");
    }

    User user = new User(email,name, city, interests);
    return user;
  }

  public List<User> getAllUsers() {
    List<User> users = new ArrayList<>();
    Query query = new Query("User");
    PreparedQuery results = datastore.prepare(query);

    for (Entity entity : results.asIterable()) {
      try {
        String name = (String) entity.getProperty("name");
        String email = (String) entity.getProperty("email");
        String city = (String) entity.getProperty("city");
        String interests = (String) entity.getProperty("interests");

        User user = new User(email, name, city, interests);
        users.add(user);
      } catch (Exception e) {
        System.err.println("Error finding user.");
        System.err.println(entity.toString());
        e.printStackTrace();
      }
    }
    return users;
  }


}
