package mx.cinvestav.edu.cinveslocatorclient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import mx.cinvestav.edu.cinveslocatorclient.RPC.client.PointsProvider;
import mx.cinvestav.edu.cinveslocatorclient.RPC.common.MapResource;

/**
 * Created by Celeste on 25/04/2015.
 */
public class menu2 extends Fragment {
    View rootview;
    ImageView imageView;
    Bitmap mapa;
    Point myPosition;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menu2, container, false);
        return rootview;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        // setView(R.layout.activity_login);
        super.onActivityCreated(savedInstanceState);
        // Find the imageview
        imageView = (ImageView) getView().findViewById(R.id.imageView3);
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
        MapResource mr = PointsProvider.getInstance().getUbicacion("Celeste");
        c.drawCircle(mr.getPosition().getX(), mr.getPosition().getY(), 25.0F, paint);
        //Now scale to imgView
        mapa = bitmap;
        //Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, imageView.getWidth(), imageView.getHeight(), false);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageBitmap(mapa);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float xTouch = event.getX();
                float yTouch = event.getY();
                float scaleX =  (float)mapa.getWidth() / (float)imageView.getWidth() ;
                float scaleY =   (float)mapa.getHeight() / (float)imageView.getHeight() ;
                xTouch *= scaleX;
                yTouch *= scaleY;
                PointsProvider.getInstance().setUbicacion("Celeste","persona",1,(int)xTouch,(int)yTouch);

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
                MapResource mr = PointsProvider.getInstance().getUbicacion("Celeste");
                c.drawCircle(mr.getPosition().getX(), mr.getPosition().getY(), 25.0F, paint);
                //Now scale to imgView
                mapa = bitmap;
                //Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, imageView.getWidth(), imageView.getHeight(), false);
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setImageBitmap(mapa);

                return true;
            }
        });
    }

}
