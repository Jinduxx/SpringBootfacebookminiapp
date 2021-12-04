package com.example.facebookminiapp.otherModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDetails {
    private String email;
    private String password;
}