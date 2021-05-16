package tr.edu.yildiz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegisterActivity extends AppCompatActivity {
    private static final String FILE_NAME = "users2.txt";

    private EditText name, surname, phone, register_email, register_password1, register_password2;
    private Button create, image_button;
    private ImageView profil_pic;
    static final int SELECT_IMAGE = 0;
    Uri imageUri;

    public void init(){
        create = findViewById(R.id.create);
        image_button = findViewById(R.id.image_button);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        phone = findViewById(R.id.phone);
        register_email = findViewById(R.id.register_email);
        register_password1 = findViewById(R.id.register_password1);
        register_password2 = findViewById(R.id.register_password2);
        profil_pic = findViewById(R.id.profil_pic);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        // Resim seçme
        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImage = new Intent(Intent.ACTION_GET_CONTENT);
                intentImage.setType("image/*");
                startActivityForResult(intentImage, SELECT_IMAGE);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNewAcc = new Intent(RegisterActivity.this, MainActivity.class);
                String username = name.getText().toString();
                String usersurname = surname.getText().toString();
                String userphone = phone.getText().toString();
                String useremail = register_email.getText().toString();
                String userpassword = register_password1.getText().toString();
                String text = useremail+","+username+","+usersurname+","+userphone+","+userpassword+";\n" ;

                int flag =load(useremail);

                if (flag == 0 && username!= "" &&  usersurname!= ""
                 && userphone.length()== 11 && useremail!= ""
                &&  userpassword.equals(register_password2.getText().toString())){

                    save(text);
                    startActivity(intentNewAcc);


                }else if (flag == 1){
                    Toast toast_register_error = Toast.makeText(getApplicationContext(), "Bu e-posta adresiyle kayıtlı başka bir hesap var", Toast.LENGTH_LONG);
                    toast_register_error.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast_register_error.show();
                }
                else {
                    Toast toast_register_error = Toast.makeText(getApplicationContext(), "Tüm alanları doldurun!/Parolalar uyumlu değil!", Toast.LENGTH_LONG);
                    toast_register_error.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast_register_error.show();
                }
            }
        });
    }

    public void save(String text){
        FileOutputStream fos = null;


        try {
            fos = openFileOutput(FILE_NAME, MODE_APPEND);
            String text1 = text;
            fos.write(text.getBytes());
            Toast toast_dir = Toast.makeText(getApplicationContext(), "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
            profil_pic.setImageURI(imageUri);

        }
    }

    public int load(String email){

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
                if (useremail.equals(email)) return 1;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return 0;
    }

    static String stripExtension (String str) {
        if (str == null) return null;
        String[] parts = str.split(",");
        String useremail = parts[0];

        return useremail;

    }

}