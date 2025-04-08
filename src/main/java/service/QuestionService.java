package service;

import entity.Answer;
import entity.Question;
import repository.QuestionRepository;

import java.util.List;

public class QuestionService {
    public QuestionRepository questionRepository;

    public QuestionService() {
        questionRepository = new QuestionRepository();
        questionRepository.initDb();
    }

    public Question getById(Long id) {
        return questionRepository.findById(id);
    }

    public List<Answer> getAnswerList(Question question) {
        return question.getAnswers();
    }
}