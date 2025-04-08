package controller;

import entity.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/quest")
public class QuestController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    public QuestionService questionService;

    public QuestController() {
        logger.info("Start quest");
        questionService = new QuestionService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long id = request.getParameter("id") == null ? 1 : Long.parseLong(request.getParameter("id"));
        if (id < 0 || id == 999) {
            request.setAttribute("result", id);
            getServletContext().getRequestDispatcher("/finish.jsp").forward(request, response);
        }
        HttpSession session = request.getSession();
        int counter = session.getAttribute("counter") == null ? 0 : (int) session.getAttribute("counter");
        String eraseCounter = request.getParameter("erase");
        if (Objects.equals(eraseCounter, "true")) {
            counter = 0;
            session.removeAttribute("counter");
        }
        if (id == 1) {
            session.setAttribute("counter", ++counter);
        }
        Question question = questionService.getById(id);
        if (question != null) {
            request.setAttribute("question", question);
            request.setAttribute("answers", questionService.getAnswerList(question));
            getServletContext().getRequestDispatcher("/quest.jsp").forward(request, response);
        }
    }
}
