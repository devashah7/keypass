package com.example.keypass;

import static com.example.keypass.MainActivity.mainActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        ImageView imageView = listItemView.findViewById(R.id.passwordIcon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YesNoDialogFragment dialogFragment = new YesNoDialogFragment(currentCredential.getId());
                dialogFragment.show(mainActivity.getSupportFragmentManager(), "myDialog");

            }
        });

        return listItemView;
    }
}

