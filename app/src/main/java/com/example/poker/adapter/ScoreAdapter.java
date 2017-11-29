package com.example.poker.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.poker.R;
import com.example.poker.model.ScoreItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GUNNER on 28/11/2560.
 */

public class ScoreAdapter extends ArrayAdapter<ScoreItem>{
    private Context context;
    private int layoutResId;
    ArrayList<ScoreItem> scoreItemList;

    public ScoreAdapter(@NonNull Context context, int layoutResId, @NonNull ArrayList<ScoreItem> scoreItemList) {
        super(context, layoutResId, scoreItemList);
        this.context = context;
        this.layoutResId = layoutResId;
        this.scoreItemList = scoreItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemLayout = inflater.inflate(layoutResId,null);

        ScoreItem item = scoreItemList.get(position);

        TextView rankText = itemLayout.findViewById(R.id.rank_id_text_view);
        TextView scoreText = itemLayout.findViewById(R.id.score_id_text_view);

        rankText.setText(String.valueOf(item.rank));
        scoreText.setText(String.valueOf(item.score));

        return itemLayout;
    }
}
