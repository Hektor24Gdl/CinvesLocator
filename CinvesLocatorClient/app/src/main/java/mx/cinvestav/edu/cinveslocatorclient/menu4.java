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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Celeste on 25/04/2015.
 */
public class menu4 extends Fragment {
    View rootview;
    ImageView imageView;
    Spinner sp;
    Bitmap mapaOriginal;
    Bitmap mapa;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menu4, container, false);

        return rootview;
    }
    public void onActivityCreated(Bundle savedInstanceState){
        // setView(R.layout.activity_login);
        super.onActivityCreated(savedInstanceState);
        // Find the imageview
        sp = (Spinner) getView().findViewById(R.id.spinner);
        sp.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String item = (String)parent.getItemAtPosition(position);
                        item = item.toLowerCase();
                        Bitmap bitmap = Bitmap.createBitmap(mapaOriginal.getWidth(),mapaOriginal.getHeight(), Bitmap.Config.RGB_565);
                        //create canvas for bitmap
                        Canvas c = new Canvas(bitmap);
                        Paint paint = new Paint();
                        paint.setColor(Color.BLUE);
                        paint.setStrokeWidth(5.0f);
                        // imageView.addt  ----------
                        //-----------------------------------------------------------AQUI VOY
                        c.drawBitmap(mapaOriginal, 0, 0, null);
                        ArrayList<MapResource> puntos = PointsProvider.getAll(item);
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
                            c.drawCircle(mr.getPosition().x, mr.getPosition().y, 25.0F, paint);
                        }
                        mapa=bitmap;
                        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, imageView.getWidth(), imageView.getHeight(), false);
                        imageView.setImageDrawable(new BitmapDrawable(getResources(), scaledBitmap));

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Bitmap scaledBitmap = Bitmap.createScaledBitmap(mapaOriginal, imageView.getWidth(), imageView.getHeight(), false);
                        imageView.setImageDrawable(new BitmapDrawable(getResources(), scaledBitmap));

                    }
                }
        );
        imageView = (ImageView) getView().findViewById(R.id.imageView5);
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
        //Now scale to imgView
        mapa = bitmap;
        mapaOriginal = bitmap;
        //Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, imageView.getWidth(), imageView.getHeight(), false);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout(){
                try {
                    ImageView imageView = (ImageView) getView().findViewById(R.id.imageView5);
                    Drawable img = imageView.getDrawable();
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(mapa, imageView.getWidth(), imageView.getHeight(), false);
                    imageView.setImageDrawable(new BitmapDrawable(getResources(), scaledBitmap));
                }catch(Exception ex){

                }
            }
        });
    }
}
