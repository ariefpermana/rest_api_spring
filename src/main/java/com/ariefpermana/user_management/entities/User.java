package com.ariefpermana.user_management.entities;


import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tb_master_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long user_id;
    private String name;
    private String jenis_kelamin;
    private String alamat;
    private String email;
    private Integer role_id;
    private Timestamp created_on;
    private Timestamp lastupdated;
}