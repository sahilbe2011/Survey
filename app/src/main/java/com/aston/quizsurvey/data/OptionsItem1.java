package com.aston.quizsurvey.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class OptionsItem1 implements Parcelable {

    public OptionsItem1(String id, String title) {
        this.id = id;
        this.title = title;
    }

    protected OptionsItem1(Parcel in) {
        id = in.readString();
        title = in.readString();
        selected = in.readByte() != 0;
    }

    public static final Creator<OptionsItem1> CREATOR = new Creator<OptionsItem1>() {
        @Override
        public OptionsItem1 createFromParcel(Parcel in) {
            return new OptionsItem1(in);
        }

        @Override
        public OptionsItem1[] newArray(int size) {
            return new OptionsItem1[size];
        }
    };

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    boolean selected  = false;


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

    @Override
    public String toString(){
        return
                "OptionsItem{" +
                        "id = '" + id + '\'' +
                        ",title = '" + title + '\'' +
                        "}";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeByte((byte) (selected ? 1 : 0));
    }
}