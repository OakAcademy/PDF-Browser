package com.example.pdfbrowserapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class PDFActivity extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfactivity);

        pdfView = findViewById(R.id.pdfView);
        Uri uri = Uri.parse(getIntent().getStringExtra("uri"));
        pdfView.fromUri(uri).load();

    }
}