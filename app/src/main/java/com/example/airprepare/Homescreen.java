package com.example.airprepare;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jgabrielfreitas.core.BlurImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;
import me.relex.circleindicator.CircleIndicator;


public class Homescreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public String[] imageurls;
    public Toast t;
    public int i = 0, pl = 0;
    RecyclerView recyclerView;
    public String loc;
    TextView tv;
    BlurImageView blurImageView;
    Button button;
    BlurView blurView;
    public Button ngetlocation;
    public String cur_city;
    int count = 0;
    int t1 = 0;
    android.os.Handler customHandler = new android.os.Handler();
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            ngetlocation.performClick();
            customHandler.postDelayed(this, 60000);
        }
    };
    public ViewPager viewPager;
    Toolbar toolbar;
    CircleIndicator circleIndicator;
    NavigationView nav_draw;
    DatabaseReference databaseReference1;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_homescreen);
        imageurls = new String[]{"https://firebasestorage.googleapis.com/v0/b/airprepare.appspot.com/o/uploads%2F1580376870827.jpg?alt=media&token=6df7e9b2-0a09-4d23-ae07-055f0f490b3f", "https://firebasestorage.googleapis.com/v0/b/airprepare.appspot.com/o/uploads%2F1580376883984.jpg?alt=media&token=7142d928-a10b-4694-a2b7-10ee80adc244", "https://firebasestorage.googleapis.com/v0/b/airprepare.appspot.com/o/uploads%2F1580376890100.jpg?alt=media&token=3ce387e2-0c8c-4b31-a235-c384f0926caa"};
        ArrayList<String> imgUrls = new ArrayList<>();
        imgUrls.add("https://firebasestorage.googleapis.com/v0/b/airprepare.appspot.com/o/uploads%2F1580376870827.jpg?alt=media&token=6df7e9b2-0a09-4d23-ae07-055f0f490b3f");
        imgUrls.add("https://firebasestorage.googleapis.com/v0/b/airprepare.appspot.com/o/uploads%2Fravana.jpg?alt=media&token=b282b618-a89d-47dd-994b-73ed06720a17");
        imgUrls.add("https://firebasestorage.googleapis.com/v0/b/airprepare.appspot.com/o/uploads%2Fthor%20mjolnir.jpg?alt=media&token=f1f9f600-236f-49b1-b807-4bb500897d7a");
        imgUrls.add("https://firebasestorage.googleapis.com/v0/b/airprepare.appspot.com/o/uploads%2Femergeny.jpeg?alt=media&token=2012dbb4-afe5-4176-ab6a-6413b5e1adfb");
        imgUrls.add("https://firebasestorage.googleapis.com/v0/b/airprepare.appspot.com/o/uploads%2Felections.jpg?alt=media&token=7fa1b025-1ea4-4f31-afcb-b05bd65b91d1");
        imgUrls.add("https://firebasestorage.googleapis.com/v0/b/airprepare.appspot.com/o/uploads%2Felections1.jpg?alt=media&token=fe340a12-0914-46ac-93e1-d4370f2c3462");
        imgUrls.add("https://firebasestorage.googleapis.com/v0/b/airprepare.appspot.com/o/uploads%2Fbundh.jpg?alt=media&token=8306ec27-8cb0-4249-bc75-8cd37d3ca320");
        imgUrls.add("https://firebasestorage.googleapis.com/v0/b/airprepare.appspot.com/o/uploads%2Ffloods.png?alt=media&token=1a3bbaa5-aad9-4843-8151-9d90c78f5405");
        super.onCreate(savedInstanceState);
        toolbar = findViewById(R.id.toolbar1);

        setSupportActionBar(toolbar);
        nav_draw = findViewById(R.id.nvView);
        nav_draw.setNavigationItemSelectedListener(this);
        t = new Toast(this);
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("FLIGHTS");
        /*
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.floods), 5000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.elections), 5000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bundh), 5000);
        animationDrawable.setOneShot(false);
        animationDrawable.start();*/
        blurView = findViewById(R.id.blurview);
        //   viewPager = findViewById(R.id.viewpager);
        //  ImageAdapter adapter = new ImageAdapter(this);
        ImageListAdapter adapter = new ImageListAdapter(this, imageurls);
      /*  viewPager.setAdapter(adapter);
        circleIndicator = findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
          public void run() {
                if (i == 3) {
                    i = 0;
                }
                 viewPager.setCurrentItem(i++, true);
            }
        };*/
        final GestureDetector fab = new GestureDetector(this, new fab1());
        final GestureDetector tapGestureDetector = new GestureDetector(this, new TapGestureListener());
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if (pl != 0)
                    blurImageView.setVisibility(View.VISIBLE);
                blurbackground1();
                t1 = 12;
                return false;
            }
        });
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new recycler_view(this, imgUrls));

   /*     viewPager.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (tapGestureDetector.onTouchEvent(event))
                    Toast.makeText(Homescreen.this, "Welcome", Toast.LENGTH_SHORT).show();
                else {
                    if (viewPager.getCurrentItem() == 0) {
                        Toast.makeText(Homescreen.this, "pressed 1", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(Homescreen.this, "pressed 2", Toast.LENGTH_SHORT).show();
                }
                if (tapGestureDetector.onGenericMotionEvent(event)) {
                    Toast.makeText(Homescreen.this, "Clicked", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

        });
        viewPager.setFadingEdgeLength(200);
        viewPager.setPageTransformer(false, new PageTransformer());
        viewPager.setPageMargin(20);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(80, 20, 80, 20);

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 10000, 10000);
*/

        button = findViewById(R.id.button);

        ngetlocation = findViewById(R.id.getLocation);
        customHandler.postDelayed(updateTimerThread, 0);
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new HomeFragment()).commit();
            nav_draw.setCheckedItem(R.id.first);
        }

    }



    public void logout(View view) {
        Intent intent1 = new Intent(Homescreen.this, MainActivity.class);
        startActivity(intent1);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getLocation(View view) {

        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
        } else {
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            try {
                String city = location1(location.getLatitude(), location.getLongitude());
                cur_city = city;
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
            }
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.announcement)
                .setContentTitle("hello")
                .setContentText("welcome to " + cur_city);
        notificationManager.notify(notificationId, mBuilder.build());

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                try {
                    String city = location1(location.getLatitude(), location.getLongitude());
                    tv.setText(city);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == 100) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("+919848833207", null, loc, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again.", Toast.LENGTH_LONG).show();
        }
    }


    private String location1(double lat, double lon) {
        String cityname = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(lat, lon, 10);
            if (addresses.size() > 0) {
                for (Address adr : addresses) {
                    if (adr.getLocality() != null && adr.getLocality().length() > 0) {
                        cityname = adr.getLocality() + "count=" + count;
                        count++;
                        List<Address> list = geocoder.getFromLocation(lat, lon, 1);
                        Address address = list.get(0);
                        loc = address.getAddressLine(0);
                        loc = loc + "http://maps.google.com/maps?saddr=" + lat + "," + lon + "\n" + adr.getPremises() + "\n" + adr.getFeatureName() + "\n" + adr.getSubLocality() + "\n" + adr.getLocality() + "\n" + adr.getSubAdminArea() + "\n" + adr.getAdminArea() + "\n" + adr.getCountryName();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityname;
    }

    public void cabs(View view) {/*
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.olacabs.customer");
        if (launchIntent != null)
            startActivity(launchIntent);
        else {

            String URL = "https://www.olacabs.com/";
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            builder.addDefaultShareMenuItem();
            customTabsIntent.launchUrl(this, Uri.parse(URL));
*/
        Intent i12 = new Intent(this, flight_details.class);
        startActivity(i12);


    }
    

    public void rooms(View view) {
        Intent i = getPackageManager().getLaunchIntentForPackage("com.oyo.consumer");
        if (i != null)
            startActivity(i);
        else {
            String URL = "https://www.oyorooms.com/";
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            builder.addDefaultShareMenuItem();
            customTabsIntent.launchUrl(this, Uri.parse(URL));
        }
    }

    public void hospitals(View view) {
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:9290645115"));
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 10);
        } else {
            startActivity(i);
        }

    }

    public void police(View view) {
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:9182933505"));
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 11);
        } else {
            startActivity(i);
        }
    }


    public void Sms(View view) {
        Toast.makeText(this, "SMS", Toast.LENGTH_SHORT).show();
    }

    public void sendlocationSms() {
        if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 100);

        } else {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("+919848833207", null, loc, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void imageslider1(View view) {
        if (viewPager.getCurrentItem() == 0)
            Toast.makeText(this, "pressed 1", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "pressed 2", Toast.LENGTH_SHORT).show();
    }

    public void Alarm(View view) {
        Intent intent = new Intent(this, Alarm.class);
        startActivity(intent);
    }

    public void blur(View view) {
        if (t1 == 0)
            Toast.makeText(this, "press and hold long for emergency", Toast.LENGTH_SHORT).show();
    }

    private void blurbackground1() {
        pl = 1;
        blurImageView = findViewById(R.id.myblur);
        Drawable d = getDrawable(R.drawable.blur);
        blurImageView.setImageDrawable(d);
        blurImageView.setClickable(true);
        blurImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        blurImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    String x = String.valueOf(event.getX());
                    Toast.makeText(Homescreen.this, x, Toast.LENGTH_SHORT).show();

                    blurImageView.setVisibility(View.INVISIBLE);
                }
                return true;
            }
        });

    }

    private void blurbackground() {


        float radius = 20f;

        View decorView = getWindow().getDecorView();
        //ViewGroup you want to start blur from. Choose root as close to BlurView in hierarchy as possible.
        ViewGroup rootView = decorView.findViewById(android.R.id.content);
        //Set drawable to draw in the beginning of each blurred frame (Optional).
        //Can be used in case your layout has a lot of transparent space and your content
        //gets kinda lost after after blur is applied.
        Drawable windowBackground = getResources().getDrawable(R.drawable.bundh, getApplicationContext().getTheme());

        blurView.setupWith(rootView)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new RenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);
        button.setVisibility(View.VISIBLE);

    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_acc1:
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new AboutFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_acc:
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new HelpFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_acc3:
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new SetFragment()).addToBackStack(null).commit();
                break;
            case R.id.first:
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new HomeFragment()).commit();
                nav_draw.setCheckedItem(R.id.first);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

    public void getImage(View view) {

    }


}
