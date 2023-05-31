package com.user.controller;

import com.user.dto.PatchDto;
import com.user.exception.NotFoundException;
import com.user.exception.NotYetImplementedException;
import com.user.model.User;
import com.user.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  UserService service;

  @GetMapping("/all")
  public ResponseEntity<List<User>> findAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> findById(@PathVariable(name = "id") Integer id)
      throws NotFoundException {
    return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Boolean> updatePartially(@PathVariable(name = "id") int id,
      @RequestBody PatchDto dto) throws NotYetImplementedException, NotFoundException {
    if (dto.getOp().equalsIgnoreCase("update")) {
      boolean result = service.partialUpdate(id, dto.getKey(), dto.getValue());
      return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    } else {
      throw new NotYetImplementedException("NOT_YET_IMPLEMENTED");
    }
  }
}
