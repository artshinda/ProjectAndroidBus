package com.example.training.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.training.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        View contact = inflater.inflate(R.layout.fragment_profile, container,false);

        final EditText editFirstName      = root.findViewById(R.id.editFirstName);
        final EditText editLastName       = root.findViewById(R.id.editLastName);
        final EditText editMobileNumber   = root.findViewById(R.id.editMobileNumber);
        final EditText editEmail          = root.findViewById(R.id.editEmail);

        profileViewModel.getUsername().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                editFirstName.setText(s);
            }
        });

        profileViewModel.getContactNumber().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                editLastName.setText(s);
            }
        });

        profileViewModel.getMobileNumber().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                editMobileNumber.setText(s);
            }
        });

        profileViewModel.getEmail().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                editEmail.setText(s);
            }
        });
        return root;
    }
}