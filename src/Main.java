import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;



public class Main {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {


        JFrame jframe = new JFrame();
        WorldsHardestGame worldsHardestGame = new WorldsHardestGame();
        jframe.setBounds(100,100,1200,800);
        jframe.setTitle("World's hardest game");
        jframe.setVisible(true);
        jframe.setResizable(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(worldsHardestGame);


            File file = new File("music.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
    }



}
