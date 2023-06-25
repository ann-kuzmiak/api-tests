package org.example.services;

import org.example.models.Teams;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface TeamsService {
    @GET("team")
    Call<Teams> getAuthorizedTeams();
}
