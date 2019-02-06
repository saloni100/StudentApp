package com.salonig.studentdemo;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.salonig.studentdemo.db.DaoSession;
import com.salonig.studentdemo.db.Student;
import com.salonig.studentdemo.db.StudentDao;
import com.salonig.studentdemo.db.User;
import com.salonig.studentdemo.db.UserDao;

import java.util.Calendar;
import java.util.Date;

import static com.salonig.studentdemo.R.id.course_text;

public class Add_activity extends AppCompatActivity {

    private DaoSession daoSession;

    TextView dob;
    TextView dialog;
    ImageView image;
    Button add_button;
    int year_x, month_x, day_x;
    static final int DIALOD_ID = 0;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addreset);
        spinner = (Spinner) findViewById(R.id.spinner);
        dialog = (TextView) findViewById(R.id.dialog_text);
        image = (ImageView) findViewById(R.id.image);
        add_button = (Button)findViewById(R.id.button);
        adapter = ArrayAdapter.createFromResource(this, R.array.course_names,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position) + "is selected", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        showdialogonclick();

        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setup the alert builder

                AlertDialog.Builder builder = new AlertDialog.Builder(Add_activity.this);
                builder.setTitle("Select State");

// add a radio button list
                String[] state = {"Rajasthan", "Madhya pradesh", "Uttar pradesh", "Gujrat", "Goa", "Bihar", "Asaam", "Haryana", "Himachal Pradesh", "Jharkhand", "Arunachal Pradesh", "Kerala"};
                int checkedItem = 1; // cow
                builder.setSingleChoiceItems(state, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // user checked an item
                    }
                });

// add OK and Cancel buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // user clicked OK
                    }
                });
                builder.setNegativeButton("Cancel", null);

// create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);//zero can be replaced with any action code// }


                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);//one can be replaced with any action code


            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add();
                Toast.makeText(getApplicationContext(),"student data set",Toast.LENGTH_SHORT).show();


            }
        });


        }

    public void add() {
        StudentDao studentDao = daoSession.getStudentDao();
        Student student=new Student();
        student.setFull_name(student.getFull_name());
        student.setRoll_no(student.getRoll_no());
        student.setGender(student.getGender());
        student.setDate((Date) dob.getTag());
        student.setCourse_position(spinner.getSelectedItemPosition());

        student.setAddress_name(dialog.getText().toString());
        studentDao.insert(student);
    }





    public void showdialogonclick() {
        dob = (TextView) findViewById(R.id.datepicker_text);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOD_ID);

            }
        });

    }

    protected Dialog onCreateDialog(int id) {

        if (id == DIALOD_ID)
            return new DatePickerDialog(this, dpickerlistener, year_x, month_x, day_x);
        return null;

    }

    private DatePickerDialog.OnDateSetListener dpickerlistener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthofyear, int dayofmonth) {
                    Date date = new Date();
                    year_x = year;
                    month_x = monthofyear + 1;
                    day_x = dayofmonth;
                    dob.setTag(date);
                    Toast.makeText(Add_activity.this, year_x + " / " + month_x + " / " + day_x, Toast.LENGTH_LONG).show();


                }
            };

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    image.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    image.setImageURI(selectedImage);
                }
                break;
        }
    }



}