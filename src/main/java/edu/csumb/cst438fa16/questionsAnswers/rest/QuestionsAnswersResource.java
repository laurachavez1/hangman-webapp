package edu.csumb.cst438fa16.questionsAnswers.rest;

import edu.csumb.cst438fa16.questionsAnswers.QuestionsAnswers;

//import edu.csumb.cst438fa16.hangman.Hangman;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * Hangman REST service.
 *
 * See:
 * https://jersey.java.net/documentation/latest/jaxrs-resources.html
 */
@Path("")
public class QuestionsAnswersResource {
	static QuestionsAnswers qa = new QuestionsAnswers(){{
		put("Water is", "clear");
		put("Pursuit of", "happiness");
		put("Kid", "Cudi");
	}};
	
	@GET
	@Path("/randomquestion")
	public String randomquestion(){
		return qa.getRandomQuestion();
	}
	
	@GET
	@Path("/testanswer")
	public Response testanswer(@QueryParam("question") String question, @QueryParam("answer") String answer){
		if(answer == null || answer == " "){
	            return Response.status(Response.Status.BAD_REQUEST)
		                   .entity("answer is empty")
			           .build();
	    }
		
		if (!qa.testAnswer(question, answer)) {
	            return Response.status(Response.Status.BAD_REQUEST)
		                   .entity("'" + answer + "' is the wrong answer to '" + question +
					   "'")
			           .build();
	        }
		
		return Response.ok("'" + answer + "' is the right answer to '" + question + "'").build();
	}
}
