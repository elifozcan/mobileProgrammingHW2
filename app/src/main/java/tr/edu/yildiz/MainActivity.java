package tr.edu.yildiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {



    private EditText login_email, login_password;
    private Button login_button, new_acc;
    int count=0;

    public void init(){
        login_button = (Button) findViewById(R.id.login_button);
        new_acc = (Button) findViewById(R.id.new_acc);

        login_email = (EditText)findViewById(R.id.login_email);
        login_password = (EditText)findViewById(R.id.login_password);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(MainActivity.this, MenuActivity.class);
                String useremail = login_email.getText().toString();
                String userpassword = login_password.getText().toString();

                String data = load(useremail);
                String[] parts = data.split(",");
                String password = parts[4].split(";")[0];



                if(userpassword.equals(password)){
                    intentLogin.putExtra("EMAIL", useremail);
                    startActivity(intentLogin);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Hatalı Giriş Bilgileri ", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    count = count+1;
                    if(count>2){
                        login_button.setEnabled(false);
                        Toast toast_register = Toast.makeText(getApplicationContext(), "Yeni Hesap Oluştur", Toast.LENGTH_LONG);
                        toast_register.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast_register.show();

                    }
                }

            }
        });

        new_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intentRegister);

            }
        });
    }

    public String load(String email){

        try {
            FileInputStream fileInputStream = openFileInput("users2.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;
            String useremail;
            while ((lines = bufferedReader.readLine()) != null) {
                stringBuffer.append(lines + "\n");
                useremail=stripExtension(lines);
                if (useremail.equals(email)) return lines;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    static String stripExtension (String str) {
        if (str == null) return null;
        String[] parts = str.split(",");
        String useremail = parts[0];

        return useremail;

    }



}