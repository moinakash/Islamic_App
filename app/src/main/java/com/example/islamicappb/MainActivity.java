package com.example.islamicappb;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText ed;
    Button btn;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed = findViewById(R.id.idEd);
        btn = findViewById(R.id.idbtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = ed.getText().toString();

                Toast.makeText(MainActivity.this, ""+data, Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPrefWidget = getSharedPreferences("WidgetMM",0);

                sharedPrefWidget = getSharedPreferences("mm",0);



                SharedPreferences.Editor editorWidget = sharedPrefWidget.edit();
                editorWidget.putString("fw", ""+data);

                editorWidget.commit();
            }
        });




    }
}