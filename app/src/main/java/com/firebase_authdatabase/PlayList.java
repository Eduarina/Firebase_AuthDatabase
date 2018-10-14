package com.firebase_authdatabase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;

import com.firebase_authdatabase.Fragment.SongFragment;


public class PlayList extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);

        getSupportFragmentManager()
                .beginTransaction ()
                .replace (R.id.rootContainer, new SongFragment())
                .setTransition (FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit ();

    }
}