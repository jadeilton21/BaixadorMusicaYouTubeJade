import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;

public class BaixadorMusicaYouTubeJade extends JFrame {

    private JTextField urlFieldYouTube;
    private JTextField urlFieldFilme;
    private JButton downloadYouTubeButton;
    private JButton downloadFilmeButton;

    public BaixadorMusicaYouTubeJade() {
        setTitle("Baixador de Música e Filmes - Jade");
        setSize(450, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        // Linha 1 - YouTube
        JPanel youtubePanel = new JPanel(new FlowLayout());
        JLabel labelYT = new JLabel("URL do vídeo do YouTube:");
        urlFieldYouTube = new JTextField(25);
        downloadYouTubeButton = new JButton("Baixar Áudio");
        downloadYouTubeButton.addActionListener(this::baixarAudioYouTube);
        youtubePanel.add(labelYT);
        youtubePanel.add(urlFieldYouTube);
        youtubePanel.add(downloadYouTubeButton);

        // Linha 2 - Filme
        JPanel filmePanel = new JPanel(new FlowLayout());
        JLabel labelFilme = new JLabel("URL do filme (direta .mp4):");
        urlFieldFilme = new JTextField(25);
        downloadFilmeButton = new JButton("Baixar Filme");
        downloadFilmeButton.addActionListener(this::baixarFilmeDireto);
        filmePanel.add(labelFilme);
        filmePanel.add(urlFieldFilme);
        filmePanel.add(downloadFilmeButton);

        // Adiciona os painéis
        add(youtubePanel);
        add(filmePanel);
    }

    private void baixarAudioYouTube(ActionEvent e) {
        String url = urlFieldYouTube.getText().trim();
        String destino = System.getProperty("user.home") + "\\Desktop\\MusicasYouTube";
        new java.io.File(destino).mkdirs();

        if (url.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe a URL do YouTube!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "yt-dlp", "-x", "--no-playlist",
                    "--audio-format", "mp3",
                    "-o", destino + "\\%(title)s.%(ext)s",
                    url
            );
            pb.inheritIO();
            Process p = pb.start();
            p.waitFor();

            JOptionPane.showMessageDialog(this, "Download concluído!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao baixar do YouTube: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void baixarFilmeDireto(ActionEvent e) {
        String url = urlFieldFilme.getText().trim();
        String destino = System.getProperty("user.home") + "\\Desktop\\FilmesJade";
        new java.io.File(destino).mkdirs();

        if (url.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe a URL direta do arquivo .mp4!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (InputStream in = new URL(url).openStream()) {
            String nomeArquivo = "filme_" + System.currentTimeMillis() + ".mp4";
            Files.copy(in, Paths.get(destino + "\\" + nomeArquivo), StandardCopyOption.REPLACE_EXISTING);

            JOptionPane.showMessageDialog(this, "Filme baixado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao baixar o filme: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BaixadorMusicaYouTubeJade().setVisible(true));
    }
}
