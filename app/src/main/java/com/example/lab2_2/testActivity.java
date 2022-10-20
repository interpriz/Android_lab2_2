package com.example.lab2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class testActivity extends AppCompatActivity {

    private TextView textView;
    private TextView check;
    private TextView percentResult;
    private EditText result;
    private Button buttonCheck;
    private Button buttonNext;

    int score = 0;
    int counter = 1;
    int a;
    int b;
    final int number = 5;
    String mode;
    boolean flagCheck = false;

    private void createEquation(String mode) {
        if (mode.equals("all"))
            a = new Random().nextInt(8) + 1;
        b = new Random().nextInt(8) + 1;
        textView.setText(a + "*" + b + "=");
        check.setText("");
        result.setText("");
        percentResult.setText(counter + " из " + number);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textView = findViewById(R.id.textView);
        check = findViewById(R.id.check);
        percentResult = findViewById(R.id.percentResult);
        result = findViewById(R.id.result);
        buttonCheck = findViewById(R.id.buttonCheck);
        buttonNext = findViewById(R.id.buttonNext);

        Bundle arguments = getIntent().getExtras();
        mode = arguments.get("mode").toString();
        if (mode.equals("some"))
            a = arguments.getInt("a");

        createEquation(mode);

    }

     public void buttonNext (View view){
        if(flagCheck){
            flagCheck = false;
            if (counter <= number) {
                counter++;
                buttonCheck.setClickable(true);
                createEquation(mode);
            } else if (counter == number + 1) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        }else{
            Toast toast = Toast.makeText(this, "Вы не проверили пример!",Toast.LENGTH_LONG);
            toast.show();
        }


    };

    public void buttonCheck(View view ){
        flagCheck = true;
        int res = Integer.parseInt(result.getText().toString());
        if (a * b == res) {
            score++;
            check.setText("верно!");
            check.setTextColor(Color.GREEN);
        } else{
            check.setText("не верно!");
            check.setTextColor(Color.RED);
        }
        buttonCheck.setClickable(false);
        if (counter == number) {
            counter++;
            percentResult.setText("Правильно:" + score / (double)number * 100 + "%");
            buttonCheck.setVisibility(View.INVISIBLE);
            buttonNext.setText("В меню");
        }
    };
}