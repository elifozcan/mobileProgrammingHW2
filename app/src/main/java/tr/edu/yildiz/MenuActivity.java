package tr.edu.yildiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private Button question_add,question_list,create_test,settings;
    private TextView userName;
    private ImageView profile_pic;

    public void init(){
        question_add = findViewById(R.id.question_add);
        question_list= findViewById(R.id.question_list);
        create_test =findViewById(R.id.create_test);
        settings=findViewById(R.id.settings);
        userName=findViewById(R.id.userName);
        profile_pic=findViewById(R.id.profil_pic);
        userName=findViewById(R.id.userName);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();

        Intent intent = getIntent();
        String useremail = intent.getStringExtra("EMAIL");

        //userName.setText(useremail);

        question_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_add = new Intent(MenuActivity.this, AddQuestionActivity.class);
                intent_add.putExtra("EMAIL", useremail);
                startActivity(intent_add);
            }
        });

        question_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_list = new Intent(MenuActivity.this, ListQuestionsActivity.class);
                intent_list.putExtra("EMAIL", useremail);
                startActivity(intent_list);
            }
        });

        create_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_create_test = new Intent(MenuActivity.this, CreateTestActivity.class);
                intent_create_test.putExtra("EMAIL", useremail);
                startActivity(intent_create_test);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_settings = new Intent(MenuActivity.this, TestSettingsActivity.class);
                intent_settings.putExtra("EMAIL", useremail);
                startActivity(intent_settings);
            }
        });
    }
}