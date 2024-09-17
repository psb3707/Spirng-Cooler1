package Myproject.mallapi.repository.search;

import Myproject.mallapi.domain.Todo;
import org.springframework.data.domain.Page;

public interface TodoSearch {
    Page<Todo> search1();
}
