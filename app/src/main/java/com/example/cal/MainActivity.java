package com.example.cal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }
    public void onButtonPress(View view){
        Button btn = (Button) view;
        String btnText = btn.getText().toString();
        if(btnText.equals("C")) {
            Cal.setFirstNum("");
            Cal.setSecondNum("");
            Cal.setLastOperation("");
            Cal.setLastInputWasOperation(false);
        } else if (Cal.canUseOperation()) {
            handleOperations(btnText);
            Cal.setLastInputWasOperation(true);
        }
        updateDisplays();
    }
    void handleOperations(String operation) {
        boolean usedOperatorInsteadOfEqual = false;
        switch (operation) {
            case "=" :
                if (Cal.getSecondNum().isEmpty()) break;
                int first = Integer.parseInt(Cal.getFirstNum());
                int second = Integer.parseInt(Cal.getSecondNum());

                switch(Cal.getLastOperation()) {
                    case "+":
                        first += second;
                        break;
                    case "-":
                        first -= second;
                        break;
                    case "*":
                        first *= second;
                        break;
                    case "÷":
                        first /= second;
                        break;
                }
                Cal.setFirstNum(String.valueOf(first));
                Cal.setSecondNum("");
                if (!usedOperatorInsteadOfEqual) {
                    Cal.setLastOperation("");
                }
                break;
            case "." :
                break;
            default:
                usedOperatorInsteadOfEqual = !Cal.getSecondNum().isEmpty();
                handleOperations("=");
                Cal.setLastOperation(operation);
                break;

        }
    }
    public void onNumberPress(View view) {
        Button btn = (Button) view;
        String btnText = btn.getText().toString();

        if(!Cal.getLastOperation().isEmpty()) {
            Cal.setSecondNum(Cal.getSecondNum() + btnText);
        }else {
            Cal.setFirstNum(Cal.getFirstNum() + btnText);
        }
        Cal.setLastInputWasOperation(false);
        updateDisplays();
    }
    void updateDisplays() {
        TextView display0 = (findViewById(R.id.txtDisplay0));
        TextView display1 = (findViewById(R.id.txtDisplay1));
        if (!Cal.getSecondNum().isEmpty()) {
            display0.setText(String.format("%s %s", Cal.getFirstNum(),Cal.getLastOperation()));
            display1.setText(Cal.getSecondNum());
        }else {
            display0.setText(Cal.getLastOperation());
            display1.setText(Cal.getFirstNum());
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Continue", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "app get stop", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "restarting", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "app closed", Toast.LENGTH_SHORT).show();
    }

}
