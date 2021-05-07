package com.aston.quizsurvey.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aston.quizsurvey.OnClickSubQuestion;
import com.aston.quizsurvey.R;
import com.aston.quizsurvey.data.OptionsItem1;
import com.aston.quizsurvey.select;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class MultiQueAdapter extends RecyclerView.Adapter<MutltiQueVH>  {

    List<OptionsItem1> list = new ArrayList<>();
    OnClickSubQuestion onClickSubQuestion;
    select select;
    Context context;
    public MultiQueAdapter(Context context)
    {
        this.context = context;
    }

    public void setList(List<OptionsItem1> list) {
        this.list = list;
    }

    public void selected(select select){
        this.select=select;
    }


    public void setOnClickSubQuestion(OnClickSubQuestion onClickSubQuestion) {
        this.onClickSubQuestion = onClickSubQuestion;
    }

    @NonNull
    @Override
    public MutltiQueVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_sub_que,parent,false);
        return new MutltiQueVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MutltiQueVH holder, int position) {

        OptionsItem1 optionsItem1 = list.get(position);

        MaterialCardView btnOption = holder.btnOption;
        MaterialTextView optionText = holder.optionText;

        optionText.setText(optionsItem1.getTitle());

        if(optionsItem1.isSelected())
        {
             optionText.setTextColor(context.getResources().getColor(android.R.color.black));
            btnOption.setCardBackgroundColor(context.getResources().getColor(android.R.color.holo_green_dark));
            holder.btnOption.setCardBackgroundColor(context.getResources().getColor(android.R.color.holo_green_dark));


//            optionText.setText("Checked");
            }
        else
        {
            optionText.setTextColor(context.getResources().getColor(android.R.color.black));
            btnOption.setCardBackgroundColor(context.getResources().getColor(android.R.color.white));

        }


        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickSubQuestion!=null) {
                    onClickSubQuestion.selectedOption(position);
                  //  select.select(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class MutltiQueVH extends RecyclerView.ViewHolder {

    public MaterialCardView btnOption;
    public MaterialTextView optionText;
    public MutltiQueVH(@NonNull View itemView) {
        super(itemView);
        btnOption = itemView.findViewById(R.id.btnOption);
        optionText
                 = itemView.findViewById(R.id.optionText);
    }
}
