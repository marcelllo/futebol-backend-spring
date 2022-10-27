package com.marcelo.futebol.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "players")
@AllArgsConstructor
@NoArgsConstructor
public class Player {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false, length = 14, unique = true)
  private String phone;

  @Column(nullable = false, length = 200, unique = true)
  private String email;

  @Column(nullable = false)
  private Timestamp createdAt;

  @Column(nullable = false)
  private Timestamp updatedAt;

  @OneToMany(mappedBy = "owner")
  private List<Group> groups;
}
