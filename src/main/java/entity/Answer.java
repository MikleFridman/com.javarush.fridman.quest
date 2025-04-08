package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {
    private long id;
    private String text;
    private long nextQuestionId;
}
