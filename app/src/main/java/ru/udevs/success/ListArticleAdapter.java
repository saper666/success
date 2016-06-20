package ru.udevs.success;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListArticleAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ListArticleAdapter(Context context) {
        ctx = context;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return Article.sortArt.size();
    }
    @Override
    public Object getItem(int position) {
        return Article.sortArt.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.itemarticle, parent, false);
        }

        Article p = getArticle(position);

        ((TextView) view.findViewById(R.id.authorlst)).setText(p.Author);
        ((TextView) view.findViewById(R.id.titlelst)).setText(p.Title);
        return view;
    }
    Article getArticle(int position) {
        return ((Article) getItem(position));
    }
}
