package player;

import javazoom.jlgui.basicplayer.BasicPlayer;
import java.io.File;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 *
 * @author larios
 */
public class Player {
    private final BasicPlayer player;
    private final String path;
    
    public Player(String path) {
        player = new BasicPlayer();
        this.path = path;
    }
    
    public void play() throws BasicPlayerException{
        player.open(new File(path));
        player.play();
    }
    
    public void openFile() throws BasicPlayerException {
        player.open(new File(path));
    }

    public void pause() throws BasicPlayerException {
        player.pause();
    }

    public void resume() throws BasicPlayerException {
        player.resume();
    }

    public void stop() throws BasicPlayerException {
        player.stop();
    }
    
    public String getName() {
        return new File(path).getName().split("\\.")[0];
    }
}
