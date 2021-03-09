package com.mtv.erp.model;

import javax.swing.text.Position;
import java.time.LocalDate;

public class User {
    private int id;
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private String number;
    private LocalDate birthDate;
    private Departament departament;
    private Position position;
    private UserType userType;
}
