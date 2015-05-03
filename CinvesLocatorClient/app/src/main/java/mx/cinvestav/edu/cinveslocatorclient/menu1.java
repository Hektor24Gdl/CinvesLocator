package mx.cinvestav.edu.cinveslocatorclient;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import mx.cinvestav.edu.cinveslocatorclient.RPC.client.PointsProvider;
import mx.cinvestav.edu.cinveslocatorclient.RPC.common.MapResource;

import mx.cinvestav.edu.cinveslocatorclient.webservice.*;

import java.util.StringTokenizer;

/**
 * Created by Celeste on 25/04/2015.
 */
public class menu1 extends Fragment {
    View rootview;
    ImageView imageView;
    Bitmap mapa;
    Point myPosition;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menu1, container, false);
        return rootview;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        // setView(R.layout.activity_login);
        super.onActivityCreated(savedInstanceState);
        // Find the imageview
        imageView = (ImageView) getView().findViewById(R.id.imageView2);
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

        TGVCinvesLocatorPortBinding port = new TGVCinvesLocatorPortBinding();

        try{
            TGVlocation location = port.getLastLocation("Celeste");
            System.out.println(location.coordinates);
            StringTokenizer tokens = new StringTokenizer(location.coordinates, ",");
            tokens.nextToken();
            float x = Float.parseFloat(tokens.nextToken());
            float y = Float.parseFloat(tokens.nextToken());
            c.drawCircle(x, y, 25.0F, paint);
        }catch(Exception ex){
            System.err.println(ex);
        }

        //Now scale to imgView
        mapa = bitmap;
        //Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, imageView.getWidth(), imageView.getHeight(), false);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageBitmap(mapa);
    }


}
