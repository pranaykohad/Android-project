package com.example.pranaykohad.qrattendance;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class stu_reg extends AppCompatActivity {

    Button btn1,btn2;
    EditText et1,et2;
    String name,roll,std;
    Spinner spin;
    static String my_std;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_reg);




        btn1 = (Button)findViewById(R.id.button3);
        btn2 = (Button)findViewById(R.id.button4);

        et1 = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewById(R.id.editText2);
        spin = (Spinner)findViewById(R.id.spinner);

        et1.setText("");
        et2.setText("");

        String[] c1 = {"select class","MCA-I","MCA-II","MCA-III"};
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item ,c1);
        spin.setAdapter(aa);


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object std1;


                if(spin.getSelectedItem()!="select 1 item") {
                    std1 = spin.getSelectedItem();
                    my_std = std1.toString();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getBaseContext(), "please select 1 item!!!!!!!", Toast.LENGTH_LONG).show();
            }
        });




        btn1.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                            try {


                                name = et1.getText().toString();
                                roll = et2.getText().toString();
                                std = my_std;



                                FileOutputStream fos = openFileOutput("file4", MODE_PRIVATE);


                                fos.write((name + " ").getBytes());
                                fos.write((roll + "  ").getBytes());
                                fos.write((std).getBytes());
                                fos.close();

                                et1.setText("");
                                et2.setText("");


                                Toast.makeText(getApplicationContext(), "msg is saved", Toast.LENGTH_SHORT).show();

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Intent i = new Intent(stu_reg.this, Student.class);
                            startActivity(i);

                    }
                }
        );

        btn2.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {

                        et1.setText("");
                        et2.setText("");


                    }
                }
        );

    }
}
