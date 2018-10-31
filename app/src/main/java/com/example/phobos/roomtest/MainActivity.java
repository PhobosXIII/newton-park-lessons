package com.example.phobos.roomtest;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initList();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            final Person person = new Person("Luke", "", "Tatooin", 77);
            PersonDao personDao = AppDatabase.getInstance(this).personDao();
            personDao.insertPerson(person);
            List<Person> people = personDao.getAll();
            adapter.update(people);
        });
    }

    private void initList() {
        RecyclerView rvPersons = findViewById(R.id.rvPersons);
        rvPersons.setHasFixedSize(true);
        rvPersons.setLayoutManager(new LinearLayoutManager(this));
        rvPersons.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        List<Person> people = AppDatabase.getInstance(this).personDao().getAll();
        adapter = new PersonAdapter(people, item -> Snackbar.make(rvPersons, String.valueOf(item.getId()), Snackbar.LENGTH_LONG).show());
        rvPersons.setAdapter(adapter);
    }
}
