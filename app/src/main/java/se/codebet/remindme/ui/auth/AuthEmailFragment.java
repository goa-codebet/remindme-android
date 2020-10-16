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

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.codebet.remindme.R;
import se.codebet.remindme.api.ApiRestClient;
import se.codebet.remindme.data.models.Email;

public class AuthEmailFragment extends Fragment {
    View view;
    EditText email;
    TextView apiText;

    public AuthEmailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_auth_email, container, false);
        email = view.findViewById(R.id.editTextTextEmailAddress);
        apiText = view.findViewById((R.id.apiResponse));


        email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;

                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    handled = true;
                    checkEmail(email.getText().toString());
                }
                return handled;
            }
        });

        return view;
    }

    private void checkEmail(final String email) {
        ApiRestClient api = ApiRestClient.getInstance();
        api.checkIfEmailExists(email).enqueue(new Callback<Email>() {
            @Override
            public void onResponse(Call<Email> call, Response<Email> response) {
                int code = response.code();

                AuthEmailFragmentDirections.LoginAction action = AuthEmailFragmentDirections.loginAction(email);

                switch(code) {
                    case 200:
                        action.setIsLogin(true);
                        Navigation.findNavController(view).navigate(action);
                        break;
                    case 404:
                        Navigation.findNavController(view).navigate(action);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Email> call, Throwable t) {
                Log.i("blaha",  t.getMessage());
            }
        });
    }
}