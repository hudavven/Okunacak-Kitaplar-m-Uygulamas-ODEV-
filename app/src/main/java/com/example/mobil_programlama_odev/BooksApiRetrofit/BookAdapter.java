package com.example.mobil_programlama_odev.BooksApiRetrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobil_programlama_odev.Activityler.FragmentKitapDetay;
import com.example.mobil_programlama_odev.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<BookItem> bookList;
    private Context context;

    public BookAdapter(List<BookItem> bookList, Context context) {
        this.bookList = bookList;
        this.context = context;
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, authorText;
        Button buttonviewDetailsButton;

        public BookViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.bookTitle);
            authorText = itemView.findViewById(R.id.bookAuthor);
            buttonviewDetailsButton = itemView.findViewById(R.id.viewDetailsButton);

        }
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        BookItem book = bookList.get(position);
        VolumeInfo info = book.getVolumeInfo();

        holder.titleText.setText(info.getTitle());
        holder.authorText.setText(info.getAuthors() != null ? TextUtils.join(", ", info.getAuthors()) : "Bilinmiyor");

        holder.buttonviewDetailsButton.setOnClickListener(v -> {
            // NavController'ı al
            NavController navController = Navigation.findNavController(v);

            // Bundle oluştur
            Bundle bundle = new Bundle();
            bundle.putString("title", info.getTitle());
            bundle.putString("authors", info.getAuthors() != null ? TextUtils.join(", ", info.getAuthors()) : "Bilinmiyor");
            bundle.putString("description", info.getDescription());
            bundle.putInt("pageCount", info.getPageCount());
            bundle.putString("publishedDate", info.getPublishedDate());

            // Navigation action'ını kullanarak geçiş yap
            navController.navigate(R.id.action_fragmentKatalog_to_fragmentKitapDetay, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}

