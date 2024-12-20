package com.group34.View;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * AudioPlayer class to play audio files
 */
public class AudioPlayer {
    private Clip clip;
    private FloatControl volumeControl;
    private boolean isMuted = false;
    private float previousVolume = 0.0f;

    public AudioPlayer(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Play the audio file continuously
     * @return void
     */
    public void playLoop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }

    /**
     * Mute the audio
     * @return void
     */
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

    /**
     * Sets the volume of the audio
     * @param volume the volume to set
     * @return void
     */
    public void setVolume(float volume) {
        if (volumeControl != null) {
            volumeControl.setValue(volume);
        }
    }
}