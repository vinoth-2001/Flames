package com.example.neyumnanum;


import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.util.ArrayUtils;


public class MainActivity extends AppCompatActivity {

    EditText Name1, Name2;
    TextView text_flamesResult, title;
    ImageView rbtn;
    Button result;
    String FLAMES = "FLAMES";
    char[] arr_FLAMES = FLAMES.toCharArray();

    String yourName, yourNameDisplay, partnerName, partnerNameDisplay, completeName;
    String[] arr_yourName, arr_partnerName, arr_completeName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name1 = findViewById(R.id.Name1);
        Name2 = findViewById(R.id.Name2);
        result = findViewById(R.id.result);
        text_flamesResult = findViewById(R.id.textview);
        rbtn = findViewById(R.id.resetbtn);
        title= (TextView) findViewById(R.id.title);

        //title 𝘍𝘓𝘈𝘔𝘌𝘚
        //String text = "<font color=#FF7000>F</font><font color=#ffcc00>L</font><font color=#540375>A</font><font color=#FFBF00>M</font>" +
            //    "<font color=#5F8D4E>E</font><font color=#ffcc00>S</font>";
        //title.setText(Html.fromHtml(text));

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //hide keyboard
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                //flames calculated
                flamsfun();
            }
        });

        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //reset all
                resetbtnimg();
            }
        });



    }


    private void flamsfun() {

        if (!Name2.getText().toString().trim().isEmpty() && !Name1.getText().toString().trim().isEmpty()){
            Name1.setBackgroundResource(R.drawable.editboder);
            Name2.setBackgroundResource(R.drawable.editboder);
            processName();
            eliminateCommonLetters();
            flamesCalculator();
            displayFlames();
            resetValues();

        }
        else {
            Name1.setBackgroundResource(R.drawable.noteditbod);
            Name2.setBackgroundResource(R.drawable.noteditbod);

        }



    }
    public void resetValues() {
        FLAMES = getString(R.string.FLAMES);
        arr_FLAMES = FLAMES.toCharArray();
    }

    public void resetbtnimg() {
        FLAMES = getString(R.string.FLAMES);
        arr_FLAMES = FLAMES.toCharArray();
        Name1.setText("");
        Name2.setText("");
        text_flamesResult.setText("");
    }



    public void processName() {
        yourNameDisplay = Name1.getText().toString().trim();
        yourName = yourNameDisplay.toLowerCase().replaceAll("\\s+", "");
        arr_yourName = yourName.split("");

        partnerNameDisplay = Name2.getText().toString().trim();
        partnerName = partnerNameDisplay.toLowerCase().replaceAll("\\s+", "");
        arr_partnerName = partnerName.split("");
    }

    public void eliminateCommonLetters() {
        for (String i : arr_yourName) {
            for (String j : arr_partnerName) {
                if ( i.equals(j) ) {
                    arr_yourName = ArrayUtils.removeAll(arr_yourName, i);
                    arr_partnerName = ArrayUtils.removeAll(arr_partnerName, j);
                    break;
                }
            }
        }
        int name11 = arr_yourName.length;
        int name22 = arr_partnerName.length;
        arr_completeName = new String[name11+name22];
        System.arraycopy(arr_yourName,0,arr_completeName,0,name11);
        System.arraycopy(arr_partnerName,0,arr_completeName,name11,name22);

        StringBuilder stringBuilder = new StringBuilder();
        for (String string : arr_completeName) {
            stringBuilder.append(string);
        }

        completeName = stringBuilder.toString();

    }

    public void flamesCalculator() {
        int index, flamesLength = 6;

        while (FLAMES.length() != 1) {
            index = completeName.length() % flamesLength;

            if (index == 0) {
                FLAMES = FLAMES.replace(String.valueOf(arr_FLAMES[FLAMES.length() - 1]), "");
            } else {
                FLAMES = FLAMES.replace(String.valueOf(arr_FLAMES[index - 1]), "");
                FLAMES = FLAMES.substring(index - 1) + FLAMES.substring(0, index - 1);
            }
            arr_FLAMES = FLAMES.toCharArray();

            flamesLength--;
        }

    }

    public void displayFlames(){

        switch(arr_FLAMES[0]){
            case'F':
                text_flamesResult.setText("Friends");
                break;
            case'L':
                text_flamesResult.setText("Love");
                break;
            case'A':
                text_flamesResult.setText("Affection");
                break;
            case'M':
                text_flamesResult.setText("Married");
                break;
            case'E':
                text_flamesResult.setText("Enemies");
                break;
            case'S':
                text_flamesResult.setText("Siblings");
                break;
        }
    }
}
