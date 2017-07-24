package com.example.alpin.sharedpreferences.Doa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alpin.sharedpreferences.R;
import com.example.alpin.sharedpreferences.model.Doa;

public class DetailDoaActivity extends AppCompatActivity {
    private Doa doa = null;
    TextView tv_nama,tv_doa,tv_ket;
    ImageView imgDoa;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_doa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_nama = (TextView) findViewById(R.id.tv_nama);
        tv_doa = (TextView) findViewById(R.id.tv_doa);
        tv_ket = (TextView) findViewById(R.id.tv_ket);
        imgDoa = (ImageView) findViewById(R.id.iv_img);


        doa = getIntent().getParcelableExtra("doa");
        if (doa != null) {
            getSupportActionBar().setTitle(doa.getNama());
            id = doa.getId();
            tv_nama.setText(doa.getNama());
            tv_doa.setText(doa.getDoa());
            tv_ket.setText(doa.getKet());
            Glide.with(DetailDoaActivity.this).load(doa.getImageAddrees()).into(imgDoa);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
