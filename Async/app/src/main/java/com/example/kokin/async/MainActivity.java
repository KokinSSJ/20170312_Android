package com.example.kokin.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          ButterKnife.bind(this);

        textView.setText("Calculating...");
        new PrimeTask(this, textView).execute(new Integer (5000));

    }
//    @Override
//    public void onResume(){
//
//    }
    private class PrimeTask extends AsyncTask<Integer, Integer, BigInteger>{

          private Context context;
          private TextView textView;
          private ProgressDialog progressDialog;

        public PrimeTask(Context context, TextView textView){
            this.context=context;
            this.textView=textView;

        }

        @Override
        public void onPreExecute(){ //progres dialog!
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Calculating prime number");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.setCancelable(true);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {

                    PrimeTask.this.cancel(false); //zamkniecie procesu obliczeniowego
                }
            });

            progressDialog.show();
        }

        @Override
        public void onPostExecute(BigInteger result){ // po skonczeniu doInBackground

            textView.setText(result.toString());
            progressDialog.dismiss(); //zamyka sie okienko!

        }


        @Override
        protected BigInteger doInBackground(Integer... params) {//wynik!
            int n = params[0];
            BigInteger result = new BigInteger("1");
            int progress = 0;

            for(int i = 0; i<n && !isCancelled(); i++) { //!isCanceled jezeli uzytkownik przerwie w PrimeTask.this.cancel(false);
               int percent = (100*i)/n;
               if(percent>progress){
                   publishProgress(percent); //-> wywołuje onProgressUpdate!
                   progress=percent;
               }
               //obciazający obliczeniowo fragment
                result = result.nextProbablePrime();
            }




            return result;
        }

        @Override
        public void onProgressUpdate(Integer ... vals){ //update porgres bara
            Integer v = vals[0];
            progressDialog.setProgress(v);
        }

        @Override
        public void onCancelled(){
            textView.setText("Canceled");
        } //gdy ktoś kliknie po za oknem i przerwie
    }
}
