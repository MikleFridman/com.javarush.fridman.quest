package repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Answer;
import entity.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepository implements Repository<Question> {
    private static final Logger logger = LogManager.getLogger();
    public final List<Question> questionList = new ArrayList<>();

    private void readFromJson() {
        logger.info("Reading file...");
        URL file = getClass().getResource("/data/questions.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonTree = objectMapper.readTree(file);
            for (JsonNode questionNode : jsonTree) {
                List<Answer> answerList = new ArrayList<>();
                Question question = objectMapper.treeToValue(questionNode, Question.class);
                JsonNode answerListNode = questionNode.get("answers");
                for (JsonNode answerNode : answerListNode) {
                    Answer answer = objectMapper.treeToValue(answerNode, Answer.class);
                    answerList.add(answer);
                }
                question.setAnswers(answerList);
                questionList.add(question);
            }
        } catch (IOException e) {
            logger.error("Error reading file");
            throw new RuntimeException(e);
        }
    }

    public void initDb() {
        readFromJson();
    }

    @Override
    public Question findById(long id) {
        return questionList.stream().filter(question -> question.getId() == id)
                                    .findFirst().orElse(null);
    }
}
