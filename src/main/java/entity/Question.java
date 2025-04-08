package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Question {
    private long id;
    private String text;
    private List<Answer> answers;
}
