package com.wikisearch.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wikisearch.android.R;
import com.wikisearch.android.entity.WikiBaseObject;
import com.wikisearch.android.wikiapplication.WikiSearchApplication;
import com.wikisearch.android.wikiservice.pagesearch.WikiPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veena.joshi on 1/24/2016.
 */
public class WikiImageListRecyclerViewAdapter extends RecyclerView.Adapter<WikiImageListRecyclerViewAdapter.ViewHolder> {

    private List<WikiBaseObject> wikiPageList = new ArrayList<WikiBaseObject>();

    public void setWikiPageList(List<WikiBaseObject> pageList){
        wikiPageList = pageList;
    }
    public void clearWikiPageList(){
        wikiPageList.clear();
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wiki_img_item, null);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        WikiPage page = (WikiPage)wikiPageList.get(position);
        viewHolder.pageTitle.setText(page.getTitle());

        String url = "";
        if(page.getThumbnail() != null){

            if( page.getThumbnail().getSource() != null) {
                url = page.getThumbnail().getSource();

                Picasso.with(WikiSearchApplication.getInstance().getApplicationContext())
                        .load(url)
                        .placeholder(R.drawable.wiki)
                        .into(viewHolder.pageImage);
            }
        }
        else {
            viewHolder.pageImage.setImageResource(R.drawable.wiki);
        }

    }

    @Override
    public int getItemCount() {
        return wikiPageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView pageTitle;

        public ImageView pageImage;

        public ViewHolder( View view ){
            super(view);
            view.setOnClickListener(this);
            pageTitle = (TextView)view.findViewById(R.id.page_title);
            pageImage = (ImageView)view.findViewById(R.id.page_img);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
