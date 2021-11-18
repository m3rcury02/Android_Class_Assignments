package com.example.fileEditor;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class ExternalFiles extends AppCompatActivity {

    private EditText editTextFileName, editTextFolderName, editTextContent;
    private Button buttonDeleteFile, buttonWriteFile, buttonReadFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external_files);
        initView();
        assignPermission();
    }

    private void assignPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    private void initView() {
        editTextFileName = findViewById(R.id.editTextFileName);
        editTextFolderName = findViewById(R.id.editTextFolderName);
        editTextContent = findViewById(R.id.editTextContent);
        buttonReadFile = findViewById(R.id.buttonReadFile);
        buttonReadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonReadFile_onClick(view);
            }
        });
        buttonWriteFile = findViewById(R.id.buttonWriteFile);
        buttonWriteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonWriteFile_onClick(view);
            }
        });
        buttonDeleteFile = findViewById(R.id.buttonDeleteFile);
        buttonDeleteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonDeleteFile_onClick(view);
            }
        });
    }

    private void buttonReadFile_onClick(View view) {
        try {
            if (checkExternalMedia()) {
                File sdCard = Environment.getExternalStorageDirectory();
                File dir = new File(sdCard.getAbsolutePath() + File.separator + editTextFolderName.getText().toString() + File.separator);
                File file = new File(dir, editTextFileName.getText().toString());
                StringBuilder result = new StringBuilder();
                String line = "";
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                editTextContent.setText(result.toString());
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.unavailable), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void buttonWriteFile_onClick(View view) {
        try {
            if (checkExternalMedia()) {
                File sdCard = Environment.getExternalStorageDirectory();
                File dir = new File(sdCard.getAbsolutePath() + File.separator + editTextFolderName.getText().toString() + File.separator);
                dir.mkdirs();
                File file = new File(dir, editTextFileName.getText().toString());
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(editTextContent.getText().toString().getBytes());
                fileOutputStream.close();
                Toast.makeText(getApplicationContext(), getString(R.string.done), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.unavailable), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void buttonDeleteFile_onClick(View view) {
        new AlertDialog.Builder(this).setTitle("Deleting File").setMessage("Are you sure you want to delete the file?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            if (checkExternalMedia()) {
                                File sdCard = Environment.getExternalStorageDirectory();
                                File dir = new File(sdCard.getAbsolutePath() + File.separator + editTextFolderName.getText().toString() + File.separator);
                                File file = new File(dir, editTextFileName.getText().toString());
                                if (file.exists()) {
                                    file.delete();
                                }
                                Toast.makeText(ExternalFiles.this, "File Deleted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), getString(R.string.unavailable), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }).setNegativeButton("No", null).show();
    }


    private boolean checkExternalMedia() {
        boolean mExternalStorageAvailable, mExternalStorageWriteable;
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }
        return mExternalStorageAvailable && mExternalStorageWriteable;
    }

    public void ToIntFiles(View v) {
        startActivity(new Intent(this, InternalFiles.class));
    }

}