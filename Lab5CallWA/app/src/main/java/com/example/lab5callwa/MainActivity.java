package com.example.lab5callwa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    Button dialButton;
    Button waButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialButton = (Button) findViewById(R.id.button);
        waButton = (Button) findViewById(R.id.button2);

    }


    public void callPhoneNumber(View view) {
        try {
            if (Build.VERSION.SDK_INT > 22) {
                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //TODO: Consider calling
                    ActivityCompat.requestPermissions(this, new String[]
                            {
                                    Manifest.permission.CALL_PHONE}, 101);
                    return;
                }
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + "0355442000"));
                startActivity(callIntent);
            } else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + "0355442000"));
                startActivity(callIntent);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void waNumber (View view)
    {
        PackageManager packageManager = getPackageManager ();
        Intent i = new Intent(Intent.ACTION_VIEW);
        String message = "Type your custom message here";
        String phone = "+60132121704";
        //put phone number here (Must have whatsapp account)
        try { String url = "https://api.whatsapp.com/send?phone=" + phone + "&text="+ URLEncoder.encode (message, "UTF-8");
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
                startActivity(i);
            }}
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}


