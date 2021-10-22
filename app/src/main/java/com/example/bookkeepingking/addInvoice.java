package com.example.bookkeepingking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookkeepingking.R;
import java.util.Calendar;

public class addInvoice extends AppCompatActivity implements View.OnClickListener {
   private static final String TAG="date";
    //for camera
    Button photo;
    ImageView currentInvoice;
    private static final int PERMISSION_CODE=100;
    private static final int IMAGE_CAPTURE_CODE=1001;
    Uri image_uri;

    //for date
    private TextView DisplayDate;
    private DatePickerDialog.OnDateSetListener DateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invoice);

        //camera
        currentInvoice = findViewById(R.id.invoiceTaken);
        photo = (Button) findViewById(R.id.fileUpload);

        //photo button clicked
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if system os is>= marshmallow, request runtime position
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) ==
                            PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED) {
                        //request permission if not given
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        //show popup requesting permission
                        requestPermissions(permission, PERMISSION_CODE);
                    } else {
                        //permission already granted
                        openCamera();
                    }
                } else {
                    //system os<marshmallow
                    openCamera();
                }
            }
        });
        //date
        DisplayDate=(TextView) findViewById(R.id.date);
        DisplayDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar cal= Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(
                        addInvoice.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        DateSetListener,
                        year,month,day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        //initializing datesetlistener
        DateSetListener=new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker,int year, int month,int day){
                month=month+1;
                Log.d(TAG,"onDateSet: mm/dd/yyyy: "+month+ "/"+day+"/"+year);

                String date=month+"/"+day+"/"+year;
                DisplayDate.setText(date);
            }

        };
    }
    public void openCamera(){
        ContentValues value=new ContentValues();
        value.put(MediaStore.Images.Media.TITLE,"New Picture");
        value.put(MediaStore.Images.Media.DESCRIPTION,"invoice");
        image_uri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,value);
        //Camera intent
        Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(cameraIntent,IMAGE_CAPTURE_CODE);

    }




    //handles permission for camera
    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults){
        //will activate once user presses allow or deny
        switch(requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0]==
                        PackageManager.PERMISSION_GRANTED){
                    //was granted
                    openCamera();

                }
                else{
                    //permission denied
                    Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        //called when image was captured from camera
        if(resultCode==RESULT_OK){
            //set image capture to image view
            currentInvoice.setImageURI(image_uri);

        }
    }

    public void onClick(View view){
        if(view.getId()== R.id.fileUpload){

        }
    }
}