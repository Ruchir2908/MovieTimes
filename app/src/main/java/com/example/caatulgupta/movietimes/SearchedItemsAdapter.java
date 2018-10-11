package com.example.caatulgupta.movietimes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchedItemsAdapter extends ArrayAdapter{

    ArrayList<Item> items;
    Context context;

    public SearchedItemsAdapter(@NonNull Context context, int resource, ArrayList<Item> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.search_item,parent,false);

        TextView contentTextView = view.findViewById(R.id.content);
        TextView categoryTextView = view.findViewById(R.id.category);

        if(items.get(position) instanceof SearchResult) {

            SearchResult searchResult = (SearchResult) items.get(position);

            categoryTextView.setText("Category - " + searchResult.category);

            if (searchResult.content_name != null) {

                contentTextView.setText("Name - " + searchResult.content_name);

            } else if (searchResult.name != null) {

                contentTextView.setText("Name - " + searchResult.name);

            } else if (searchResult.title != null) {

                contentTextView.setText("Title - " + searchResult.title);
            }

            return view;
        }

        Movie movie = (Movie) items.get(position);

        categoryTextView.setText("Vote - " + movie.avgVote);
        contentTextView.setText(movie.title);

        return view;
    }

}
