package ru.job4j.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "todo_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @EqualsAndHashCode.Include
    private String login;
    private String password;
}
