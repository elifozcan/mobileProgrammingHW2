package tr.edu.yildiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddQuestionActivity extends AppCompatActivity {

    private EditText question, ans1, ans2, ans3, ans4, ans5, correct_answer;
    private Button save_question;


    public void init(){
        question = findViewById(R.id.question);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
        ans5 = findViewById(R.id.ans5);
        correct_answer = findViewById(R.id.correct_answer);
        save_question = findViewById(R.id.save_question);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        init();
        Intent intent = getIntent();
        String useremail = intent.getStringExtra("EMAIL");



        save_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soru;
                soru = question.getText().toString()+","+ans1.getText().toString()+","+ans2.getText().toString()+
                        ","+ans3.getText().toString()+","+ans4.getText().toString()+","+ans5.getText().toString()+","+
                        correct_answer.getText().toString()+";\n";
                save(useremail, soru);
                Intent intent_back = new Intent(AddQuestionActivity.this, MenuActivity.class);
                intent_back.putExtra("EMAIL", useremail);
                startActivity(intent_back);
            }
        });



    }


    public void save(String email, String text){
        FileOutputStream fos = null;
        String fileName = email+".txt" ;

        try {
            fos = openFileOutput(fileName, MODE_APPEND);
            String text1 = text;
            fos.write(text.getBytes());
            Toast toast_dir = Toast.makeText(getApplicationContext(), "Saved to " + getFilesDir() + "/" + fileName+text1, Toast.LENGTH_LONG);
            toast_dir.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast_dir.show();
            return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
    }
}