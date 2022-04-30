package com.example.tagalong;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.editTextTextPersonName2);
        Login = (Button)findViewById(R.id.button);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }


    private void Validate(String username, String userPassword)
    {
        if((username.equals("admin")) && (userPassword.equals("admin")))
        {
            Intent intent = new Intent(MainActivity.this,GetLocation.class);
            startActivity(intent);
        }else
        {
            String message = "You have entered wrong username or password";
            NotificationCompat.Builder builder = new NotificationCompat.Builder(
                     MainActivity.this
            )
                    .setSmallIcon(R.drawable.ic_message)
                    .setContentTitle("Wrong Login")
                    .setContentText(message)
                    .setAutoCancel(true);



            Login.setEnabled(false);
        }

    }
}