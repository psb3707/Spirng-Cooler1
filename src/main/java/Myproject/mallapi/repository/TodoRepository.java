package Myproject.mallapi.repository;

import Myproject.mallapi.domain.Todo;
import Myproject.mallapi.repository.search.TodoSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {
}
