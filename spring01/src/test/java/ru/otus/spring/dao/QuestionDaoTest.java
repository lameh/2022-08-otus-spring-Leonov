package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionDaoTest {

    @Test
    void verifyDataFromResource() throws IOException {
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("1,What is your name?");
        expectedList.add("2,What is your gender?,man,woman");
        expectedList.add("3,How old are your?,less than 18,between 18 and 45,older than 45");
        expectedList.add("4,Do you have education?,yes,no");
        expectedList.add("5,What country are you from?,Russia,USA,other");

        List<String> actual = new QuestionDaoResources("question_list.csv").readQuestions();

        Iterator<String> iterator = actual.iterator();
        Iterator<String> expectedIterator = expectedList.iterator();

        while(iterator.hasNext()) {
            var actualRes = iterator.next();
            var expectedRes = expectedIterator.next();
            assertThat(actualRes.equals(expectedRes));
        }
    }
}
