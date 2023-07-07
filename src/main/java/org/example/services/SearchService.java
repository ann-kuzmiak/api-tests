package org.example.services;

import org.example.models.SearchPayload;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchService {
    @GET("API/SearchMovie/{APIKey}/{expression}")
    Call<SearchPayload> searchMovie(@Path("APIKey") String APIKey, @Path("expression") String expression);

    @GET("API/AdvancedSearch/{APIKey}")
    Call<SearchPayload> searchMovieAdvanced(
            @Path("APIKey") String APIKey,
            @Query("title") String title,
            @Query("title_type") String titleType,
            @Query("genres") String genres);
}
