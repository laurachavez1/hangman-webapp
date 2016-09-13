package edu.csumb.cst438fa16.questionsAnswers;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.*;

public class QuestionsAnswersTest {
    private QuestionsAnswers qas;

    @Before
    public void setup() {
	qas = new QuestionsAnswers();
    }

    @Test
    public void testEmpty() {
        try {
            qas.getRandomQuestion();
            fail("should have thrown IllegalStateException");
        } catch(IllegalStateException expected) {
            // expected
        }
    }

    @Test
    public void testWrongQuestion() {
        try {
            qas.testAnswer("q", "a");
            fail("should have thrown IllegalArgumentException");
        } catch(IllegalArgumentException expected) {
            // expected
        }
    }

    @Test
    public void testOne() {
        qas.put("q", "a");
        assertTrue(qas.testAnswer("q", "a"));
        assertFalse(qas.testAnswer("q", "a2"));
        assertThat(qas.getRandomQuestion(), equalTo("q"));
    }

    @Test
    public void testTwo() {
        qas.put("q1", "a1");
        qas.put("q2", "a2");
        assertTrue(qas.testAnswer("q1", "a1"));
        assertTrue(qas.testAnswer("q2", "a2"));
        assertFalse(qas.testAnswer("q1", "a2"));
        assertFalse(qas.testAnswer("q2", "a1"));
        assertThat(qas.getRandomQuestion(), anyOf(is("q1"), is("q2")));
    }

    @Test
    public void testNullAnswer() {
        qas.put("q1", null);
        assertTrue(qas.testAnswer("q1", null));
        assertThat(qas.getRandomQuestion(), equalTo("q1"));
        qas.put("q2", "a2");
        assertFalse(qas.testAnswer("q2", null));
    }
}
