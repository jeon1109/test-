package com.example.jeon.dto;

import com.example.jeon.common.Gender;

import java.time.LocalDate;

public class apiDtoRequest {
    long id;
    String loginId;
    String password;
    String name;
    LocalDate birthDate;
    Gender gender;
    String email;
}
