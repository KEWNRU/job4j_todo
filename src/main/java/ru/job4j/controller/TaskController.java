package ru.job4j.controller;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Task;
import ru.job4j.service.TaskService;
@Controller
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;
    @GetMapping("/list")
    public String getTaskAll(Model model) {
        model.addAttribute("task", taskService.findAll());
        return "task/list";
    }
    @GetMapping("/{id}")
    public String getTask(Model model, @PathVariable int id) {
        model.addAttribute("task", taskService.findById(id));
        return "task/one";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute Task task) {
        taskService.update(task);
        return "redirect:/task/list";
    }
    @GetMapping("/create")
    public String getCreateTaskPage(Model model) {
        model.addAttribute("task", taskService.findAll());
        return "task/create";
    }
    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task) {
        taskService.add(task);
        return "redirect:/task/list";
    }
    @GetMapping("/secondList")
    public String getDoneTaskTrue(Model model) {
        model.addAttribute("task", taskService.findDone(true));
        return "task/secondList";
    }
    @GetMapping("/newTaskList")
    public String getDoneTaskFalse(Model model) {
        model.addAttribute("task", taskService.findDone(false));
        return "task/newTaskList";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        taskService.delete(id);
        return "redirect:/task/list";
    }
}