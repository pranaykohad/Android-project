package com.example.pranaykohad.qrattendance;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Teacher extends AppCompatActivity {


    Button btn1,btn2,btn3;
    ListView lv1;
    static ArrayList al = new ArrayList();
    ArrayAdapter aa;

    final Activity a = this;
    IntentIntegrator ii = new IntentIntegrator(a);


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();

        try {
            FileInputStream fis = openFileInput("file2");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String msg;

            while((msg=br.readLine()) != null){
                sb.append(msg);
                sb.append("hi");
            }

            String d1 = getdate1();
            sb1.append(d1);

            sb.append(d1);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        switch (item.getItemId()){
            case R.id.f1:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,sb.toString());
                i.putExtra(Intent.EXTRA_SUBJECT,sb1.toString());
                startActivity(Intent.createChooser(i,"share...."));



                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.folder1, menu);

        MenuItem item = menu.findItem(R.id.f1);

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        btn1 = (Button)findViewById(R.id.button6);
        btn2 = (Button)findViewById(R.id.button7);
        btn3 = (Button)findViewById(R.id.button8);

        btn1.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        ii.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                        Toast.makeText(getApplicationContext(), "you can start scan", Toast.LENGTH_SHORT).show();
                        ii.setCameraId(0);
                        ii.setBeepEnabled(true);
                        ii.setBarcodeImageEnabled(false);
                        ii.initiateScan();
                    }
                }
        );


        btn2.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        if(aa != null) {
                            aa.clear();
                            Toast.makeText(getApplicationContext(), "result is clear", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "result in already clear", Toast.LENGTH_SHORT).show();

                    }
                }
        );

        btn3.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        if(al!=null) {

                            String save_date = al.toString();

                            try {
                                FileOutputStream fos = openFileOutput("file2", MODE_PRIVATE);
                                fos.write((save_date + " ").getBytes());

                                fos.close();

                                Toast.makeText(getApplicationContext(), "data is saved", Toast.LENGTH_SHORT).show();

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                            else
                            Toast.makeText(getApplicationContext(), "no data found!!", Toast.LENGTH_LONG).show();

                    }
                }
        );



    }

    private String getdate1() {

        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yy  EEE  hh:mm a");
        String d = sdf.format(date);
        return d;

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult ir = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(ir.getContents() == null)
            Toast.makeText(getApplicationContext(), "scaning is done", Toast.LENGTH_LONG).show();


        else{
            lv1 = (ListView)findViewById(R.id.listv);

            aa = new ArrayAdapter(this,android.R.layout.simple_list_item_1,al);

            al.add(ir.getContents().toString());



            lv1.setAdapter(aa);

            ii.initiateScan();

        }



        super.onActivityResult(requestCode, resultCode, data);
    }
}
