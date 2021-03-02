package org.kpn.ch3.xml.transfer;

public class FtpArtworkSender  implements ArtworkSender{

    @Override
    public void sendArtwork(String artworkPath, Recipient recipient) {
        recipient.execute(artworkPath);
    }

    @Override
    public String getFriendlyName() {
        return "File Transfer Protocol";
    }

    @Override
    public String getShortName() {
        return "ftp";
    }
}
