package com.firebase_authdatabase.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase_authdatabase.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SongFragment extends Fragment {

    RecyclerView recylerView;
    FirebaseDatabase database;
    private ArrayList<Song> canciones;

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate (R.layout.fragment_song, container, false);
        database = FirebaseDatabase.getInstance();
        DatabaseReference root = database.getReference();
        DatabaseReference songs  = root.child("songs");

        canciones = new ArrayList<>();

        ValueEventListener value = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item: dataSnapshot.getChildren()){
                    canciones.add(item.getValue(Song.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w ("PKAT", databaseError.toException ());
            }
        };

        songs.addValueEventListener(value);

        recylerView = v.findViewById(R.id.reyclerViewUser);
        recylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SongAdapter adapter = new SongAdapter(canciones);
        recylerView.setAdapter(adapter);
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}

class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder>{

    private ArrayList<Song> canciones;

    public SongAdapter(ArrayList<Song> canciones) {
        this.canciones = canciones;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from (parent.getContext ());
        View view = inflater.inflate (R.layout.info_song, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        Song c = canciones.get(position);
        holder.setDatos(c.title,c.author);
    }

    @Override
    public int getItemCount() {
        return canciones.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {

        private TextView title,author;

        public SongViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.titulo);
            author = v.findViewById(R.id.autor);
        }

        public void setDatos(String title, String author) {
            this.title.setText(title);
            this.author.setText(author);
        }
    }
}

class Song{
    String title;
    String author;
    String album;
    String company;
    String composer;
    String cover;
    Long year;
}

