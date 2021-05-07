package com.aston.quizsurvey.data;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	public Response(List<QuestionsItem> questions) {
		this.questions = questions;
	}

	@SerializedName("questions")
	private List<QuestionsItem> questions;

	public void setQuestions(List<QuestionsItem> questions){
		this.questions = questions;
	}

	public List<QuestionsItem> getQuestions(){
		return questions;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"questions = '" + questions + '\'' + 
			"}";
		}
}