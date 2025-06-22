package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Category;
import ru.job4j.model.Task;
import ru.job4j.model.User;
import ru.job4j.service.category.CategoryService;
import ru.job4j.service.priority.PriorityService;
import ru.job4j.service.task.TaskService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final PriorityService priorityService;
    private final CategoryService categoryService;

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
        var taskOptional = optionalTask.get();
        model.addAttribute("task", taskOptional);
        model.addAttribute("priority", priorityService.findById(id));
        model.addAttribute("category", taskOptional.getCategories());
        return "task/one";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(Model model, @PathVariable int id) {
        var taskOptional = taskService.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Задача не найдена");
            return "error/404";
        }

        model.addAttribute("task", taskOptional.get());
        model.addAttribute("categories", categoryService.findAll());
        return "task/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task,
                         @RequestParam("categoryIds") List<Integer> categoryIds,
                         Model model) {
        List<Category> selectedCategories = categoryService.findByListId(categoryIds);
        task.setCategories(selectedCategories);
        var isUpdated = taskService.update(task);
        if (!isUpdated) {
            model.addAttribute("message", "Не удалось обновить задачу");
            return "error/404";
        }
        return "redirect:/task/list";
    }

    @GetMapping("/create")
    public String getCreateTaskPage(Model model) {
        model.addAttribute("task", taskService.findAll());
        model.addAttribute("priority", priorityService.findAll());
        model.addAttribute("category", categoryService.findAll());
        return "task/create";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task,
                             @RequestParam("categoryIds") List<Integer> categoryIds,
                             @SessionAttribute User user) {

        task.setUser(user);
        List<Category> selectedCategories = categoryService.findByListId(categoryIds);
        task.setCategories(selectedCategories);

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