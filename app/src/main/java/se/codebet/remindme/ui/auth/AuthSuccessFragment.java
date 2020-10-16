package se.codebet.remindme.ui.auth;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.codebet.remindme.R;

public class AuthSuccessFragment extends Fragment {
    View view;

    public AuthSuccessFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_auth_success, container, false);

        final boolean isLogin = AuthSuccessFragmentArgs.fromBundle(getArguments()).getIsLogin();
        final String email = AuthSuccessFragmentArgs.fromBundle(getArguments()).getEmail();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AuthSuccessFragmentDirections.PasswordAction action = AuthSuccessFragmentDirections.passwordAction(email);
                action.setIsLogin(isLogin);
                Navigation.findNavController(view).navigate(action);
            }
        }, 5000);

        return view;
    }
}