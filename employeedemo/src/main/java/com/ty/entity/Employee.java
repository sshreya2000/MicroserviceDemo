package com.ty.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Indexed;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
}
