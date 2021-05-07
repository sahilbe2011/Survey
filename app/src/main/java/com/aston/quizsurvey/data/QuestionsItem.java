package com.aston.quizsurvey.data;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class QuestionsItem{
	public QuestionsItem(List<OptionsItem> options, String id, String title, String type, String multilevel) {
		this.options = options;
		this.id = id;
		this.title = title;
		this.type = type;
		this.multilevel = multilevel;
	}
	public  QuestionsItem(){}

	@SerializedName("options")
	private List<OptionsItem> options;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("type")
	private String type;

	@SerializedName("multilevel")
	private String multilevel;



	public void setOptions(List<OptionsItem> options){
		this.options = options;
	}

	public List<OptionsItem> getOptions(){
		return options;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setMultilevel(String multilevel){
		this.multilevel = multilevel;
	}

	public String getMultilevel(){
		return multilevel;
	}

	@Override
 	public String toString(){
		return 
			"QuestionsItem{" + 
			"options = '" + options + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",type = '" + type + '\'' + 
			",multilevel = '" + multilevel + '\'' + 
			"}";
		}
}