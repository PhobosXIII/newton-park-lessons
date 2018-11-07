package com.example.phobos.roomtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PersonActivity extends AppCompatActivity {

    public static final String EXTRA_PERSON_ID = "com.example.phobos.roomtest.extras.EXTRA_PERSON_ID";

    public static Intent getStartIntent(Context context, long personId){
        return new Intent(context, PersonActivity.class).putExtra(EXTRA_PERSON_ID, personId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initUi();
    }

    private void initUi() {
        ImageView ivAvatar = findViewById(R.id.ivAvatar);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvPlanet = findViewById(R.id.tvPlanet);
        TextView tvMass = findViewById(R.id.tvMass);

        final long personId = getIntent().getLongExtra(EXTRA_PERSON_ID, 0);
        final Person person = AppDatabase.getInstance(this).personDao().getById(personId);
        ivAvatar.setImageResource(R.drawable.ic_launcher_foreground);
        tvName.setText(person.getName());
        tvPlanet.setText(person.getPlanet());
        tvMass.setText(String.valueOf(person.getMass()));
    }
}
