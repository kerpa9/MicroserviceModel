package users.model_users.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "model-courses", url = "localhost:3100")
public interface ICourseClientsRest {

    @DeleteMapping("/delete-userC/{id}")
    void deleteCourseUser(@PathVariable Long id);
}
