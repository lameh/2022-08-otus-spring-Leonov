package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.QuestionList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionDaoTest {

    @Test
    void verifyDataFromResource() throws IOException {
        ArrayList<QuestionList> expectedList = new ArrayList<>();
        expectedList.add(new QuestionList(1, "What is your name?", ""));
        expectedList.add(new QuestionList(2, "What is your gender?", "man, woman, "));
        expectedList.add(new QuestionList(3, "How old are your?", "less than 18, between 18 and 45, older than 45, "));
        expectedList.add(new QuestionList(4, "Do you have education?", "yes, no, "));
        expectedList.add(new QuestionList(5, "What country are you from?", "Russia, USA, other, "));

        Iterator<QuestionList> actual = new QuestionDaoResources(getClass().getClassLoader()
                .getResourceAsStream("question_list.csv").toString()).iterateQuestions();

        Iterator<QuestionList> expected = expectedList.iterator();

        while(actual.hasNext()) {
            var actualRes = actual.next();
            var expectedRes = expected.next();
            assertThat(actualRes.getId()).isEqualTo(expectedRes.getId());
            assertThat(actualRes.getQuestion()).isEqualTo(expectedRes.getQuestion());
            assertThat(actualRes.getAnswers()).isEqualTo(expectedRes.getAnswers());
        }
    }
}
