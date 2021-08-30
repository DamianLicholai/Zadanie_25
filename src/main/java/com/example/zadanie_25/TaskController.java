package com.example.zadanie_25;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String home(Model model, @RequestParam(required = false) Status status) {
        List<Task> taskList;
        if (status == null) {
            taskList = taskRepository.findAll();
        } else {
            taskList = taskRepository.findByStatus(status);
        }
        model.addAttribute("taskList", taskList);
        return "home";
    }

    @GetMapping("film/{id}")
    public String showTask(@PathVariable Long id, Model model) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            model.addAttribute("task", task);
            return "task";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("zadanie/{id}/edit")
    public String showTaskEditForm(@PathVariable Long id, Model model) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            model.addAttribute("taskToEdit", task);

            return "taskEdit";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("zadanie/{id}/done")
    public String markTaskDone(@PathVariable Long id, Model model) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setStatus(Status.DONE);
            model.addAttribute("task", task);

            return "task";
        } else {
            return "redirect:/";
        }

    }


    @PostMapping("/zadanie/edit")
    public String editTask(Task task) {
        taskRepository.save(task);

        return "redirect:/film/" + task.getId();
    }


    @GetMapping("/zadanie/add")
    public String showTaskAddForm(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "taskAdd";
    }


    @PostMapping("/zadanie/add")
    public String addTask(Task task) {
        taskRepository.save(task);
        return "redirect:/";
    }


}



