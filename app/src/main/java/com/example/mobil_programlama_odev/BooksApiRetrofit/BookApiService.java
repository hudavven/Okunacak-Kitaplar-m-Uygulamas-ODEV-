package com.example.mobil_programlama_odev.BooksApiRetrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// https://www.googleapis.com/books/v1/volumes?q=flowers+inauthor:keyes&key=yourAPIKey

public interface BookApiService {
    @GET("volumes")
    Call<BookResponse> searchBooks(
            @Query("q") String query,
            @Query("key") String apiKey
    );
}

