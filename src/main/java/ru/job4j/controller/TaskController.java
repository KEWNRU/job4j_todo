package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dto.TaskDto;
import ru.job4j.model.Priority;
import ru.job4j.model.Task;
import ru.job4j.model.User;
import ru.job4j.service.priority.PriorityService;
import ru.job4j.service.task.TaskService;

@Controller
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final PriorityService priorityService;

    @GetMapping("/list")
    public String getTaskAll(Model model) {
        model.addAttribute("task", taskService.findAll());
        model.addAttribute("priority", priorityService.findAll());
        return "task/list";
    }

    @GetMapping("/{id}")
    public String getTask(Model model, @PathVariable int id) {
        var optionalTask = taskService.findById(id);
        if (optionalTask.isEmpty()) {
            model.addAttribute("message", "Задание с указанным идентификатором не найдена");
            return "error/404";
        }
        model.addAttribute("task", optionalTask.get());
        model.addAttribute("priority", priorityService.findById(id));
        return "task/one";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute Task task, Model model) {
        var tasks = taskService.update(task);
        if (!tasks) {
            model.addAttribute("message", "Что-то пошло не так");
            return "redirect:/error/404";
        }

        return "redirect:/task/list";
    }

    @GetMapping("/create")
    public String getCreateTaskPage(Model model) {
        model.addAttribute("task", taskService.findAll());
        model.addAttribute("priority", priorityService.findAll());
        return "task/create";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task, @SessionAttribute User user) {
        task.setUser(user);
        taskService.add(task);
        return "redirect:/task/list";
    }

    @GetMapping("/buttonCompleteTask/{id}")
    public String buttonCompleteTask(Model model, @ModelAttribute Task task) {
        if (!taskService.updateTask(task.getId(), task.isDone())) {
            model.addAttribute("message", "Что-то пошло не так");
            return "error/404";
        } else {
            task.setDone(true);
            taskService.updateTask(task.getId(), task.isDone());
        }
        return "redirect:/task/list";
    }

    @GetMapping("/newTaskList")
    public String newGetDoneTask(Model model) {
        model.addAttribute("task", taskService.findDone(false));
        return "/task/newTaskList";
    }

    @GetMapping("/secondList")
    public String oldGetDoneTask(Model model) {
        model.addAttribute("task", taskService.findDone(true));
        return "/task/secondList";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        var isDeleted = taskService.delete(id);
        if (!isDeleted) {
            model.addAttribute("message", "Задание с указанным идентификатором не найдена");
            return "redirect:/error/404";
        }
        return "redirect:/task/list";
    }
}