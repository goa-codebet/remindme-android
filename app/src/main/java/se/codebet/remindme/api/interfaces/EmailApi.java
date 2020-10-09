package se.codebet.remindme.api.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import se.codebet.remindme.data.models.Email;

public interface EmailApi {
    @POST("/check-account")
    Call<List<Email>> checkIfEmailExists(@Body Email email);
}
