package com.ariefpermana.user_management.entities;


import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tb_master_user_role")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long user_id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String privelege;
    private Timestamp created_on;
    private Timestamp last_login;
}