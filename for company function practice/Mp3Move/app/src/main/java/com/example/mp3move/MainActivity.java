package com.example.mp3move;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        DirMake();


    }

    public void DirMake(){
        String folderName = "Test";
        String folderPath = context.getFilesDir().toString() + File.separator + folderName;
        File demoDir = new File(folderPath);
        if(!demoDir.exists()) {
            demoDir.mkdir();
        }



        Field[] fileds = R.raw.class.getFields();
        for(int i=0;i<fileds.length;i++) {
            int id = getRawResIdByName(fileds[i].getName());
            String name = fileds[i].getName();

            copy(id,name);
        }
    }

    public void copy(int id, String name){

        String rawFilePath = context.getFilesDir().toString() + File.separator + "Test/" + name;

        try {
            InputStream inputStream = getResources().openRawResource(id);
            FileOutputStream fileOutputStream = new FileOutputStream(rawFilePath);

            byte buf[]=new byte[2048];
            int len;
            while((len=inputStream.read(buf))>0) {
                fileOutputStream.write(buf,0,len);
            }

            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e1) {}

    }

    public int getRawResIdByName(String resName){
        String pkgName = this.getPackageName();
        int resID = this.getResources().getIdentifier(resName,"raw",pkgName);
        return resID;
    }
}
