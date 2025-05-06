package com.example.mobil_programlama_odev.Activityler;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobil_programlama_odev.R;


public class FragmentKitapDetay extends Fragment {
    TextView titleText, authorText, descriptionText, pageCountText, publishedDateText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kitap_detay, container, false);
        TextView titleText = view.findViewById(R.id.detailTitle);
        TextView authorText = view.findViewById(R.id.detailAuthor);
        TextView descriptionText = view.findViewById(R.id.detailDescription);
        TextView pageCountText = view.findViewById(R.id.detailPageCount);
        TextView publishedDateText = view.findViewById(R.id.detailPublishedDate);

        Bundle args = getArguments();
        if (args != null) {
            titleText.setText(args.getString("title"));
            authorText.setText(args.getString("authors"));
            descriptionText.setText(args.getString("description"));
            pageCountText.setText("Sayfa Sayısı: " + args.getInt("pageCount", 0));
            publishedDateText.setText("Yayın Tarihi: " + args.getString("publishedDate"));
        }

        return view;
    }

}