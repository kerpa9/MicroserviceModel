package model_courses.model_courses.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import model_courses.model_courses.domail.Users;

@FeignClient(name = "model_users", url = "localhost:3000")
@RequestMapping("/api/v1/users")
public interface IUserClientRest {

    @GetMapping("detail/{id}")
    Users detail(@PathVariable Long id);

    @PostMapping("/")
    Users create(@RequestBody Users users);
}
