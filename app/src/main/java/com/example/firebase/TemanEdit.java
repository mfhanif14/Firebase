package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebase.database.Teman;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TemanEdit extends AppCompatActivity {
    private DatabaseReference database;
    private TextView kodeText;
    EditText edNama,edTelpon;
    Button editBtn;
    String kode,nama,telpon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teman_edit);

        kodeText = findViewById(R.id.txtKode);
        edNama = findViewById(R.id.editNama);
        edTelpon = findViewById(R.id.editTelpon);
        editBtn = findViewById(R.id.btnEdit);

        database = FirebaseDatabase.getInstance().getReference();

        Bundle bendel = this.getIntent().getExtras();
        kode = bendel.getString("kunci1");
        nama = bendel.getString("kunci2");
        telpon = bendel.getString("kunci3");

        kodeText.setText("key : "+kode);
        edNama.setText(nama);
        edTelpon.setText(telpon);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTeman(new Teman(edNama.getText().toString(),edTelpon.getText().toString()));
            }
        });
    }

    public void editTeman(Teman tmn){
        database.child("Teman")
                .child(kode)
                .setValue(tmn)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(TemanEdit.this, "Data berhasil diupdate", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

}