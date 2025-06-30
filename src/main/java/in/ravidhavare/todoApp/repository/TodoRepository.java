package in.ravidhavare.todoApp.repository;

import in.ravidhavare.todoApp.entity.todoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<todoEntity, Long> {

}
