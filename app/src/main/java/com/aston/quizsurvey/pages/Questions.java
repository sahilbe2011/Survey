package com.aston.quizsurvey.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aston.quizsurvey.Adapter.MultiQueAdapter;
import com.aston.quizsurvey.Adapter.SubquesAdapter;
import com.aston.quizsurvey.Network.ApiClient;
import com.aston.quizsurvey.Network.Loginresponce;
import com.aston.quizsurvey.OnClickSubQuestion;
import com.aston.quizsurvey.R;
import com.aston.quizsurvey.data.OptionsItem;
import com.aston.quizsurvey.data.OptionsItem1;
import com.aston.quizsurvey.data.QuestionsItem;
import com.aston.quizsurvey.data.Response;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;

public class Questions extends AppCompatActivity {
    TextView textView,numberofquestions;
    Button next,previous;
    LinearLayout linearLayout;
    private static Button[] buttonHolder;
    RecyclerView recyclerView;
    String ROUTE = "appSaveData";
    String surveydata="";
    String id;
    QuestionsItem qItem;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Number = "nameKey";
    SharedPreferences sharedpreferences;



    List<QuestionsItem> questionmodels=new ArrayList<>();
    int postion=0;
    int pos=postion;
    Response response;
    HashMap<String,String> answer=new HashMap<>();

    List<OptionsItem> questionsItem;
    List<OptionsItem> options;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        String id=getIntent().getStringExtra("agent_id");
        String name =getIntent().getStringExtra("name");
        String number =getIntent().getStringExtra("number");
        String village =getIntent().getStringExtra("village");
        String survey =getIntent().getStringExtra("survey");

//        String agent_id=getIntent().getStringExtra("agent_id");
        rg = new RadioGroup(this);
        linearLayout = (LinearLayout) findViewById(R.id.op);
//         linearLayout(op) phale likha tha
//         op.addView(rg);
        textView =findViewById(R.id.questions);
        numberofquestions=findViewById(R.id.noofquestions);
        String name1=getIntent().getStringExtra("name");
        String number11=getIntent().getStringExtra("number");
        String village1=getIntent().getStringExtra("village");
        next=findViewById(R.id.next);
        previous=findViewById(R.id.previous);
//        recyclerView.findViewById(R.id.questionrecycler);
        questionmodels=new ArrayList<>();
        questionsItem=new ArrayList<OptionsItem>();

//

//Reading Internal Json file
        try {

            String jsonStr = loadJSONFromAsset();
            Type type  = TypeToken.get(Response.class).getType();
            response = new Gson().fromJson(jsonStr,type);
            questionmodels = response.getQuestions();


           /*  JSONObject obj = new JSONObject(loadJSONFromAsset());
             JSONArray userArray = obj.getJSONArray("questions");
             for (int i = 0; i < userArray.length(); i++) {
                 JSONObject userDetail = userArray.getJSONObject(i);
                 QuestionsItem questionmodel=new QuestionsItem();
                 String id = userDetail.getString("id");
                 String title=userDetail.getString("title");
                 String type=userDetail.getString("type");
                 String multilevel=userDetail.getString("multilevel");

                  questionmodel.setId(id);
                 questionmodel.setTitle(title);
                 questionmodel.setType(type);
                 questionmodel.setMultilevel(multilevel);

                 questionmodels.add(questionmodel);
             }

            */

            if(questionmodels!=null)
                Toast.makeText(this, "Coneversion Worked", Toast.LENGTH_SHORT).show();
            //displayed

        } catch (Exception e) {
            e.printStackTrace();
        }


//        playani(textView,0,questionsItem.get(postion).getTitle());
//         next button Event
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(postion==questionmodels.size()-1)
                {
//                    [{"ques_1":1},{"ques_2":1},{"ques_3":1},{"ques_4":1},{"ques_5":1},{"ques_6":1},{"ques_7":1},{"ques_8":1},{"ques_9":1},{"ques_10":1}]
//                  = "/"" + i "/"= j,"

                    surveydata= "[";

                    Set<String> keys = answer.keySet();
                    //get all keys
                    int count =0;
                    for(String i: keys)
                    {
                        count++;
                        surveydata += "{\"" + i + "\": "+answer.get(i) +"}" ;
                        if(count!=answer.size()){
                            surveydata+=",";
                        }
                    }
                    surveydata+="]";

                    Log.d("Surveydata",surveydata);

                    sendSurveyData();
                }
                else {
                    String question_id=""+qItem.getId();
                    Set<String> keys = answer.keySet();
                    //get all keys
                    boolean error = true;
                    for(String i: keys)
                    {

                        if(i.equalsIgnoreCase(question_id)){
                            Log.d("Loop","yes");
                            error=false;
                            break;
                        }
                    }

                    if (error){
                        Toast.makeText(Questions.this,"Please select option",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        ++postion;
                        quesdisplay(postion);

                        pos++;

                    }

                }

            }

//              void setquestionnumbwe(int d) {
//                int k=d+1;
//                numberofquestions.setText(d+"/"+questionmodels.size());
//            }

//                playani(textView,0,questionsItem.get(postion).getTitle());

