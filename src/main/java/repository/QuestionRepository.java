package repository;

import entity.Answer;
import entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionRepository implements Repository<Question> {
    private static final Logger logger = LogManager.getLogger();
    public final List<Question> questionList = new ArrayList<>();

    public void initDb() {
        logger.info("Initialize database...");

        @Data
        @AllArgsConstructor
        class QuestionRecord {
            public long id;
            public String text;
            public List<Long> answerList;
        }
        @Data
        @AllArgsConstructor
        class AnswerRecord {
            public long id;
            public String text;
            public long nextQuestionId;
        }

        List<QuestionRecord> questionTable = new ArrayList<>();
        List<AnswerRecord> answerTable = new ArrayList<>();
        answerTable.add(new AnswerRecord(11L,"Да", 2L));
        answerTable.add(new AnswerRecord(12L,"Нет",-1L));
        answerTable.add(new AnswerRecord(21L,"Умею", 3L));
        answerTable.add(new AnswerRecord(22L,"Не умею",-1L));
        answerTable.add(new AnswerRecord(31L,"Смогу", 999L));
        answerTable.add(new AnswerRecord(32L,"Не смогу",-1L));
        answerTable.add(new AnswerRecord(33L,"Загуглю на хабре",999L));

        questionTable.add(new QuestionRecord(1L,"Вы хотите у нас работать?", Arrays.asList(11L, 12L)));
        questionTable.add(new QuestionRecord(2L,"Вы умеете программировать?", Arrays.asList(21L, 22L)));
        questionTable.add(new QuestionRecord(3L,"Сможете написать сортировку пузырьком?", Arrays.asList(31L, 32L, 33L)));

        for (QuestionRecord questionRecord : questionTable) {
            List<Answer> answerList = new ArrayList<>();
            for (Long index : questionRecord.answerList) {
                answerTable.stream().filter(answer -> answer.id == index)
                                    .findFirst().ifPresent(answerRecord -> answerList
                                    .add(new Answer(answerRecord.id, answerRecord.text, answerRecord.nextQuestionId)));
            }
            questionList.add(new Question(questionRecord.id, questionRecord.text, answerList));
        }
    }

    @Override
    public Question findById(long id) {
        return questionList.stream().filter(question -> question.getId() == id)
                                    .findFirst().orElse(null);
    }

    @Override
    public List<Question> findAll() {
        return questionList;
    }
}
