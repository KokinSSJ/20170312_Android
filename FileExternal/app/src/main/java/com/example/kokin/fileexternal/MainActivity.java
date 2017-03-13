package com.example.kokin.fileexternal;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import java.io.*;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.buttonSave)
    Button buttonSave;

    @BindView(R.id.buttonRead)
     Button buttonRead;

    @BindView(R.id.buttonClean)
     Button buttonClean;

    @BindView(R.id.editText) // inna metoda na dodawanie kontrolek
     EditText editText;

    @BindView(R.id.buttonDate)
    Button buttonDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


    }

 //trzeba dodać do pliku MANIFEST.xml -> access danied -> permission
    // np. dla camery, internetu itd
    @OnClick(R.id.buttonSave)
    public void buttonSaveClick() {
        String text = editText.getText().toString();
        File file = null;
        FileOutputStream fos = null;
        try {
            file = new File(Environment.getExternalStorageDirectory(), "appData");
            fos = new FileOutputStream(file);
            fos.write(text.getBytes());
            Log.v("SaveButton", "Saving");

        } catch (Exception ex) {
            Log.e("ButtonSaveERROR", ex.getMessage());

        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception ex2) {
                    Log.e("MyApp", ex2.getMessage());

                }

            }

        }


    }

    @OnClick(R.id.buttonRead)
    public void buttonReadClick() {
        BufferedReader br = null;
        File file = null;
        String line = null;
        StringBuilder sb = new StringBuilder();

        try {
            file = new File(Environment.getExternalStorageState(), "appData");
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            Log.v("Test1", "testuje read");
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            Log.v("Test", sb.toString());
        } catch (Exception ex) {
            Log.e("Ex", ex.getMessage());}
            finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception ex2) {
                    Log.e("MyApp", ex2.getMessage());
                }
            }

        }

        editText.setText(sb.toString());
    }

    @OnClick(R.id.buttonClean)
    public void clearEditText(){
        Log.v("Test", "testuje clean");
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("Are you sure? ")
                .setTitle("Confirm dialog")
                // set positive button
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() { //wyglada jakby tworzonoy był obiekt interfejsu!! ale tak nie ejst!
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editText.getText().clear();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    @OnClick(R.id.buttonDate)
    public void createDate (){
        int position = editText.getSelectionStart();
        editText.getText().insert(position, new Date().toString());
    }

}
