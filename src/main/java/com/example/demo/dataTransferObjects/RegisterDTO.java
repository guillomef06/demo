package com.example.demo.dataTransferObjects;

import java.time.LocalDate;

public record RegisterDTO(
         String email,

         String nickName,

         String bio,

         String password,

         LocalDate dob) {
}
