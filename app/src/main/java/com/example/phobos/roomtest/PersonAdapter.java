package com.example.phobos.roomtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder>{
    private List<Person> people;
    private OnItemClickListener itemClickListener;

    public static PersonAdapter createAdapter(OnItemClickListener itemClickListener){
        List<Person> people = new ArrayList<>(3);
        people.add(new Person(1,"Luke", "", "Tatooine", 77));
        people.add(new Person(2, "Leia", "", "Alderaan", 49));
        people.add(new Person(3, "Obi-Wan", "", "Stewjon", 77));
        return new PersonAdapter(people, itemClickListener);
    }

    public PersonAdapter(List<Person> people, OnItemClickListener itemClickListener) {
        this.people = people;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_person, viewGroup, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int position) {
        personViewHolder.bind(people.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return people == null ? 0 : people.size();
    }

    public void add(Person person){
        people.add(person);
        notifyDataSetChanged();
    }

    public void update(List<Person> people){
        this.people.clear();
        this.people.addAll(people);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Person item);
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder{

        ImageView ivAvatar;
        TextView tvName;
        TextView tvPlanet;
        TextView tvMass;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            tvName = itemView.findViewById(R.id.tvName);
            tvPlanet = itemView.findViewById(R.id.tvPlanet);
            tvMass = itemView.findViewById(R.id.tvMass);
        }

        public void bind(final Person person, final OnItemClickListener itemClickListener){
            itemView.setOnClickListener(v -> itemClickListener.onItemClick(person));

            ivAvatar.setImageResource(R.drawable.ic_launcher_foreground);
            tvName.setText(person.getName());
            tvPlanet.setText(person.getPlanet());
            tvMass.setText(String.valueOf(person.getMass()));
        }
    }


}
