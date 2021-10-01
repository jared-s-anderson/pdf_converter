package com.example.converterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class ConvertActivity extends AppCompatActivity {

    PDFView pdfDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        pdfDisplay = (PDFView) findViewById(R.id.pdf);

        String pathWay = getExternalFilesDir(null).toString()+ "/converted.pdf";
        File f = new File(pathWay);
        pdfDisplay.fromFile(f)
                .load();
    }
}