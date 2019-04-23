package pacman;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class SoundBoard {
    private static final String AUDIO_PATH = "media/audio/";

    static final Media START =
            new Media(new File(AUDIO_PATH + "start.wav").toURI().toString());

    static final Media CHOMP =
            new Media(new File(AUDIO_PATH + "chomp.wav").toURI().toString());

    static final Media END =
            new Media(new File(AUDIO_PATH + "end.wav").toURI().toString());

    static final Media EAT =
            new Media(new File(AUDIO_PATH + "eat.wav").toURI().toString());

    private static final Map<Media,MediaPlayer> MEDIA = new HashMap<>();

    static void play(Media media) {
        if (!MEDIA.containsKey(media)) {
            MediaPlayer player = new MediaPlayer(media);
            player.setOnEndOfMedia(player::stop);
            MEDIA.put(media, player);
        }
        MediaPlayer player = MEDIA.get(media);
        player.play();
    }
}
