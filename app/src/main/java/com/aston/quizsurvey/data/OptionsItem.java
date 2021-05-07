package com.aston.quizsurvey.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OptionsItem{

	@SerializedName("options")
	private List<OptionsItem1> subOptions;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("type")
	private String type;

	boolean selected = false;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public boolean getSelected() {
		return isSelected();
	}


	public List<OptionsItem1> getSubOptions() {
		return subOptions;
	}

	public void setSubOptions(List<OptionsItem1> subOptions) {
		this.subOptions = subOptions;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
 	public String toString(){
		return 
			"OptionsItem{" + 
			"id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}