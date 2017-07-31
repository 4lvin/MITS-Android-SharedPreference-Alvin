package com.example.alpin.sharedpreferences;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.example.alpin.sharedpreferences.Doa.DetailDoaActivity;
import com.example.alpin.sharedpreferences.Doa.DetailItemActivity;
import com.example.alpin.sharedpreferences.Doa.DoaAdapter;
import com.example.alpin.sharedpreferences.model.Doa;
//import com.example.alpin.sharedpreferences.model.Doa.DetailDoaActivity;
//import com.example.alpin.sharedpreferences.model.Doa.DetailItemActivity;
//import com.example.alpin.sharedpreferences.model.Doa.DoaAdapter;
import com.example.alpin.sharedpreferences.utility.SpacesItemDecoration;
import com.example.alpin.sharedpreferences.utility.recycler.RecyclerTouchListener;

import java.util.List;

public class HomeFragment extends Fragment {

    private DoaAdapter adapter;
    private RecyclerView recyclerView;
    private Intent intent;
    public static final int RESULT_ADD = 2;
    public static final int RESULT_UPDATE = 5;
    private int pos = 1;
    private long id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        initRecyclerView();

    /*    List<Doa> list1;
        list1 = new ArrayList<>();

        list1.add(new Doa("Doa sebelum makan", "bismillahirrohmanirrohim", "baca 1x",
                R.drawable.ic_home_black_24dp));

        adapter = new DoaAdapter(list1);*/

        FloatingActionButton btnSave = view.findViewById(R.id.add);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DetailItemActivity.class);
                startActivityForResult(intent, RESULT_ADD);
            }
        });

        return view;
    }

    public void initRecyclerView() {
        List<Doa> list = Doa.getAll();
        adapter = new DoaAdapter(getActivity(), list);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(getActivity(), R.dimen.space_5));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView,
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
                        intent = new Intent(getActivity(), DetailItemActivity.class);
                        Doa doa = adapter.getItem(position);

                        if (doa.getId() == null)
                            id = 0;
                        else
                            id = doa.getId();

                        intent.putExtra("id_doa", doa.getId());
                        intent.putExtra("doa", doa);
                        pos = position;
                        startActivityForResult(intent, RESULT_UPDATE);
                    }
                }));

        ItemTouchHelper touchHelper = new ItemTouchHelper(simpleCallback);
        touchHelper.attachToRecyclerView(recyclerView);

    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                              RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            Doa doa = adapter.getItem(position);
            doa.delete();
            adapter.remove(position);
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_ADD) {
            Doa doa = data.getParcelableExtra("data_add");
            doa.save();
            adapter.insert(doa);
            recyclerView.scrollToPosition(0);
        } else if (resultCode == RESULT_UPDATE) {
            Doa doa = data.getParcelableExtra("data_update");
//            Doa.updateDoa(id, doa);
            Log.d("id : " + doa.getId(), " image : " + doa.getImage());
            adapter.update(pos, doa);
            adapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(0);
        }
    }
}

