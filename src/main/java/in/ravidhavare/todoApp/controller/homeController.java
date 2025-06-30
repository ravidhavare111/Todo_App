package in.ravidhavare.todoApp.controller;

import in.ravidhavare.todoApp.entity.todoEntity;
import in.ravidhavare.todoApp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class homeController {

//    @Autowired
    private final TodoRepository todoRepo;

    @GetMapping({"","/","/home"})
    public String homeShow(Model model){
        model.addAttribute("todos",todoRepo.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String addTodo(@RequestParam String title){
        todoEntity newTodo = todoEntity.builder()
                .title(title)
                .completed(false)
                .build();
        todoRepo.save(newTodo);
        return "redirect:/home";
    }

    @GetMapping("/update/{id}")
    public String updateTodo(@PathVariable Long id){
        todoEntity existingTodo = todoRepo.findById(id).orElseThrow(() -> new RuntimeException("Todo Not Found : " +id));
        existingTodo.setCompleted(true);
        todoRepo.save(existingTodo);
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id){
        todoEntity existingTodo = todoRepo.findById(id).orElseThrow(() -> new RuntimeException("Todo Not Found : " +id));
        todoRepo.delete(existingTodo);
        return "redirect:/home";
    }
}
