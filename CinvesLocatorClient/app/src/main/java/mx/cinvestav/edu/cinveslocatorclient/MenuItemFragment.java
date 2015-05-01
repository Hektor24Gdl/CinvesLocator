package mx.cinvestav.edu.cinveslocatorclient;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import mx.cinvestav.edu.cinveslocatorclient.RPC.common.MapResource;


public class MenuItemFragment extends Fragment{
    View rootview;
    //TabHostDeclaration
    private ViewPager viewPager;
    //Resources needed
    private MapaFragment plantaBajaFragment;
    private MapaFragment plantaAltaFragment;
    private ArrayList<MapaFragment> fragments;
    private PagerAdapter myViewPagerAdapter;
    private int mySection;

    public void drawPoint(MapResource mr){
        int planta = mr.getPlanta();
        MapaFragment target = plantaBajaFragment;
        if(planta == 2 ) {
            target = plantaAltaFragment;
        }
        Bitmap originalBitmap = target.getOriginalBitmap();
        Bitmap resultBitmap = Bitmap.createBitmap(originalBitmap.getWidth(),
                                                  originalBitmap.getHeight(),
                                                  Bitmap.Config.RGB_565);
        Canvas c = new Canvas(resultBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5.0f);
        // imageView.addt  ----------
        //-----------------------------------------------------------AQUI VOY
        c.drawBitmap(originalBitmap, 0, 0, null);
        if (mr.getType().equals("persona")) {
            paint.setColor(Color.BLUE);
        }
        if (mr.getType().equals("impresora")) {
            paint.setColor(Color.GREEN);
        }
        if (mr.getType().equals("laboratorio")) {
            paint.setColor(Color.MAGENTA);
        }
        if( mr.getName().equals("Celeste")){
            paint.setColor(Color.CYAN);
        }
        c.drawCircle(mr.getPosition().getX(), mr.getPosition().getY(), 25.0F, paint);
        target.setMapa(resultBitmap);
    }
    public void drawPoints(ArrayList<MapResource> mresources){
        Bitmap originalBitmapAlta = plantaAltaFragment.getOriginalBitmap();
        Bitmap originalBitmapBaja = plantaBajaFragment.getOriginalBitmap();
        Bitmap resultBitmapAlta = Bitmap.createBitmap(originalBitmapAlta.getWidth(),
                originalBitmapAlta.getHeight(),
                Bitmap.Config.RGB_565);
        Bitmap resultBitmapBaja = Bitmap.createBitmap(originalBitmapBaja.getWidth(),
                originalBitmapBaja.getHeight(),
                Bitmap.Config.RGB_565);

        Canvas cAlta = new Canvas(resultBitmapAlta);
        Canvas cBaja = new Canvas(resultBitmapBaja);
        cAlta.drawBitmap(originalBitmapAlta, 0, 0, null);
        cBaja.drawBitmap(originalBitmapBaja,0,0,null);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5.0f);
        for(MapResource mr : mresources) {

            if (mr.getType().equals("persona")) {
                paint.setColor(Color.BLUE);
            }
            if (mr.getType().equals("impresora")) {
                paint.setColor(Color.GREEN);
            }
            if (mr.getType().equals("laboratorio")) {
                paint.setColor(Color.MAGENTA);
            }
            if( mr.getName().equals("Celeste")){
                paint.setColor(Color.CYAN);
            }
            if( mr.getPlanta() == 1) {
                cBaja.drawCircle(mr.getPosition().getX(), mr.getPosition().getY(), 25.0F, paint);
            }else{
                cAlta.drawCircle(mr.getPosition().getX(), mr.getPosition().getY(), 25.0F, paint);
            }
        }
        plantaBajaFragment.setMapa(resultBitmapBaja);
        plantaAltaFragment.setMapa(resultBitmapAlta);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        rootview = inflater.inflate(R.layout.activity_menu_item,container,false);
        fragments = new ArrayList<MapaFragment>();
        plantaAltaFragment = new MapaFragment();
        plantaBajaFragment = new MapaFragment();
        Bundle arg1 = new Bundle();
        arg1.putInt("mapa", 1);
        Bundle arg2 = new Bundle();
        arg2.putInt("mapa",2);
        plantaBajaFragment.setArguments(arg1);
        plantaAltaFragment.setArguments(arg2);
        fragments.add(plantaBajaFragment);
        fragments.add(plantaAltaFragment);
        viewPager = (ViewPager) rootview.findViewById(R.id.pager);
        viewPager.setAdapter(new ActionTabsViewPagerAdapter(getChildFragmentManager(), fragments));
        return rootview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager = (ViewPager)rootview.findViewById(R.id.pager   );
        mySection = getArguments().getInt("SECTION_NUMBER");
        // create a fragment list in order.

        // use FragmentPagerAdapter to bind the slidingTabLayout (tabs with different titles) and ViewPager (different pages of fragment) together.
        myViewPagerAdapter =new ActionTabsViewPagerAdapter(getChildFragmentManager(),
                fragments);
        viewPager.setAdapter(myViewPagerAdapter);
    }

    public void onAttach(   Activity activity) {
        super.onAttach(activity);
        ((NavigationActivity) activity).onSectionAttached(getArguments().getInt("SECTION_NUMBER"));
    }

}

/**
 * Created by Fanglin Chen on 12/18/14.
 */

class ActionTabsViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<MapaFragment> fragments;

    public static final int MAP_1 = 0;
    public static final int MAP_2 = 1;
    public static final String UI_MAP_1= "Planta Baja";
    public static final String UI_MAP_2= "Planta Alta";

    public ActionTabsViewPagerAdapter(FragmentManager fm, ArrayList<MapaFragment> fragments){
        super(fm);
        this.fragments = fragments;
    }

    public Fragment getItem(int pos){
        return fragments.get(pos);
    }

    public int getCount(){
        return fragments.size();
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case MAP_1:
                return UI_MAP_1;
            case MAP_2:
                return UI_MAP_2;
            default:
                break;
        }
        return null;
    }
}