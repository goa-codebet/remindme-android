package se.codebet.remindme.ui.auth;

import android.content.Context;
import android.content.SharedPreferences;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.codebet.remindme.R;
import se.codebet.remindme.api.ApiRestClient;
import se.codebet.remindme.data.models.Email;
import se.codebet.remindme.data.models.Token;
import se.codebet.remindme.data.models.UserData;

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
                    final UserData userData = new UserData(email, password.getText().toString());
                    Log.i("onEditorAction", "onEditorAction: ");

                    if(!isLogin) {
                        createAccount(userData);
                    }
                    handled = true;
                }
                return handled;
            }
        });

        return view;
    }

    private void createAccount(final UserData userdata) {
        ApiRestClient api = ApiRestClient.getInstance();
        api.createAccount(userdata).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                int code = response.code();

               // uthEmailFragmentDirections.LoginAction action = AuthEmailFragmentDirections.loginAction(email);
                Log.i("blaha",  String.valueOf(code));
                Log.i("blaha",  response.body().getMessage());
                switch(code) {
                    case 201:
                        Log.i("blaha",  String.valueOf(code));
                        String token = response.body().getToken();
                        SharedPreferences sharedPref = getActivity().getSharedPreferences("CB", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("token", token);
                        editor.apply();
                        //Navigation.findNavController(view).navigate(action);
                        break;
                    case 409:
                        //Navigation.findNavController(view).navigate(action);
                        Log.i("blaha",  "409");
                        break;
                    case 400:
                        Log.i("blaha",  "400");
                    default:
                      break;
                }

            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.i("blaha",  t.getMessage());
            }
        });
    }
}