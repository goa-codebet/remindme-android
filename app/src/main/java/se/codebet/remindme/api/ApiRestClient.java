package se.codebet.remindme.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.codebet.remindme.api.interfaces.EmailApi;
import se.codebet.remindme.data.models.Email;

public class ApiRestClient {

    private static final String API_URL = "https://f1gc3szwd4.execute-api.eu-north-1.amazonaws.com/Stage/";
    private static final int TIMEOUT = 10;
    private static ApiRestClient INSTANCE = null;

    private Retrofit mRetrofit;

    /**
     * Singleton instance of {@link ApiRestClient}.
     *
     * @return instance of {@link ApiRestClient}.
     */
    public static ApiRestClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApiRestClient();
        }
        return INSTANCE;
    }

    /**
     * Singleton construct.
     */
    private ApiRestClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);


        mRetrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();

    }

    /**
     * Invoke getWeather via {@link Call} request.
     *
     * @param email String
     */
    public Call<Email> checkIfEmailExists(String email) {
        EmailApi api = mRetrofit.create(EmailApi.class);
        return api.checkIfEmailExists(email);
    }

}