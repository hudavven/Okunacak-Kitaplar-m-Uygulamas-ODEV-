package com.example.mobil_programlama_odev.Activityler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobil_programlama_odev.BooksApiRetrofit.BookAdapter;
import com.example.mobil_programlama_odev.BooksApiRetrofit.BookApiService;
import com.example.mobil_programlama_odev.BooksApiRetrofit.BookItem;
import com.example.mobil_programlama_odev.BooksApiRetrofit.BookResponse;
import com.example.mobil_programlama_odev.BooksApiRetrofit.RetrofitClient;
import com.example.mobil_programlama_odev.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentKatalog extends Fragment {
    private Toolbar toolbar;
    private ImageView imageView;
    private TextView textViewPoweredBy;
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private static final String API_KEY = "AIzaSyD462bSHYikJw8RZAMwVMnhcJbgc-D8uhQ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_katalog, container, false);

        imageView = view.findViewById(R.id.imageView2);
        textViewPoweredBy = view.findViewById(R.id.textViewPoweredBy);

        toolbar = view.findViewById(R.id.toolbar2);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        // Fragment'ın menülerini kullanabilmek için bu satırı ekleyin
        setHasOptionsMenu(true);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));


        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_arama, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setQueryHint("Kitap ara...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                imageView.setVisibility(View.GONE);
                textViewPoweredBy.setVisibility(View.GONE);
                fetchBooks(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Yazı değiştikçe yapılacaklar (canlı filtreleme gibi)
                performSearch(newText);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void performSearch(String query) {
        // Burada arama işlemini yap, örneğin RecyclerView filtresi gibi
        Log.d("Search", "Aranan: " + query);
    }

    private void fetchBooks(String query) {
        BookApiService service = RetrofitClient.getClient().create(BookApiService.class);
        Call<BookResponse> call = service.searchBooks(query, API_KEY);

        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<BookItem> books = response.body().getItems();
                    bookAdapter = new BookAdapter(books, getContext());
                    recyclerView.setAdapter(bookAdapter);
                } else {
                    Toast.makeText(getContext(), "Veri alınamadı.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Hata: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