            //send data to Api
            private void sendSurveyData() {
                Call<Loginresponce> call= ApiClient.getInstance().getApi().appSaveData(ROUTE,surveydata,id,name,number,village);
                call.enqueue(new Callback<Loginresponce>() {
                    @Override
                    public void onResponse(Call<Loginresponce> call, retrofit2.Response<Loginresponce> response) {
                        if (response.body() != null) {
                            Loginresponce getReg = response.body();
//
                            int d= Integer.valueOf(survey);
                            int f=d+1;
                            String s=String.valueOf(f);
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Questions.this);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("number",s);
                            editor.putString("id",id);
                            editor.apply();

                            editor.commit();
//                            intent.putExtra("editTextValue",survey );
//                            setResult(RESULT_OK, intent);
//                                        finish();
                            Intent intent=new Intent(Questions.this, StartSurvey.class);

                            startActivity(intent);
                            finish();
                            Log.d("sahil", getReg.getMessage());
                            if (getReg.getStatus().equalsIgnoreCase("success")) {
                                Log.d("sahil","sahil");
                                Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT).show();

                            } else {
                                Log.d("falseee", getReg.getMessage().toString());
                                Toast.makeText(getApplicationContext(), getReg.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d("response", response.message());
                            Toast.makeText(getApplicationContext(), "Something went wrong!!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Loginresponce> call, Throwable t) {
                        Log.d("errorr", t.getMessage());
                        Toast.makeText(getApplicationContext(), "Something went wrong!!!", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

//         Previous Button Event
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(postion >0 )
                {
                    --postion;
                    quesdisplay(postion);
                }
            }
        });


//
        quesdisplay(postion);



    }

    RadioGroup rg;
    SubquesAdapter subquesAdapter;
    private void quesdisplay(int postion) {

        String nos = (postion+1)+"/"+
                (questionmodels.size());
        numberofquestions.setText(nos);

        if(postion == 0)
        {
            previous.setVisibility(View.GONE);
            next.setVisibility(View.VISIBLE);
        }
        else
        {
            previous.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
        }

        qItem = questionmodels.get(postion);
        textView.setText(qItem.getTitle());
//         Creating Radio Button
        options = qItem.getOptions();


       /*  displybutton();

         final RadioButton[] rb = new RadioButton[options.size()];

             rg.removeAllViews();

         rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
         for(int i=0; i<options.size(); i++){
             OptionsItem optionsItem = options.get(i);
             rb[i]  = new RadioButton(this);
             rb[i].setText(optionsItem.getTitle());
             rb[i].setId(Integer.parseInt(optionsItem.getId()));
             rg.addView(rb[i]);
         }


         rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {
                 if (qItem.getMultilevel() == null) {

                     String question_id = "ques_" + qItem.getId();
                     if (!question_id.equalsIgnoreCase("1")) {
                         answer.put(question_id, checkedId);
                     }
                 } else {
                     if (qItem.getMultilevel().equalsIgnoreCase("1")) {
//                       ans.add("sahil");
                         showdialog(checkedId);

                     }

                 }
             }

             private void showdialog(int checkedId) {

             }


         });


        */


        RecyclerView opRecycler = findViewById(R.id.opRecycler);
        opRecycler.setHasFixedSize(true);
        opRecycler.setLayoutManager(new LinearLayoutManager(this));
        subquesAdapter = new SubquesAdapter(this);
        subquesAdapter.setList(options);
        subquesAdapter.setOnClickSubQuestion(new OnClickSubQuestion() {
            @Override
            public void selectedOption(int pos) {
                for (int i = 0; i < options.size(); i++) {
                    OptionsItem op1 = options.get(i);
                    if(i == pos)
                    {
                        if(qItem.getMultilevel().equalsIgnoreCase("0")) {

                            op1.setSelected(true);
                            answer.put(qItem.getId(), op1.getId());


                        }
                        else {
                            if(qItem.getMultilevel().isEmpty())
                            {
                                op1.setSelected(true);
                            }
                            else
                            {
                                if(qItem.getMultilevel().equalsIgnoreCase("0"))
                                {

                                    op1.setSelected(true);
                                }
                                else
                                {
                                    showMultiLevelDialog(op1.getSubOptions(),pos,postion,op1.getTitle());
                                }
                            }
                        }

                    }
                    else
                    {
                        op1.setSelected(false);
                    }

                    options.set(i,op1);
                }


                subquesAdapter.setList(options);
                subquesAdapter.notifyDataSetChanged();
            }
        });

        opRecycler.setAdapter(subquesAdapter);
    }
    //no pop up
    private void displybutton() {
        OptionsItem optionsItem = options.get(postion);
        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT); //Layout params for Button
        linearLayout.removeAllViews();//Remove all views from Layout before placing new view
        buttonHolder = new Button[options.size()];//Setting size of Button Array
        for (int i = 0; i < options.size(); i++) {
            Button button = new Button(this);//Creating Button
            button.setId(i);//Setting Id for using in future
            button.setText(optionsItem.getTitle());//Setting text
            button.setTextSize(15);//Text Size
//             button.setLayoutParams(params);//Setting Layout Params
//             button.setOnClickListener(this);//Setting click listener
            buttonHolder[i] = button;//Setting button reference in array for future use
            linearLayout.setOrientation(LinearLayout.VERTICAL);//Setting Layout orientation
            linearLayout.addView(button);//Finally adding view
        }
    }


    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("ques.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void showMultiLevelDialog(List<OptionsItem1> optionsItem1s, final int position, int postion,String title )
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sub_question);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MaterialButton btnCancel = dialog.findViewById(R.id.btnCancel);
        MaterialButton btnOkay = dialog.findViewById(R.id.btnOkay);

        if(optionsItem1s == null)
            optionsItem1s = new ArrayList<>();

        final  List<OptionsItem1> listOptions = optionsItem1s;
        MultiQueAdapter  adapter = new MultiQueAdapter(this);
        adapter.setList(listOptions);
        adapter.setOnClickSubQuestion(new OnClickSubQuestion() {
            @Override
            public void selectedOption(int pos) {


                for (int i = 0; i < listOptions.size(); i++) {
                    OptionsItem1 op1 = listOptions.get(i);
                    if(i == pos)
                    {
                        op1.setSelected(true);
                        answer.put(title,op1.getId());

                    }
                    else
                    {
                        op1.setSelected(false);
                    }

                    listOptions.set(i,op1);
                }


                adapter.setList(listOptions);
                adapter.notifyDataSetChanged();

            }
        });

        recyclerView.setAdapter(adapter);

        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean selectedValue = false;

                for(OptionsItem1  o : listOptions)
                {
                    if(o.isSelected())
                    {
                        selectedValue = true;
                        break;
                    }

                }

                if(selectedValue) {


                    QuestionsItem questionsItem = questionmodels.get(postion);
                    List<OptionsItem> list1 = questionsItem.getOptions();
                    for (int j = 0; j < list1.size(); j++) {
                        OptionsItem optionsItem = list1.get(j);
                        if (j == position)
                            optionsItem.setSelected(true);
                        else
                            optionsItem.setSelected(false);

                        list1.set(j, optionsItem);
                    }

                    OptionsItem optionsItem = list1.get(position);
                    optionsItem.setSubOptions(listOptions);

                    list1.set(position, optionsItem);

                    subquesAdapter.setList(list1);
                    subquesAdapter.notifyDataSetChanged();

                    questionsItem.setOptions(list1);

                    questionmodels.set(postion, questionsItem);


                    dialog.dismiss();
                }
                else
                {
                    Toast.makeText(Questions.this, "Selction is Mandatory", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        if(listOptions.size() >0)
            dialog.show();

    }
    @Override
    public void onBackPressed() {

    }

}