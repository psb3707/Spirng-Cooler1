package Myproject.mallapi.repository;

import Myproject.mallapi.domain.Todo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void 리포지토리_테스트() {

        Assertions.assertNotNull(todoRepository);

        log.info(todoRepository.getClass().getName());

    }

    @Test
    public void todo생성테스트() {
        Todo todo = Todo.builder()
                .title("title")
                .content("content")
                .dueDate(LocalDate.of(2024, 10, 31))
                .build();

        Todo result = todoRepository.save(todo);
        log.info(result.toString());

        Assertions.assertNotNull(result);
        Assertions.assertEquals("title", result.getTitle());
        Assertions.assertEquals("content", result.getContent());
        Assertions.assertEquals(LocalDate.of(2024, 10, 31), result.getDueDate());
    }

    @Test
    public void todo조회테스트() {
        Long myId = 1L;

        Todo todo = todoRepository.findById(myId).orElse(null);

        assertNotNull(todo);
        log.info(todo.toString());

        Assertions.assertEquals("title", todo.getTitle());
        Assertions.assertEquals("content", todo.getContent());
        Assertions.assertEquals(LocalDate.of(2024, 10, 31), todo.getDueDate());
    }

    @Test
    public void todo수정테스트() {
        Long myId = 1L;

        Todo todo = todoRepository.findById(myId).orElse(null);
        assertNotNull(todo);

        todo.changeTitle("changedTitle");
        todo.changeContent("changedContent"); // 객체는 불변으로 유지하는 것이 유지/보수에 적합, 테스트용 메서드

        Todo result = todoRepository.save(todo);

        log.info(result.toString());
        Assertions.assertEquals("changedTitle", result.getTitle());
        Assertions.assertEquals("changedContent", result.getContent()); // 변경한 값들에 대한 검증
        Assertions.assertEquals(LocalDate.of(2024, 10, 31), result.getDueDate()); // 변경하지 않은 값은 유지
    }

    @Test
    public void 페이징테스트() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending()); // 오류 발생 ("todo_id)로 매핑 시 오류
        Page<Todo> list = todoRepository.findAll(pageable);

        log.info(list.toString());

        Assertions.assertEquals(2, list.getTotalElements());
        Assertions.assertEquals(2, list.getContent().get(0).getId());

    }

    @Test
    public void Querydsl테스트() {
        todoRepository.search1();
    }
}