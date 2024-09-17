package Myproject.mallapi.repository.search;

import Myproject.mallapi.domain.QTodo;
import Myproject.mallapi.domain.Todo;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Slf4j
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Page<Todo> search1() {
        log.info("searching............");

        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);

        query
                .where(todo.title.contains("1"));

        Pageable pageable = PageRequest.of(1, 10, Sort.by("id").descending());
        this.getQuerydsl().applyPagination(pageable, query);

        query.fetch();
        query.fetchCount();
        return null;
    }

}
