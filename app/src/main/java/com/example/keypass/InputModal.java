package com.example.keypass;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

public class InputModal extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);

        EditText editWebsite = (EditText) view.findViewById(R.id.websiteEditText);
        EditText editUsername = (EditText) view.findViewById(R.id.usernameEditText);
        EditText editPassword = (EditText) view.findViewById(R.id.passwordEditText);
        Button save = (Button) view.findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(getContext());
                String codedpass = "";
                try{
                    codedpass = EncryptDecrypt.encrypt(editPassword.getText().toString());
                }catch(Exception e){

                    e.printStackTrace();
                    Log.d("MyApp", "This is a debug message" + e);
                }
                Log.d("MyApp", "This is a debug message" + codedpass);
                long id = dbHelper.insertCredential(editWebsite.getText().toString(), editUsername.getText().toString(), codedpass);
                dbHelper.close();
                if (listener != null) {
                    listener.onCredentialAdded();
                }
                dismiss();
            }
        });

        return view;
    }
    // Define an interface for communication
    public interface OnCredentialAddedListener {
        void onCredentialAdded();
    }

    // Declare a listener variable
    private OnCredentialAddedListener listener;

    public void setOnCredentialAddedListener(OnCredentialAddedListener listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("Hello!");
        return dialog;
    }
}