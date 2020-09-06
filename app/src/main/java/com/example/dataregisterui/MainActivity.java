package com.example.dataregisterui;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private ScrollView parent;
    private EditText editName,editMail,editPass,editRe;
    private Button button;
    private TextView warningName,warningMail,warningPass1,warningPass2;
    private RadioGroup gender;
    private Spinner countrySpinner;
    private CheckBox agreeCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedBtn();
            }
        });

    }

    private void clickedBtn(){
        Log.d(TAG,"clickedBtn :  Started");

        validateData();

        if(validateData()){
            if(agreeCheck.isChecked()){
                showSnackBar();
            }else{
                Toast.makeText(this, "You need to agree to user Agreement", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSnackBar(){
        Log.d(TAG, "snackBar : Started");

        warningName.setVisibility(View.GONE);
        warningPass2.setVisibility(View.GONE);
        warningPass1.setVisibility(View.GONE);
        warningMail.setVisibility(View.GONE);

        Snackbar.make(parent,"Registered", Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .show();
    }

    private boolean validateData(){
        Log.d(TAG,"Validate :  Started");

        if(editName.getText().toString().equals("")){
            warningName.setVisibility(View.VISIBLE);
            return false;
        }

        if(editMail.getText().toString().equals("")){
            warningMail.setVisibility(View.VISIBLE);
            return false;
        }

        if(editPass.getText().toString().equals("")){
            warningPass1.setVisibility(View.VISIBLE);
            return false;
        }

        if(editRe.getText().toString().equals("")){
            warningPass2.setVisibility(View.VISIBLE);
            return false;
        }
        else  if(!editRe.getText().toString().equals(editPass.getText().toString())) {
            warningPass2.setText("Please input your password correctly");
            warningPass2.setVisibility(View.VISIBLE);
            return false;
        }

        return true;
    }

    private void initView(){
        Log.d(TAG,"initViews : Started");

        parent = findViewById(R.id.parent);

        editName = findViewById(R.id.editName);
        editMail = findViewById(R.id.editMail);
        editPass = findViewById(R.id.editPass);
        editRe = findViewById(R.id.editRe);

        button = findViewById(R.id.btn);

        warningName = findViewById(R.id.warningName);
        warningMail = findViewById(R.id.warningMail);
        warningPass1 = findViewById(R.id.warningPass1);
        warningPass2 = findViewById(R.id.warningPass2);

        gender = findViewById(R.id.gender);

        countrySpinner = findViewById(R.id.countrySpinner);

        agreeCheck = findViewById(R.id.agreeCheck);
    }

}