package com.example.keypass;

import static com.example.keypass.MainActivity.mainActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class YesNoDialogFragment extends DialogFragment {

    long id;

    public YesNoDialogFragment(long id) {
        this.id = id;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Are you sure?");
        builder.setMessage("Delete action cannot be undone.");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when the user clicks yes.
                DatabaseHelper dbHelper = new DatabaseHelper(getContext());
                dbHelper.deleteCredential(id);
                mainActivity.onCredentialAdded();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when the user clicks no.
            }
        });
        return builder.create();
    }
}