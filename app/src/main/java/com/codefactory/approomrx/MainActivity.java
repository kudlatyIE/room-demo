package com.codefactory.approomrx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codefactory.approomrx.data.AppDataBase;

public class MainActivity extends AppCompatActivity {

    private TextView tvAction, tvResult;
    private EditText editName, editId, editDaysFrom, editDaysTo;
    private Button btnReset, btnInsert, btnFingText;
    private AppDataBase appDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvAction = findViewById(R.id.action_title_text);
        tvResult = findViewById(R.id.action_result_text);
        editName = findViewById(R.id.item_name_editText);
        editId = findViewById(R.id.crimeId_editText);
        editDaysFrom = findViewById(R.id.days_from_editText);
        editDaysTo = findViewById(R.id.days_to_editText);


    }
}
