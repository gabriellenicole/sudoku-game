package sudoku;

import java.io.*;
import javax.sound.sampled.*;

/**
 * This enum encapsulates all the sound effects of a game, so as to separate the
 * sound playing
 * codes from the game codes.
 * 1. Define all your sound effect names and the associated wave file.
 * 2. To play a specific sound, simply invoke SoundEffect.SOUND_NAME.play().
 * 3. You might optionally invoke the static method SoundEffect.init() to
 * pre-load all the
 * sound files, so that the play is not paused while loading the file for the
 * first time.
 * 4. You can use the static variable SoundEffect.volume to mute the sound.
 */
public enum SoundEffect {
   BACKGROUND("bin/audio/justkidding.wav"), // explosion
   CLICK("bin/audio/click.wav"), // gong
   CORRECT("bin/audio/correct.wav"), // bullet
   HINT("bin/audio/hint.wav"),
   WRONG("bin/audio/wrong.wav");

   // Nested class for specifying volume
   public static enum Volume {
      MUTE, LOW, MEDIUM, HIGH
   }

   public static Volume volume = Volume.HIGH;

   // Each sound effect has its own clip, loaded with its own sound file.
   private Clip clip;

   // Constructor to construct each element of the enum with its own sound file.
   SoundEffect(String soundFileName) {
      try {
         // Use URL (instead of File) to read from disk and JAR.
         File soundFile = new File(soundFileName);
         // Set up an audio input stream piped from the sound file.
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
         // Get a clip resource.
         clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioInputStream);
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }

   public void mute() {
      if (clip.isRunning())
         clip.stop();
   }

   public void setVolume(int volumeInt) {
      if (volumeInt == 0) {
         volume = Volume.MUTE;

      }
      if (volumeInt == 1) {
         volume = Volume.LOW;
      }
      if (volumeInt == 2) {
         volume = Volume.MEDIUM;
      }
      if (volumeInt == 3) {
         volume = Volume.HIGH;
      }
   }

   // Play or Re-play the sound effect from the beginning, by rewinding.
   public void play() {
      if (volume != Volume.MUTE) {
         if (clip.isRunning())
            clip.stop(); // Stop the player if it is still running
         clip.setFramePosition(0); // rewind to the beginning
         clip.start(); // Start playing
      }
   }

   public void playLoop() {
      if (volume != Volume.MUTE) {
         if (clip.isRunning())
            clip.stop(); // Stop the player if it is still running
         clip.setFramePosition(0); // rewind to the beginning
         clip.start(); // Start playing
         clip.loop(Clip.LOOP_CONTINUOUSLY);
      }
   }

   // Optional static method to pre-load all the sound files.
   static void init() {
      values(); // calls the constructor for all the elements
   }
}