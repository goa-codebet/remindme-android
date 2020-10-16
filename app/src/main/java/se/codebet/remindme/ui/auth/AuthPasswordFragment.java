package se.codebet.remindme.ui.auth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import se.codebet.remindme.R;

public class AuthPasswordFragment extends Fragment {
    View view;
    EditText password;

    public AuthPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_auth_password, container, false);
        password = view.findViewById(R.id.editTextTextPassword);

        final boolean isLogin = AuthPasswordFragmentArgs.fromBundle(getArguments()).getIsLogin();
        final String email = AuthPasswordFragmentArgs.fromBundle(getArguments()).getEmail();

        Log.i("email", email);

        if (isLogin) {
            password.setHint("Ange lösenord");
        }

        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;

                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    Log.i("onEditorAction", "onEditorAction: ");
                    Navigation.findNavController(v).navigate(R.id.authSuccessFragment);
                    handled = true;
                }
                return handled;
            }
        });

        return view;
    }
}