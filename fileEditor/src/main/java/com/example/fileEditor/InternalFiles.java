package com.example.fileEditor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class InternalFiles extends AppCompatActivity {
    private EditText editTextFileName, editTextFolderName, editTextContent;
    private Button buttonDeleteFile, buttonWriteFile, buttonReadFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internal_files);
        initView();
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
            StringBuilder result = new StringBuilder();
            String line;
            String folder = getApplication().getFilesDir().getAbsolutePath() + File.separator + editTextFolderName.getText().toString();
            File subFolder = new File(folder);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(folder, editTextFileName.getText().toString())));
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            editTextContent.setText(result.toString());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void buttonWriteFile_onClick(View view) {
        try {
            String folder = getApplication().getFilesDir().getAbsolutePath() + File.separator + editTextFolderName.getText().toString();
            File subFolder = new File(folder);
            if (!subFolder.exists()) {
                subFolder.mkdirs();
            }
            FileOutputStream outputStream = new FileOutputStream(new File(subFolder, editTextFileName.getText().toString()));
            outputStream.write(editTextContent.getText().toString().getBytes());
            outputStream.close();
            Toast.makeText(getApplicationContext(), getString(R.string.done), Toast.LENGTH_LONG).show();
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
                            String folder = getApplication().getFilesDir().getAbsolutePath() + File.separator + editTextFolderName.getText().toString();
                            File subFolder = new File(folder);
                            File file = new File(folder, editTextFileName.getText().toString());
                            if (file.exists()) {
                                file.delete();
                            }
                            Toast.makeText(InternalFiles.this, "File Deleted", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                }).setNegativeButton("No", null).show();
    }

    public void ToExtFiles(View v) {
        startActivity(new Intent(this, ExternalFiles.class));
    }
}


