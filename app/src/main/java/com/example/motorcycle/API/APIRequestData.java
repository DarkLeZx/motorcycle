package com.example.motorcycle.API;

import com.example.motorcycle.Model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrievemoto.php")
    Call<ModelResponse> ardRetrieve();

    @FormUrlEncoded
    @POST("createmoto.php")
    Call<ModelResponse>ardCreate(
            @Field("nama") String nama,
            @Field("merk") String merk,
            @Field("mesin") String mesin,
            @Field("tahun") String tahun,
            @Field("harga") String harga,
            @Field("foto") String foto
    );

    @FormUrlEncoded
    @POST("updatemoto.php")
    Call<ModelResponse>ardUpdate(
            @Field("id") String id,
            @Field("nama") String nama,
            @Field("merk") String merk,
            @Field("mesin") String mesin,
            @Field("tahun") String tahun,
            @Field("harga") String harga,
            @Field("foto") String foto
    );

    @FormUrlEncoded
    @POST("deletemoto.php")
    Call<ModelResponse>ardDelete(
            @Field("id") String id
    );
}
