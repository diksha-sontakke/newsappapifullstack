package com.example.newsappapifullstackapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {



    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    /**
     * A service generator class defines one method to create a basic REST client for a given class/interface.
     * It simply returns a service class from the provided argument interface.
     * @param serviceClass
     * @param <S>
     * @return
     */

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
