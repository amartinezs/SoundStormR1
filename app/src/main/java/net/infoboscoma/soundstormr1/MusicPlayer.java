package net.infoboscoma.soundstormr1;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;
import java.io.Serializable;

import Model.Song;

/**
 * Created by Albert on 20/02/2015.
 */
public class MusicPlayer implements Serializable {

    MediaPlayer mediaPlayer;
    Song nowPlayingSong;


    public MusicPlayer(Song song){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        nowPlayingSong = song;
    }

    public void startMusic(Context context){
        try {
            mediaPlayer.setDataSource(context, nowPlayingSong.getURI());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void pauseMusic(){
        mediaPlayer.pause();
    }

    public void stopMusic(){
        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public boolean isPlaying(){
       return mediaPlayer.isPlaying();
    }


}
