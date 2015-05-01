package mx.cinvestav.edu.cinveslocatorclient;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
/**
 * Created by Celeste on 4/27/15.
 */
public class MapaFragment extends Fragment {
    private View rootview;
    private ImageView imageView;
    private Drawable original;
    private Bitmap originalBitmap;
    private Bitmap mapa;
    private int planta;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_map ,container, false);
        planta = getArguments().getInt("mapa",1);
        int res = R.mipmap.mapa1;
        if( planta == 2 ){
            res = R.mipmap.ic_launcher;
        }
        original = getResources().getDrawable(res);
        originalBitmap= BitmapFactory.decodeResource(getResources(), res);
        mapa = originalBitmap;
        imageView = (ImageView) rootview.findViewById(R.id.imageViewMap);
        imageView.setImageDrawable(getResources().getDrawable(res));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return rootview;
    }
    public Bitmap getOriginalBitmap(){
        return this.originalBitmap;
    }
    public void setMapa(Bitmap mapa){
        this.mapa = mapa;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
