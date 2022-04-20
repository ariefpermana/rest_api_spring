package com.ariefpermana.user_management.entities;


import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tb_master_user_role")
public class UserRole implements Serializable {

    @Id
    private Integer role_Id;
    private String role_name;
}