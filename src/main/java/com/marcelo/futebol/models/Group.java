package com.marcelo.futebol.models;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name="groups")
@AllArgsConstructor
@NoArgsConstructor
public class Group {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false, length = 100)
  private String fieldType;

  @Column(nullable = false, columnDefinition = "char(36)")
  private String hash;

  @Column(nullable = false)
  private double monthlyFee;

  @ManyToOne
  @JoinColumn(name = "player_id")
  private Player owner;

  @OneToMany(mappedBy = "group")
  private List<GroupPlayer> groupPlayers;
}
