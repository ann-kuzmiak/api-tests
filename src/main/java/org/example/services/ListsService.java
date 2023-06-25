package org.example.services;

import okhttp3.ResponseBody;
import org.example.models.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

public interface ListsService {
    @POST("space/{space_id}/list")
    Call<List> createFolderlessList(@Path("space_id") String spaceId, @Body List list);

    @DELETE("list/{list_id}")
    Call<ResponseBody> deleteList(@Path("list_id") String listId);

    @GET("list/{list_id}")
    Call<List> getList(@Path("list_id") String listId);
}
