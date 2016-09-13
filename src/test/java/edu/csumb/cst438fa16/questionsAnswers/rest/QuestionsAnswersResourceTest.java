package edu.csumb.cst438fa16.questionsAnswers.rest;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.*;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

/**
 * See:
 * https://jersey.java.net/documentation/latest/test-framework.html
 * https://jersey.java.net/apidocs/latest/jersey/index.html
 */
public class QuestionsAnswersResourceTest extends JerseyTest {
	
	@Override
    protected Application configure() {
        return new ResourceConfig(QuestionsAnswersResource.class);
    }
	
	@Test
    public void testQuestion() {
        WebTarget webTarget = target("randomquestion");
        String theq = webTarget.request().get(String.class);
        assertThat(theq, anyOf(is("Water is"), is("Kid"), is("Pursuit of")));
    }
	
	@Test
    public void testWhenAnswerIsNull() {
        WebTarget webTarget = target("testanswer").queryParam("question", "Kid")
                                             .queryParam("answer", " ");
        Response response = webTarget.request().get();
        assertThat(response.getStatus(),
                   equalTo(Response.Status.BAD_REQUEST.getStatusCode()));
    }
	
	@Test
	public void testWhenAnswerIsWrong(){
		WebTarget webTarget = target("testanswer").queryParam("question", "Water is")
                .queryParam("answer", "healthy");
		Response response = webTarget.request().get();
		assertThat(response.getStatus(),
		equalTo(Response.Status.BAD_REQUEST.getStatusCode()));
	}
	
	@Test
	public void testWhenAnswerIsCorrect(){
		WebTarget webTarget = target("testanswer").queryParam("question", "Pursuit of")
                .queryParam("answer", "happiness");
		Response response = webTarget.request().get();
		assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
	}
}
