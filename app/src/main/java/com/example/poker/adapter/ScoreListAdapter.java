package com.example.poker.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poker.R;
import com.example.poker.model.ScoreItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GUNNER on 27/11/2560.
 */

public class ScoreListAdapter extends ArrayAdapter<ScoreItem>{
    private Context mContext;
    private int mLayoutResId;
    private ArrayList<ScoreItem> mScoreItemList;

    public ScoreListAdapter(@NonNull Context context, int layoutResId, @NonNull ArrayList<ScoreItem> scoreItemList) {
        super(context, layoutResId, scoreItemList);
        this.mContext = context;
        this.mLayoutResId = layoutResId;
        this.mScoreItemList = scoreItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemLayout = inflater.inflate(mLayoutResId, null);

        ScoreItem item = mScoreItemList.get(position);

        TextView rankText = itemLayout.findViewById(R.id.rank_id_text_view);
        TextView scoreText = itemLayout.findViewById(R.id.score_id_text_view);

        rankText.setText(item.rank);
        scoreText.setText(item.score);

        return itemLayout;
    }
}
