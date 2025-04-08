import entity.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import service.QuestionService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionServiceTest {
    private QuestionService questionService;

    @BeforeEach
    public void init() {
        questionService = new QuestionService();
    }

    @ParameterizedTest
    @ValueSource(longs = { 1L, 2L, 3L })
    public void getById(Long id) {
        Question question = questionService.getById(id);
        assertEquals(id, question.getId());
    }
}
