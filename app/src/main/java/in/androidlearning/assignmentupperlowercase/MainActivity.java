package in.androidlearning.assignmentupperlowercase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText edtTxtName;
    private Button btnSend;
    private TextView updatedText;
    private TextView newText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inThisView();
        btnSend.setOnClickListener(new OnStartBtnClickListener());

    }

    private void inThisView(){
        edtTxtName=findViewById(R.id.edtTxtName);
        btnSend=findViewById(R.id.btnSend);
        updatedText = findViewById(R.id.updatedText);
        newText = findViewById(R.id.newText);
    }

    private class OnStartBtnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent mainActIntent = new Intent(MainActivity.this, SecondActivity.class);
            mainActIntent.putExtra("EnteredName",edtTxtName.getText().toString());
            startActivityForResult(mainActIntent,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null){
            return;
        }

        String updatedStringFromSecAct = data.getStringExtra("UpdatedName");
        newText.setText(updatedStringFromSecAct);
       edtTxtName.setText(updatedStringFromSecAct);

    }

}