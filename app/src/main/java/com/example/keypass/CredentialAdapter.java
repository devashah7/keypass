package com.example.keypass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CredentialAdapter extends ArrayAdapter<Credential> {
    public CredentialAdapter(Context context, List<Credential> credentials) {
        super(context, 0, credentials);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_credential, parent, false);
        }

        Credential currentCredential = getItem(position);

        TextView websiteTextView = listItemView.findViewById(R.id.websiteTextView);
        websiteTextView.setText(currentCredential.getWebsite());

        TextView usernameTextView = listItemView.findViewById(R.id.usernameTextView);
        usernameTextView.setText(currentCredential.getUsername());

        TextView passwordTextView = listItemView.findViewById(R.id.passwordTextView);
        passwordTextView.setText(currentCredential.getPassword()); // Masked password

        return listItemView;
    }
}

