package se.codebet.remindme.api.interfaces;

        import java.util.List;

        import retrofit2.Call;
        import retrofit2.http.POST;
        import retrofit2.http.Query;
        import se.codebet.remindme.data.models.Email;

public interface EmailApi {
    @POST("check-account")
    Call<Email> checkIfEmailExists(@Query("email") String email);
}
