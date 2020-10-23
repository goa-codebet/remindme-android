package se.codebet.remindme.api.interfaces;

        import retrofit2.Call;
        import retrofit2.http.Body;
        import retrofit2.http.POST;
        import retrofit2.http.Query;
        import se.codebet.remindme.data.models.Email;
        import se.codebet.remindme.data.models.Token;
        import se.codebet.remindme.data.models.UserData;

public interface EmailApi {
    @POST("check-account")
    Call<Email> checkIfEmailExists(@Query("email") String email);

    @POST("create-account")
    Call<Token> createAccount(@Body UserData userData);

    @POST("login-account")
    Call<Token> login(@Body UserData userData);
}
