package com.example.pdfbrowserapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Uri> fileUriList = new ArrayList<>();
    ArrayList<String> fileNameList = new ArrayList<>();

    RecyclerView recyclerView;
    FileAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new FileAdapter(fileUriList,fileNameList);
        recyclerView.setAdapter(adapter);

        //openFile();
        openDirectory();

    }

    private void openFile() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");

        // Optionally, specify a URI for the file that should appear in the
        // system file picker when it loads.
        //intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri);

        startActivityForResult(intent, 1);
    }

    public void openDirectory() {
        // Choose a directory using the system's file picker.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);

        // Optionally, specify a URI for the directory that should be opened in
        // the system file picker when it loads.
        //intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uriToLoad);

        startActivityForResult(intent, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == 2
                && resultCode == Activity.RESULT_OK) {
            // The result data contains a URI for the document or directory that
            // the user selected.
            Uri uri;
            if (resultData != null) {
                uri = resultData.getData();
                // Perform operations on the document using its URI.

                DocumentFile documentFile = DocumentFile.fromTreeUri(MainActivity.this,uri);
                if(documentFile != null){

                    DocumentFile[] documentFiles = documentFile.listFiles();

                    for (DocumentFile file : documentFiles){

                        String fileName = file.getName();
                        if (fileName != null && fileName.endsWith(".pdf")){

                            fileNameList.add(fileName);
                            fileUriList.add(file.getUri());
                            adapter.notifyDataSetChanged();

                        }


                    }
                }


            }
        }
    }
}