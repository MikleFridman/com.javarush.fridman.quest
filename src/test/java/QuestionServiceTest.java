import entity.Question;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import service.QuestionService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionServiceTest {
    @ParameterizedTest
    @ValueSource(longs = { 1L, 2L, 3L })
    public void getById(Long id) {
        QuestionService questionService = new QuestionService();
        Question question = questionService.getById(id);
        assertEquals(id, question.getId());
    }
}
