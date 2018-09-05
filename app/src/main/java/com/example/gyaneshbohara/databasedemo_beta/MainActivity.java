package com.example.gyaneshbohara.databasedemo_beta;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gyaneshbohara.databasedemo_beta.data.DataContract;
import com.example.gyaneshbohara.databasedemo_beta.data.DataTableCreation;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button save=(Button)findViewById(R.id.btn_save);
        Button get=(Button)findViewById(R.id.btn_getdata);
        Button specific=(Button)findViewById(R.id.btn_getspecific);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveData();
            }
        });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getData();
            }
        });

        specific.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSpecific();
            }
        });
    }


    public void saveData(){

        EditText idInfo=(EditText)findViewById(R.id.et_id);

        EditText fileName=(EditText)findViewById(R.id.et_file_name);

        EditText size=(EditText)findViewById(R.id.et_total_size);

        EditText remaining=(EditText)findViewById(R.id.et_total_size);

        EditText flag=(EditText)findViewById(R.id.et_truefalse);


        try {

            DataTableCreation dataTableCreation = new DataTableCreation(MainActivity.this);

            dataTableCreation.insertDownloadInforamtion(Integer.parseInt(idInfo.getText().toString()),"1","2018/10/15",fileName.getText().toString(),size.getText().toString(),remaining.getText().toString(),flag.getInputType());


        }catch (Exception ex){

            Toast.makeText(MainActivity.this,"Error "+ex,Toast.LENGTH_LONG).show();


        }

    }


    public void deleteAll(View view){

        try {
            DataTableCreation dataTableCreation = new DataTableCreation(MainActivity.this);

            dataTableCreation.deleteAll();


        }catch (Exception ex){
            ex.printStackTrace();
        }



    }


    public void getData(){


        try{
            DataTableCreation dataTableCreation = new DataTableCreation(MainActivity.this);

            Cursor c=dataTableCreation.getAllInformation(MainActivity.this);


            if (c.moveToFirst()) {

                    String name = c.getString(c.getColumnIndex(DataContract.DataEntry.COLUMN_FILE_NAME));


                Toast.makeText(MainActivity.this,name,Toast.LENGTH_LONG).show();

            }





        }catch (Exception ex){

            Toast.makeText(MainActivity.this,"Error"+ex.toString(),Toast.LENGTH_LONG).show();
        }



    }


    public void getSpecific(){


        EditText idInfo=(EditText)findViewById(R.id.et_id_get);
        EditText name=(EditText)findViewById(R.id.name);



        try {
            DataTableCreation dataTableCreation = new DataTableCreation(MainActivity.this);


            String value=dataTableCreation.getColumnInfo(MainActivity.this,Integer.parseInt(idInfo.getText().toString()),name.getText().toString());

            Toast.makeText(MainActivity.this, "file :"+value, Toast.LENGTH_SHORT).show();

        }catch (Exception ex){
            Toast.makeText(MainActivity.this,"Error"+ex,Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }


    }

}
