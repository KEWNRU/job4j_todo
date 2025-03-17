package ru.job4j.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private int taskId;
    private String title;
    private String description;
    private LocalDate created;
    private boolean done;
    private String userName;
    private String priorityName;
}
