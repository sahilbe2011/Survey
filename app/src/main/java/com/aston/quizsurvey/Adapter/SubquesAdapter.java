package com.aston.quizsurvey.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aston.quizsurvey.OnClickSubQuestion;
import com.aston.quizsurvey.R;
import com.aston.quizsurvey.data.OptionsItem;
import com.aston.quizsurvey.data.OptionsItem1;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class SubquesAdapter extends RecyclerView.Adapter<SubVH>  {

    List<OptionsItem> list = new ArrayList<>();
    OnClickSubQuestion onClickSubQuestion;
    Context context;
    public SubquesAdapter(Context context)
    {
        this.context = context;
    }

    public void setList(List<OptionsItem> list) {
        this.list = list;
    }

    public void setOnClickSubQuestion(OnClickSubQuestion onClickSubQuestion) {
        this.onClickSubQuestion = onClickSubQuestion;
    }

    @NonNull
    @Override
    public SubVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_sub_que,parent,false);
        return new SubVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubVH holder, int position) {

        OptionsItem optionsItem1 = list.get(position);

        MaterialCardView btnOption = holder.btnOption;
        MaterialTextView optionText = holder.optionText;

        optionText.setText(optionsItem1.getTitle());

        if(optionsItem1.isSelected())
        {
         optionText.setTextColor(context.getResources().getColor(android.R.color.white));
         btnOption.setCardBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));



        }
        else
        {
            optionText.setTextColor(context.getResources().getColor(android.R.color.black));
            btnOption.setCardBackgroundColor(context.getResources().getColor(android.R.color.white));


        }


        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickSubQuestion!=null)
                    onClickSubQuestion.selectedOption(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class SubVH extends RecyclerView.ViewHolder {

    public MaterialCardView btnOption;
    public MaterialTextView optionText;
    public SubVH(@NonNull View itemView) {
        super(itemView);
        btnOption = itemView.findViewById(R.id.btnOption);
        optionText
                = itemView.findViewById(R.id.optionText);
    }
}
