package model_courses.model_courses.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import model_courses.model_courses.domail.Users;

@FeignClient(name = "model-users", url = "localhost:3000")
public interface IUserClientRest {

    @GetMapping("/{id}")
    Users getById(@PathVariable Long id);

    @PostMapping("/")
    Users create(@RequestBody Users users);

    @GetMapping("/users")
    List<Users> usersByCourse(@RequestParam Iterable<Long> ids);

}
