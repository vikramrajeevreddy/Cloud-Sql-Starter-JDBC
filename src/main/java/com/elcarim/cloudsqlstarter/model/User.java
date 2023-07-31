package com.elcarim.cloudsqlstarter.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;

}
