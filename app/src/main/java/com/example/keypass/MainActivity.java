package com.example.keypass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements InputModal.OnCredentialAddedListener {
    Button button;
    EditText website, username, password;
    ListView listView;
    FloatingActionButton fab;
    CredentialAdapter adapter;
    List<Credential> credentialsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputModal im = new InputModal();
                im.setOnCredentialAddedListener(MainActivity.this);
                im.show(getSupportFragmentManager(), "Input Modal");
                /*GoodDayDialogFragment goodDayDialogFragment = new GoodDayDialogFragment();
                goodDayDialogFragment.show(getSupportFragmentManager(), "GoodDayDialog");*/

            }
        });

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        credentialsList = dbHelper.getAllCredentials();
        listView = (ListView) findViewById(R.id.listView);
        adapter = new CredentialAdapter(this, credentialsList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Credential clickedCredential = (Credential) parent.getItemAtPosition(position);
        String password = clickedCredential.getPassword();
                ClipboardManager clipboard = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("password", password);
                if (clipboard != null) {
                    clipboard.setPrimaryClip(clip);
                    //Toast.makeText(getApplicationContext(), "Password copied to clipboard", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onCredentialAdded() {

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        credentialsList.clear();
        credentialsList.addAll(dbHelper.getAllCredentials());
        for (Credential credential : credentialsList) {
            Log.d("Credential", "Website: " + credential.getWebsite() +
                    ", Username: " + credential.getUsername() +
                    ", Password: " + credential.getPassword());
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                listView.invalidateViews();
            }
        });

    }
}