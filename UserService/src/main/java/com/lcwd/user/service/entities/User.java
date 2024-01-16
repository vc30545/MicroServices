package com.lcwd.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {

    @Id
    @Column(name = "ID")
    private  String userId;
    @Column(name = "name",length = 30)
    private  String name;
    @Column(name = "email")
    private  String email;
    @Column(name = "about")
    private  String about;
    @Column(name = "mobile_num")
    private  String mobileNum;

    @Column(name = "address")
    private  String address;

    @Column(name = "is_active", nullable = false)
    private  int isActive;

    @Transient
    private List<Rating> ratings = new ArrayList<>();

}
