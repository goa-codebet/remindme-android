package se.codebet.remindme.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * REST client implementation of {@link BaseRestClient} for getting weather based on Lat/Lng.
 */
public class ApiRestClient {

    private static final String API_URL = "https://515s8zqv57.execute-api.eu-north-1.amazonaws.com/Stage";
    private static final int TIMEOUT = 10;
    private static final int SLEEP_TIMEOUT = 5;
    private static final boolean USE_SLEEP_INTERCEPTOR = false;
    private static final ApiRestClient INSTANCE = new ApiRestClient();

    private Retrofit mRetrofit;
    private ApiWeather mApiWeather;
    private RestCall mApiWeatherRestCall;

    /**
     * Singleton instance of {@link ApiRestClient}.
     *
     * @return instance of {@link ApiRestClient}.
     */
    public static ApiRestClient getInstance() {
        return INSTANCE;
    }

    /**
     * Singleton construct.
     */
    private ApiRestClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);

        //simulate long running request
        if (USE_SLEEP_INTERCEPTOR) {
            NetworkSleepInterceptor networkSleepInterceptor = new NetworkSleepInterceptor(
                    SLEEP_TIMEOUT, TimeUnit.SECONDS);
            okHttpClientBuilder
                    .addInterceptor(networkSleepInterceptor);
        }

        mRetrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();

        mApiWeather = mRetrofit.create(ApiWeather.class);
    }

    /**
     * Invoke getWeather via {@link Call} request.
     *
     * @param latitude of location.
     * @param longitude of location.
     */
    public void getWeather(double latitude, double longitude) {
        Call apiWeatherCall = mApiWeather.getWeather(
                String.valueOf(latitude),
                String.valueOf(longitude));

        RestRequest restRequest = new RestRequest.Builder()
                .call(apiWeatherCall)
                .baseResponseEvent(new GetWeatherResponseEvent())
                .useStickyIntent(true)
                .build();

        mApiWeatherRestCall = call(restRequest);
    }

    /**
     * Cancel of getWeather {@link Call} request.
     */
    public void cancelGetWeather() {
        cancelCall(mApiWeatherRestCall);
    }
}