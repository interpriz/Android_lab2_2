package com.example.lab2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button buttonAll;
    private Button buttonSome;
    private EditText enterNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAll = findViewById(R.id.buttonAll);
        buttonSome = findViewById(R.id.buttonSome);
        enterNumber = findViewById(R.id.editText);

        buttonAll.setOnClickListener((v)-> {
            // создание объекта Intent для запуска SecondActivity
            Intent intent = new Intent(this, testActivity.class);
            // передача объекта с ключом "hello" и значением "Hello World"
            intent.putExtra("mode", "all");
            // запуск SecondActivity
            startActivity(intent);
        });

        buttonSome.setOnClickListener((v)-> {

            boolean flag = false;
            int a = 0;
            Toast toast = Toast.makeText(this, "Введите число от 2 до 9!",Toast.LENGTH_LONG);
            String b = enterNumber.getText().toString();
            if(!b.isEmpty()){
                a = Integer.parseInt(b);
                if(a>=2 && a<=9){
                    // создание объекта Intent для запуска SecondActivity
                    Intent intent = new Intent(this, testActivity.class);
                    // передача объекта с ключом "hello" и значением "Hello World"
                    intent.putExtra("mode", "some");
                    intent.putExtra("a", a);
                    // запуск SecondActivity
                    startActivity(intent);
                }else{
                    toast.show();
                }
            }else{
                toast.show();
            }

        });

    }
}