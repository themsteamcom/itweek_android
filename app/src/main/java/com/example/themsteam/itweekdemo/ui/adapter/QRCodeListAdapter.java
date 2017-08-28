package com.example.themsteam.itweekdemo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.themsteam.itweekdemo.R;
import com.example.themsteam.itweekdemo.data.QRCodeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vshabanov on 15-Aug-17.
 */

public class QRCodeListAdapter extends RecyclerView.Adapter<QRCodeListAdapter.ViewHolder> {

    private final List<QRCodeModel> qrCodes = new ArrayList<>();

    public void setQrCodes(List<QRCodeModel> list) {
        qrCodes.clear();
        qrCodes.addAll(list);
        this.notifyDataSetChanged();
    }

    public List<QRCodeModel> getQrCodes() {
        return qrCodes;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barcode, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(qrCodes.get(position).getTitle());
        holder.tvNote.setText(qrCodes.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return qrCodes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvNotes)
        TextView tvNote;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
 }
