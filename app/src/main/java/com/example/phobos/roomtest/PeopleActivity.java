package com.example.phobos.roomtest;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PeopleActivity extends AppCompatActivity {

    private PersonAdapter adapter;
    private PersonGenerator generator = new PersonGenerator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initList();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            final PersonDao personDao = AppDatabase.getInstance(this).personDao();
            personDao.insertPerson(generator.getPerson());
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
        adapter = new PersonAdapter(people, person -> {
            final Intent intent = PersonActivity.getStartIntent(this, person.getId());
            startActivity(intent);
        });
        rvPersons.setAdapter(adapter);
    }
}
