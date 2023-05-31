package com.user.service;

import com.user.dao.UserDao;
import com.user.exception.NotFoundException;
import com.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserDao dao;

    public long getCount() {
        return dao.count();
    }

    public void saveAll(List<User> users) {
        dao.saveAll(users);
    }

    public List<User> getAll() {
        return dao.findAll();
    }

    public User getById(int id) throws NotFoundException {
        log.info("Search id={}", id);
        return dao.findById(id).orElseThrow(() -> new NotFoundException("RESOURCE_NOT_FOUND"));
    }

    public boolean partialUpdate(int id, String key, String value)
            throws NotFoundException {
        log.info("Search id={}", id);
        Optional<User> optional = dao.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();

            if (key.equalsIgnoreCase("fullName")) {
                log.info("Updating full name");
                user.setFullName(value);
            }
            if (key.equalsIgnoreCase("age")) {
                log.info("Updating age");
                user.setAge(Integer.parseInt(value));
            }

            dao.save(user);
            return true;
        } else {
            throw new NotFoundException("RESOURCE_NOT_FOUND");
        }
    }
}
