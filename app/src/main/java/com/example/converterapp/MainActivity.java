package com.example.converterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText textBox;
    Button button, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textBox = (EditText) findViewById(R.id.textBox);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textInfo = textBox.getText().toString();
                String pathWay = getExternalFilesDir(null).toString() +
                        "/converted.pdf";
                File f = new File(pathWay);

                /* This checks to see if a file exists, and if a file does not exist, it
                creates a new one. */

                if (!f.exists()) {
                    try {
                        f.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                /* They A5 below represents the size of the page.
                Other sizes can be used, but I decided to go with A5.
                The size of A5 is about  5 7/8" x 8 1/4". */

                Document doc = new Document(PageSize.A5);
                try {
                    PdfWriter.getInstance(doc, new FileOutputStream(f.getAbsoluteFile()));
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                /* This is where the text input and font size is implemented.
                * The text that will be displayed in the document will be displayed
                * in Courier font with a size of 28. */

                doc.open();
                Font textFont = new Font(Font.FontFamily.COURIER, 28);
                Paragraph p = new Paragraph();
                p.add(new Paragraph(textInfo, textFont));

                try {
                    doc.add(p);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }

                doc.close();

                /*This provides feedback to a user. When the Convert to PDF button
                * is pressed, the user will be notified that the text was converted. */

                Toast.makeText(MainActivity.this, "Text has been converted.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ConvertActivity.class);
                startActivity(i);
            }
        });
    }
}