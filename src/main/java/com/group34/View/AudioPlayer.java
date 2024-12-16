package com.group34.View;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private Clip clip;
    private FloatControl volumeControl;
    private boolean isMuted = false;
    private float previousVolume = 0.0f;

    public AudioPlayer() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("target/classes/assets/Audio/smurf_theme.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playLoop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }

    public void mute() {
        if (volumeControl != null) {
            if (isMuted) {
                volumeControl.setValue(previousVolume);
                isMuted = false;
            } else {
                previousVolume = volumeControl.getValue();
                volumeControl.setValue(volumeControl.getMinimum());
                isMuted = true;
            }
        }
    }

    public void setVolume(float volume) {
        if (volumeControl != null) {
            volumeControl.setValue(volume);
        }
    }
}