package com.raven.video_player;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.swing.TransferHandler;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class MyTransferHandler extends TransferHandler {

    private final DataFlavor uriListFlavor;
    private final DataFlavor javaUrlFlavor;
    private final DataFlavor javaFileListFlavor;
    private final EmbeddedMediaPlayer mediaPlayer;
    private final EventTransferHandler event;

    public MyTransferHandler(EmbeddedMediaPlayer mediaPlayer, EventTransferHandler event) throws ClassNotFoundException {
        uriListFlavor = new DataFlavor("text/uri-list;class=java.lang.String");
        javaUrlFlavor = new DataFlavor("application/x-java-url;class=java.net.URL");
        javaFileListFlavor = DataFlavor.javaFileListFlavor;
        this.mediaPlayer = mediaPlayer;
        this.event = event;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        return getDataFlavor(support) != null;
    }

    @Override
    public boolean importData(TransferSupport support) {
        DataFlavor flavor = getDataFlavor(support);
        if (flavor != null) {
            try {
                Object transferData = support.getTransferable().getTransferData(flavor);
                if (transferData instanceof String) {
                    String value = (String) transferData;
                    String[] uris = value.split("\\r\\n");
                    if (uris.length > 0) {
                        String uri = uris[0];
                        mediaPlayer.media().play(uri);
                        if (event != null) {
                            event.execute(uri);
                        }
                    }
                    return true;
                } else if (transferData instanceof URL) {
                    URL value = (URL) transferData;
                    String uri = value.toExternalForm();
                    mediaPlayer.media().play(uri);
                } else if (transferData instanceof List) {
                    List<?> value = (List<?>) transferData;
                    if (value.size() > 0) {
                        File file = (File) value.get(0);
                        String uri = file.getAbsolutePath();
                        mediaPlayer.media().play(uri);
                        if (event != null) {
                            event.execute(uri);
                        }
                    }
                }
            } catch (UnsupportedFlavorException | IOException e) {
                System.err.println(e.getMessage());
            }
        }
        return false;
    }

    private DataFlavor getDataFlavor(TransferSupport support) {
        if (support.isDataFlavorSupported(uriListFlavor)) {
            return uriListFlavor;
        }
        if (support.isDataFlavorSupported(javaUrlFlavor)) {
            return javaUrlFlavor;
        }
        if (support.isDataFlavorSupported(javaFileListFlavor)) {
            return javaFileListFlavor;
        }
        return null;
    }
}
