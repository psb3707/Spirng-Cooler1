package Myproject.mallapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    private String title;

    private String content;

    private boolean completed;

    private LocalDate dueDate;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeCompleted(boolean completed) {
        this.completed = completed;
    }

    public void changeDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
