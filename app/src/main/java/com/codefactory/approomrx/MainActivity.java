package com.codefactory.approomrx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codefactory.approomrx.data.AppDataBase;
import com.codefactory.approomrx.viewmodels.JudgeViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvAction, tvResult;
    private EditText editName, editId, editDaysFrom, editDaysTo;
    private Button btnReset, btnInsert, btnFingText;
    private AppDataBase appDataBase;

    private JudgeViewModel viewModel;

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

        viewModel = ViewModelProviders.of(this).get(JudgeViewModel.class);

        viewModel.initDataBase();


    }

    private void subscribeView(){
        viewModel.getCrimesResult().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                tvResult.setText(s);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.reset_button:
                tvAction.setText("reset DB");
                subscribeView();
                break;
            case R.id.find_byName_button:
                break;
        }
    }
}
