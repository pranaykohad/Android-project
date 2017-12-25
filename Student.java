package com.example.pranaykohad.qrattendance;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Student extends AppCompatActivity {

    Button btn;
    ImageView iv1;

    TextView tv1;


    MultiFormatWriter reader = new MultiFormatWriter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);



        btn = (Button)findViewById(R.id.button);
        tv1 = (TextView)findViewById(R.id.textView7);
        iv1 = (ImageView)findViewById(R.id.imageView);


        try {
            FileInputStream fis = openFileInput("file4");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String msg;

            while((msg=br.readLine()) != null){
                sb.append(msg);
            }

            tv1.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            String stu1 = tv1.getText().toString();

            BitMatrix bm = reader.encode(stu1, BarcodeFormat.QR_CODE,1000,1000);

            BarcodeEncoder be = new BarcodeEncoder();
            Bitmap b = be.createBitmap(bm);
            iv1.setImageBitmap(b);
        }
        catch (WriterException e) {
            e.printStackTrace();
        }



        btn.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(Student.this,stu_reg.class);
                        startActivity(i);


                    }
                }
        );
    }
}
