package tr.edu.yildiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListQuestionsActivity extends AppCompatActivity {

    RecyclerView rv;
    CustomAdapter ad;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String useremail = intent.getStringExtra("EMAIL");

        setContentView(R.layout.activity_list_questions);

        Toast toast_dir = Toast.makeText(getApplicationContext(), useremail, Toast.LENGTH_LONG);
        toast_dir.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast_dir.show();

        String[] data = load(useremail);
        int size = data.length;
        int j=0;
        String[] line ;
        String[] question=null;
        String[] ans1s=null;
        String[] ans2s=null;
        String[] ans3s=null;
        String[] ans4s=null;
        String[] ans5s=null;
        String[] answers=null;

        for (j=0;j<size;j++){
            line=data[j].split(",");
            question[j] = line[0];
            ans1s[j] = line[1];
            ans2s[j] = line[2];
            ans3s[j] = line[3];
            ans4s[j] = line[4];
            ans5s[j] = line[5];
            answers[j] = line[6];

        }

        rv = (RecyclerView)findViewById(R.id.rv);
        ad = new CustomAdapter(ListQuestionsActivity.this, question, ans1s, ans2s, ans3s, ans4s,ans5s,answers);
        rv.setAdapter(ad);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
    }

    public String[] load(String email){

        try {
            FileInputStream fileInputStream = openFileInput(email+".txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;
            String[] data=null;
            int j= 0;
            while ((lines = bufferedReader.readLine()) != null) {
                data[j] = lines.split(";")[0];
            }
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }
}