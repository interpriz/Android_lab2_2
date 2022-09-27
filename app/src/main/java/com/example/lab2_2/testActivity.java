package com.example.lab2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class testActivity extends AppCompatActivity {

    private TextView textView;
    private TextView check;
    private TextView percentResult;
    private EditText result;
    private ProgressBar progressBar;
    private Button buttonCheck;
    private Button buttonNext;

    int score = 0;
    int counter = 1;
    int a;
    int b;
    final double number = 5;
    String mode;

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

        buttonNext.setOnClickListener((v) -> {

            if (counter <= number) {
                counter++;
                buttonCheck.setClickable(true);
                createEquation(mode);
            } else if (counter == number + 1) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }

        });

        buttonCheck.setOnClickListener((v) -> {
            int res = Integer.parseInt(result.getText().toString());
            if (a * b == res) {
                score++;
                check.setText("верно!");
            } else check.setText("не верно!");
            buttonCheck.setClickable(false);
            if (counter == number) {
                counter++;
                percentResult.setText("Правильно:" + score / number * 100 + "%");
                buttonNext.setText("В меню");
            }
        });

    }
}