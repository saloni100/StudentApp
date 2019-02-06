package com.salonig.studentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.salonig.studentdemo.db.DaoSession;
import com.salonig.studentdemo.db.StudentDao;
import com.salonig.studentdemo.db.User;
import com.salonig.studentdemo.db.UserDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DaoSession daoSession;
    Button add;
    Button display;
    Button delete;
    Button update;
    ListView listView;
    List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = (Button)findViewById(R.id.addButton);
        display = (Button)findViewById(R.id.displayButton);
        delete = (Button)findViewById(R.id.deleteButton);
        update = (Button)findViewById(R.id.updateButton);
        daoSession = ((AppController) getApplication()).getDaoSession();



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaylist();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(1);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(1);
            }
        });

        }


    private void displaylist() {
       int i;

        UserDao userDao = daoSession.getUserDao();
          users=userDao.loadAll();
        for(i=1;i<users.size();i++)
        {
            User user = users.get(i);

           System.out.println(user.getFirst_name()+" "+user.getLast_name()+" "+user.getEmail()+" "+user.getId());

           }

           }

    private void add() {
        UserDao userDao = daoSession.getUserDao();
        User user=new User();
        user.setUser_id(123);
        user.setEmail("gnextsaloni100@gmail.com");
        user.setLast_name("gupta");
        user.setFirst_name("saloni");
        userDao.insert(user);
        }


    private void delete(long id){
        //// Get the entity dao we need to work with.
        UserDao userDao = daoSession.getUserDao();
        /// perform delete operation
        userDao.deleteByKey(id);
        displaylist();
    }
    private void update(long id){
        UserDao userDao = daoSession.getUserDao();
        User user = new User();
        user.setId(id);
        Toast.makeText(this, "Item updated", Toast.LENGTH_SHORT).show();
        finish();
    }

}
