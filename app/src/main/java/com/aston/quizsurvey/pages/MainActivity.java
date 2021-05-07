package com.aston.quizsurvey.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.aston.quizsurvey.Network.ApiClient;
import com.aston.quizsurvey.Network.Loginresponce;
import com.aston.quizsurvey.R;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextInputLayout number;
    Button login;
    TextView textView;
    TextInputEditText text;
    CheckBox checkBox;
    SharedPreferences sharedPreferences;
    static final String sharedPreferences_name = "checked";
    AwesomeValidation awesomeValidation;
    final String url = "https://www.survey.astonsof.com/api/insert_survey_data.php?method=appLogin";
    String ROUTE = "appLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = findViewById(R.id.textField);
        login = findViewById(R.id.login);
        text = findViewById(R.id.number);
        String num = text.getText().toString();
        checkBox = findViewById(R.id.checkBox);
//        LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);

        sharedPreferences = getSharedPreferences(sharedPreferences_name, MODE_PRIVATE);
        String checkbox = sharedPreferences.getString("remember", "");

        getPreferencesData();


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.textField, "^\\d{10}$", R.string.invalid_phone);


        awesomeValidation.addValidation(MainActivity.this, R.id.number, RegexTemplate.NOT_EMPTY, R.string.invalid_number);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {

//                    loadingDialog.startLoadingDialog();
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            loadingDialog.dismissDialog();
//                        }
//                    }, 2000);
                    login();
//                    new LoginUser().execute(number.getEditText().getText().toString());
                    if (checkBox.isChecked()) {
                        Boolean aBoolean = checkBox.isChecked();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name", text.getText().toString());
                        editor.putBoolean("check", aBoolean);
                        editor.apply();
                        Toast.makeText(MainActivity.this, "Remembered", Toast.LENGTH_SHORT).show();
                    } else {
                        sharedPreferences.edit().clear().apply();
                    }

                }
            }
        });

    }

    public void login() {
        Call<Loginresponce> call= ApiClient.getInstance().getApi().getlogin(ROUTE,text.getText().toString());
        call.enqueue(new Callback<Loginresponce>() {
            @Override
            public void onResponse(Call<Loginresponce> call, Response<Loginresponce> response) {
                if (response.body() != null) {
                    Loginresponce getReg = response.body();

                   
                    if (getReg.getId()>0) {
                        Log.d("sahil","sahil");
//                        Toast.makeText(getApplicationContext(), "Welcome Mr. ", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this, StartSurvey.class);
                        int agent_id=getReg.getId();
                        intent.putExtra("agent_id",String.valueOf(agent_id));
                        startActivity(intent);
                        finish();

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

    private void getPreferencesData() {
        SharedPreferences sp = getSharedPreferences(sharedPreferences_name, MODE_PRIVATE);
        if (sp.contains("name")) {
            String u = sp.getString("name", "Not Found");
            text.setText(u.toString());
        }
        if (sp.contains("check")) {
            Boolean b = sp.getBoolean("check", false);
            checkBox.setChecked(b);
        }
    }


    private void valid(String number) {
        if (number.equalsIgnoreCase("9897005406")) {
            Toast.makeText(this, "Login.......", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Questions.class);
            startActivity(intent);
            text.getText().clear();
        } else {

        }
    }

//    public class LoginUser extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            String num = strings[0];
//            OkHttpClient okHttpClient = new OkHttpClient();
//            RequestBody body = new FormBody.Builder()
//                    .add("phone", num).build();
//            Request request = new Request.Builder()
//                    .url(url).post(body).build();
//
//            okhttp3.Response response = null;
//            try {
//                response = okHttpClient.newCall(request).execute();
//                if (response.isSuccessful()){
//                    String result=response.body().string();
//                    if(result.equalsIgnoreCase("Login")){
//                        Intent intent=new Intent(MainActivity.this,Questions.class);
//                        startActivity(intent);
//                        finish();
//
//                    }
//                    else
//                    {
//                        Toast.makeText(MainActivity.this,"Worng",Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//    }
}
//class LoadingDialog{
//    Activity activity;
//    AlertDialog dialog;
//    LoadingDialog(Activity myactivity){
//        activity=myactivity;

//    }
//    void startLoadingDialog(){
//        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
//        LayoutInflater inflater=activity.getLayoutInflater();
//        builder.setView(inflater.inflate(R.layout.progess_dialog,null));
//        builder.setCancelable(true);
//        dialog=builder.create();
//        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        dialog.show();
//    }
//    void dismissDialog(){
//        dialog.dismiss();
//    }
//}

