package org.kpn.ch3.xml.transfer;

public class RecipientImpl implements Recipient{

    @Override
    public void execute(String artworkPath) {
        System.out.println("recipient execute " + artworkPath);
    }
}
