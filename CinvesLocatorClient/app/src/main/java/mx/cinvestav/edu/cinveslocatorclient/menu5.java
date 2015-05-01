package mx.cinvestav.edu.cinveslocatorclient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import mx.cinvestav.edu.cinveslocatorclient.RPC.client.PointsProvider;
import mx.cinvestav.edu.cinveslocatorclient.RPC.common.MapResource;

/**
 * Created by Celeste on 25/04/2015.
 */
public class menu5 extends Fragment {
    View rootview;
    ImageView imageView;
    Bitmap mapa;
    Timer timer;
    TimerTask timerTask;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menu5, container, false);
        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
          //onResume we start our timer so it can start when the app comes from the background
        startTimer();
    }
    public void startTimer() {
        //set a new Timer
        timer = new Timer();
        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 1000, 1000); //
    }

    @Override
    public void onPause() {
        super.onPause();
        stoptimertask(rootview);
    }

    public void stoptimertask(View v) {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        imageView = (ImageView) getView().findViewById(R.id.imageView6);
                        //Create bitmap from mapa
                        Bitmap img = BitmapFactory.decodeResource(getResources(), R.mipmap.mapa1);
                        //Create a new bitmap where we will merge the image and the circle
                        Bitmap bitmap = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.RGB_565);
                        //create canvas for bitmap
                        Canvas c = new Canvas(bitmap);
                        Paint paint = new Paint();
                        paint.setColor(Color.BLUE);
                        paint.setStrokeWidth(5.0f);
                        // imageView.addt  ----------
                        //-----------------------------------------------------------AQUI VOY
                        c.drawBitmap(img, 0, 0, null);
                        ArrayList<MapResource> puntos = PointsProvider.getInstance().getAll();
                        for (MapResource mr : puntos) {
                            if (mr.getType().equals("persona")) {
                                paint.setColor(Color.BLUE);
                            }
                            if (mr.getType().equals("impresora")) {
                                paint.setColor(Color.GREEN);
                            }
                            if (mr.getType().equals("laboratorio")) {
                                paint.setColor(Color.MAGENTA);
                            }
                            c.drawCircle(mr.getPosition().getX(), mr.getPosition().getY(), 25.0F, paint);
                        }
                        //Now scale to imgView
                        mapa = bitmap;
                        //Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, imageView.getWidth(), imageView.getHeight(), false);
                        imageView.setAdjustViewBounds(true);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        imageView.setImageBitmap(mapa);
                    }
                });
            }
        };
    }

    public void onActivityCreated(Bundle savedInstanceState){
        // setView(R.layout.activity_login);
        super.onActivityCreated(savedInstanceState);
        // Find the imageview
        imageView = (ImageView) getView().findViewById(R.id.imageView6);
        //Create bitmap from mapa
        Bitmap img = BitmapFactory.decodeResource(getResources(), R.mipmap.mapa1);
        //Create a new bitmap where we will merge the image and the circle
        Bitmap bitmap = Bitmap.createBitmap(img.getWidth(),img.getHeight(), Bitmap.Config.RGB_565);
        //create canvas for bitmap
        Canvas c = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5.0f);
        // imageView.addt  ----------
        //-----------------------------------------------------------AQUI VOY
        c.drawBitmap(img, 0, 0, null);
        ArrayList<MapResource> puntos = PointsProvider.getInstance().getAll();
        for(MapResource mr : puntos) {
            if(mr.getType().equals("persona")){
                paint.setColor(Color.BLUE);
            }
            if(mr.getType().equals("impresora")){
                paint.setColor(Color.GREEN);
            }
            if(mr.getType().equals("laboratorio")){
                paint.setColor(Color.MAGENTA);
            }
            c.drawCircle(mr.getPosition().getX(), mr.getPosition().getY(), 25.0F, paint);
        }
        //Now scale to imgView
        mapa = bitmap;
        //Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, imageView.getWidth(), imageView.getHeight(), false);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageBitmap(mapa);

    }
}
