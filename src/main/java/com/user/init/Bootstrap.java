package com.user.init;

import com.user.model.User;
import com.user.service.UserService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

  @Autowired
  UserService service;

  @Override
  public void run(String... args) throws Exception {
    loadUsers();
  }

  private void loadUsers() {
    long total = service.getCount();
    if (total == 0) {
      List<User> users = createUsers();
      service.saveAll(users);
      log.info("Data inserted");
    } else {
      log.info("Data already present");
    }
  }

  private List<User> createUsers() {
    List<User> users = new ArrayList<>();
    users.add(User.builder().fullName("John").age(25).build());
    users.add(User.builder().fullName("Jane").age(30).build());
    users.add(User.builder().fullName("Adam").age(32).build());
    return users;
  }
}
