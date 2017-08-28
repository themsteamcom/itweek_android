package com.example.themsteam.itweekdemo.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.themsteam.itweekdemo.R;

import butterknife.ButterKnife;

/**
 * Created by vshabanov on 21-Aug-17.
 */

public final class AchievementSnackbar extends android.support.design.widget.BaseTransientBottomBar<AchievementSnackbar> {

    public AchievementSnackbar(@NonNull ViewGroup parent, @NonNull View content, @NonNull ContentViewCallback contentViewCallback) {
        super(parent, content, contentViewCallback);
    }

    public static AchievementSnackbar make(ViewGroup parent, @Duration int duration) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View content = inflater.inflate(R.layout.snackbar_achievement, parent, false);

        ButterKnife.bind(content);

        ContentViewCallback callback = new ContentViewCallback(content);
        AchievementSnackbar customSnackbar = new AchievementSnackbar(parent, content, callback);

        customSnackbar.setDuration(duration);
        return customSnackbar;
    }


    public AchievementSnackbar setAction(final View.OnClickListener listener) {
        ImageView shareBtn = (ImageView) getView().findViewById(R.id.ivShare);
        shareBtn.setOnClickListener(view -> {
            listener.onClick(view);
            dismiss();
        });
        return this;
    }

    private static class ContentViewCallback implements BaseTransientBottomBar.ContentViewCallback {
        private View content;

        public ContentViewCallback(View content) {
            this.content = content;
        }

        @Override
        public void animateContentIn(int delay, int duration) {
            ViewCompat.setScaleY(content, 0f);
            ViewCompat.animate(content)
                    .scaleY(1f).setDuration(duration)
                    .setStartDelay(delay);
        }

        @Override
        public void animateContentOut(int delay, int duration) {
            ViewCompat.setScaleY(content, 1f);
            ViewCompat.animate(content)
                    .scaleY(0f)
                    .setDuration(duration)
                    .setStartDelay(delay);
        }
    }
}
