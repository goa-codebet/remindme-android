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
                    checkEmail();
                }
                return handled;
            }
        });

        return view;
    }

    private void checkEmail() {
        ApiRestClient api = ApiRestClient.getInstance();
        api.checkIfEmailExists("kiasd@asdasd.se").enqueue(new Callback<Email>() {
            @Override
            public void onResponse(Call<Email> call, Response<Email> response) {
                int code = response.code();
                switch(code) {
                    case 200:
                        Bundle args = new Bundle();
                        args.putBoolean("is_login", true);
                        Navigation.findNavController(view).navigate(R.id.authSuccessFragment);
                        break;
                    case 404:
                        Navigation.findNavController(view).navigate(R.id.authPasswordFragment);
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