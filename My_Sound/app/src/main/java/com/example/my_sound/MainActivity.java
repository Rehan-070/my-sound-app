package com.example.my_sound;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBarRingtone, seekBarMedia, seekBarAlarm;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize AudioManager
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Initialize SeekBars
        seekBarRingtone = findViewById(R.id.seekBarRingtone);
        seekBarMedia = findViewById(R.id.seekBarMedia);
        seekBarAlarm = findViewById(R.id.seekBarAlarm);

        // Setup SeekBars
        setupSeekBar(seekBarRingtone, AudioManager.STREAM_RING);
        setupSeekBar(seekBarMedia, AudioManager.STREAM_MUSIC);
        setupSeekBar(seekBarAlarm, AudioManager.STREAM_ALARM);
    }

    private void setupSeekBar(SeekBar seekBar, int streamType) {
        // Get maximum and current volume for the stream type
        int maxVolume = audioManager.getStreamMaxVolume(streamType);
        int currentVolume = audioManager.getStreamVolume(streamType);

        seekBar.setMax(maxVolume);
        seekBar.setProgress(currentVolume);

        // Set a listener to handle changes in volume
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    audioManager.setStreamVolume(streamType, progress, AudioManager.FLAG_SHOW_UI);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}