package com.buyup.myviewpager2;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceFactory;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

/**
 * Copyright (C), buyuphk物流中转站
 * author: JianfeiMa
 * email: majianfei93@163.com
 * revised: 2020-10-20 13:57
 * motto: 勇于向未知领域探索
 */
public class MyFragment extends Fragment {
    private int position;

    private PlayerView playerView;
    private ExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private ProgressBar progressBar;

    MyFragment(int position) {
        super();
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        playerView = view.findViewById(R.id.activity_main_player_view);
        //playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
        TextView tvSkip = view.findViewById(R.id.fragment_video_skip);
        String title = "第" + position + "Fragment";
        tvSkip.setText(title);
        progressBar = view.findViewById(R.id.fragment_video_progress_bar);
        initializePlayer();
        initializePlayer1();
        return view;
    }

    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultRenderersFactory(getContext()), new DefaultTrackSelector());
        playerView.setPlayer(player);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        long duration = player.getDuration();
        Log.d("debug", "总长:" + duration);
        player.addListener(new ExoPlayer.EventListener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                Log.d("debug", "playWhenReady--->" + playWhenReady + "playbackState--->" + playbackState);
                if (playbackState == 4) {
                    //WelcomeActivity welcomeActivity = (WelcomeActivity) getActivity();
                    //welcomeActivity.setReplaceFragment(new ViewPagerFragment());
                }
                if (playbackState == 3) {
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingChanged(boolean isLoading) {
                if (isLoading) {
                    //ProgressBar progressBar = new ProgressBar(getContext());
                    //progressBar.setIndeterminateDrawable(getActivity().getResources().getDrawable(R.drawable.progressbar));
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }

    private void initializePlayer1() {
        //创建一个mp4媒体文件
        //Uri uri = Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        MainActivity mainActivity = (MainActivity) getActivity();
        String url = mainActivity.getUrl(position);
        Uri uri = Uri.parse(url);
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("exoplayer-codelab")).createMediaSource(uri);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }
}
