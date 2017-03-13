package com.example.kokin.asyncimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView image;

    public final String url = "http://lorempixel.com//480/640/nature/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.imageView);

        new DownloadAsyncTask().execute(url);


        //gdy ktoś kliknie na obrazek to sie pobierze jakiś inny!

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadAsyncTask().execute(url);
            }
        });



    }
    private class DownloadAsyncTask extends AsyncTask<String, Void, Bitmap> { //String bo dla url!


        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            InputStream inputStream = null;
            HttpURLConnection conn = null;
            try{
                URL url = new URL(params[0]);
                conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                //z połączenia pobieramy referencje na obiekt za pomoca którego ściągniemy dane
                inputStream = conn.getInputStream(); //obiekt do obsługi strumienia (bajtów  =danych)
                bitmap = BitmapFactory.decodeStream(inputStream);

            }catch (Exception ex){
                Log.e("appError", ex.getMessage());

            } finally {
                if(inputStream!=null){
                    try {
                        inputStream.close();
                    } catch (Exception e) {
                        Log.e("inputStream", e.getMessage());
                    }
                }
                if(conn!=null){
                    conn.disconnect();
                }

            }

            return bitmap;
        }


        @Override
        public void onPostExecute(Bitmap bitmap){
            super.onPostExecute(bitmap);

            image.setImageBitmap(bitmap);
        }

    }
}
