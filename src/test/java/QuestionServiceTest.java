import controller.QuestController;
import entity.Question;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import service.QuestionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class QuestionServiceTest {
    private static QuestionService questionService;

    @BeforeAll
    public static void init() {
        questionService = new QuestionService();
    }

    @ParameterizedTest
    @ValueSource(longs = { 1L, 2L, 3L })
    public void getById(Long id) {
        Question question = questionService.getById(id);
        assertEquals(id, question.getId());
    }

    @ParameterizedTest
    @CsvSource({
            "1, /quest.jsp",
            "999, /finish.jsp",
            "-1, /finish.jsp"
    })
    public void getServletResponse(String id, String path) throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getParameter("id")).thenReturn(id);
        when(request.getSession()).thenReturn(mock(HttpSession.class));

        QuestController questController = new QuestController();
        questController.questionService = questionService;
        questController.doGet(request, response);
        verify(dispatcher).forward(request, response);
    }
}
