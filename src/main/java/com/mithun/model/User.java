package com.mithun.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mithun.dto.RestaurantDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;
    private String email;
    private String password;
    private USER_ROLE role;

    @JsonIgnore //not get this when fetching
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer") //use order table for this mapping
    private List<Order> orders = new ArrayList<>();

    @ElementCollection
    private List<RestaurantDto> favorites = new ArrayList();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //when user is removed all addresses should be gone
    private List<Address> addresses = new ArrayList();

}
