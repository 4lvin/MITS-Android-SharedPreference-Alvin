package com.example.alpin.sharedpreferences;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.alpin.sharedpreferences.Doa.DetailDoaActivity;
import com.example.alpin.sharedpreferences.Doa.DoaStaticAdapter;
import com.example.alpin.sharedpreferences.model.Doa;
import com.example.alpin.sharedpreferences.utility.SpacesItemDecoration;
import com.example.alpin.sharedpreferences.utility.recycler.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Intent intent;

    public HomeFragment() {
        // Required empty public constructor
    }
    public static final List<Doa> dataSet = new ArrayList<>();
    public static final DoaStaticAdapter adapter = new DoaStaticAdapter(dataSet);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);




        dataSet.add(new Doa("Doa Sebelum Tidur", "بِسْمِكَ اللّهُمَّ اَحْيَا وَاَمُوْتُ",
                "Bismikallohumma ahya wa amutu", R.drawable.sleep));
        dataSet.add(new Doa("Doa Bangun Tidur", "اَلْحَمْدُ ِللهِ الَّذِىْ اَحْيَانَا بَعْدَمَآ اَمَاتَنَا وَاِلَيْهِ النُّشُوْرُ",
                "AL HAMDU LILLAAHIL LADZII AHYAANAA BA'DA MAA AMAA TANAA WA ILAIHIN NUSYUUR",
                R.drawable.alarm));
        dataSet.add(new Doa("Doa Sebelum Wudhu", "نَوَ ئْت الْوضُوءَلِرَفْعِ الْحَدَثِ الْاَصْغَرِفَرْضًا لِلَّةِ تَعَالَ",
                "Nawaitul wudhu a liraf'il  hada tsil ashghari fardhal lillaahi ta'aala", R.drawable.tap));
        dataSet.add(new Doa("Doa Sesudah Wudhu", "Bismillah", "baca", R.drawable.tap));
        dataSet.add(new Doa("Doa Keluar Rumah", "Bismillah", "baca", R.drawable.icon));
        dataSet.add(new Doa("Doa Masuk Rumah", "Bismillah", "baca", R.drawable.icon));
        dataSet.add(new Doa("Doa Pergi ke Masjid", "Bismillah", "baca", R.drawable.mosque));
        dataSet.add(new Doa("Doa Masuk Masjid", "Bismillah", "baca", R.drawable.mosque));
/*        dataSet.add(new Doa("Doa Keluar Masjid"));
        dataSet.add(new Doa("Doa Sesudah Adzan"));*/


        RecyclerView rvInbox = view.findViewById(R.id.rv_main);
        rvInbox.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvInbox.addItemDecoration(new SpacesItemDecoration(getActivity(), R.dimen.space_5));
        rvInbox.setAdapter(adapter);

        rvInbox.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvInbox,
                new RecyclerTouchListener.ClickListener() {

                    @Override
                    public void onClick(View view, int position) {
                        intent = new Intent(getActivity(), DetailDoaActivity.class);
                        Doa doa = adapter.getItem(position);
                        intent.putExtra("doa", doa);
                        // pos = position;
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }

                }));
        return view;
    }
}
