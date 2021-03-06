package app;

import app.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="users", path ="users")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByPassword(String password);
    User findUserByUsernameAndPassword(String username, String password);
}