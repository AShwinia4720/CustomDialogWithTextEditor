package in.androidlearning.assignmentupperlowercase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.TextKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

public class SecondActivity extends Activity {
    private EditText secEdtBox;
    private RadioGroup radioGroup;
    private RadioButton radioUpperCase,radioLowerCase,radioTitleCase,radioReverse;
    private Button btnSecSet,btnSecCancle;
    private String updatedString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        inThisView();
        processInput();
        radioGroup.setOnCheckedChangeListener(new OnRadioOptionClickListener());
        radioUpperCase.setOnClickListener(new OnRadioBtnClickListener());
        radioLowerCase.setOnClickListener(new OnRadioBtnClickListener());
        radioTitleCase.setOnClickListener(new OnRadioBtnClickListener());
        radioReverse.setOnClickListener(new OnRadioBtnClickListener());
        btnSecSet.setOnClickListener(new OnSetClickListener());
        btnSecCancle.setOnClickListener(new OnCancleBtnClickListener());

    }

    private void inThisView(){
        secEdtBox=findViewById(R.id.secEdtBox);
        radioGroup=findViewById(R.id.radioGroup);
        radioUpperCase=findViewById(R.id.radioUpperCase);
        radioLowerCase = findViewById(R.id.radioLowerCase);
        radioTitleCase=findViewById(R.id.radioTitleCase);

        btnSecSet=findViewById(R.id.btnSecSet);
        btnSecCancle =findViewById(R.id.btnSecCancle);
        radioReverse=findViewById(R.id.radioReverse);
    }

    public void processInput(){
        Intent getIntentFromMain = getIntent();
        String name = getIntentFromMain.getStringExtra("EnteredName");
        secEdtBox.setText(name);
    }

//    To change listener on check different radio buttons
    private class OnRadioOptionClickListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            RadioButton radioButton = (RadioButton)radioGroup.findViewById(checkedId);
        }
    }

    private class OnRadioBtnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            int selectedRadioBtnId = radioGroup.getCheckedRadioButtonId();

            if(selectedRadioBtnId==R.id.radioUpperCase){
                updatedString = secEdtBox.getText().toString().toUpperCase();
                secEdtBox.setText(updatedString);
            }

            if(selectedRadioBtnId==R.id.radioLowerCase){
                updatedString=secEdtBox.getText().toString().toLowerCase();
                secEdtBox.setText(updatedString);
            }

            if(selectedRadioBtnId==R.id.radioTitleCase){
                updatedString=secEdtBox.getText().toString();
                char[] chararray = updatedString.toCharArray();
                int len = chararray.length;
                int index =0;
                for(index=0; index<len; index++){
                    if(chararray[index]!=' ' && index==0){
                        chararray[index] = Character.toUpperCase(chararray[index]);
                        continue;
                    }

                    if(chararray[index]==' '){
                        int next = index+1;
                        if(next>=len){
                            break;
                        }
                        chararray[next]=Character.toUpperCase(chararray[next]);
                        index++;
                        continue;
                    }
                    else {
                        chararray[index]=Character.toLowerCase(chararray[index]);

                    }

                }
                String titleCaseString = String.valueOf(chararray);
                secEdtBox.setText((titleCaseString));
                }

            if(selectedRadioBtnId==R.id.radioReverse){
                updatedString=secEdtBox.getText().toString();
                int len = updatedString.length();
                String reversedString = "";
                for(int z=(len-1); z>=0; z--){
                    reversedString=reversedString+updatedString.charAt(z);
                }
                secEdtBox.setText(reversedString);
            }

            }
        }

        private class OnSetClickListener implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                Intent intentSec = new Intent();
                intentSec.putExtra("UpdatedName", secEdtBox.getText().toString());
                setResult(1, intentSec);
                finish();
            }
        }

    }

