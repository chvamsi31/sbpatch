package com.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "my_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@DynamicInsert
@DynamicUpdate
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  int id;

  @Column(name = "full_name", nullable = false)
  String fullName;

  @Column(name = "age")
  int age;
}
