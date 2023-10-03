package com.globits.da.domain;

import javax.persistence.*;

import lombok.*;
import org.springframework.stereotype.Component;

@Entity
@Data
//@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
//@Component
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "age")
    private Integer age;
    @Override
    public String toString(){
        return id+","+code+","+name+","+email+","+phone+","+age;
    }
}
