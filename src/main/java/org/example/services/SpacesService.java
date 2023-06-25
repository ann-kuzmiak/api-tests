package org.example.services;

import org.example.models.Spaces;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface SpacesService {
    @GET("team/{team_id}/space")
    Call<Spaces> getSpaces(@Path("team_id") String teamId);
}
