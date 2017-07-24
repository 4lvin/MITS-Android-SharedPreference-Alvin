package com.example.alpin.sharedpreferences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.alpin.sharedpreferences.Doa.DetailDoaActivity;
import com.example.alpin.sharedpreferences.Doa.DetailItemActivity;
import com.example.alpin.sharedpreferences.auth.LoginActivity;
import com.example.alpin.sharedpreferences.model.Doa;
import com.example.alpin.sharedpreferences.utility.DatabaseHandler;
import com.example.alpin.sharedpreferences.Doa.DoaAdapter;
import com.example.alpin.sharedpreferences.utility.SessionManager;
import com.example.alpin.sharedpreferences.utility.SpacesItemDecoration;
import com.example.alpin.sharedpreferences.utility.recycler.RecyclerTouchListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private DoaAdapter adapter;
    private RecyclerView recyclerView;
    private Intent intent;
    public static final int RESULT_ADD = 2;
    public static final int RESULT_UPDATE = 3;
    private DatabaseHandler tblDoa;
    private int pos = 1;

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar topToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);
        topToolBar.setLogoDescription(getResources().getString(R.string.app_name));


        tblDoa = DatabaseHandler.getInstance();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SessionManager.getInstance().clear();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    protected void onResume() {
        super.onResume();
        initRecyclerView();
    }

    public void initRecyclerView() {
        List<Doa> list = tblDoa.getAllDoa();
        adapter = new DoaAdapter(MainActivity.this, list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(this, R.dimen.space_5));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        intent = new Intent(MainActivity.this, DetailDoaActivity.class);
                        Doa doa = adapter.getItem(position);
                        intent.putExtra("doa", doa);
                       // pos = position;
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        intent = new Intent(MainActivity.this, DetailItemActivity.class);
                        Doa doa = adapter.getItem(position);

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
            tblDoa.deleteDoaById(adapter.getItem(position).getId());
            adapter.remove(position);

        }
    };


    public void submitAddMovie(View view) {
        Intent intent = new Intent(this, DetailItemActivity.class);
        startActivityForResult(intent, RESULT_ADD);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_ADD) {
            Doa doa = data.getParcelableExtra("data_add");
            tblDoa.addDoa(doa);
            adapter.insert(doa);
            recyclerView.scrollToPosition(0);
        } else if (resultCode == RESULT_UPDATE) {
            Doa doa = data.getParcelableExtra("data_update");
            tblDoa.updateDoa(new Doa(doa.getId(), doa.getNama(), doa.getDoa(),
                    doa.getKet(), doa.getImageAddrees()));
            adapter.update(pos, doa);
            recyclerView.scrollToPosition(0);
        }
    }
}

