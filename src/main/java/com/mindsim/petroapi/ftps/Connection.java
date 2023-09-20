package com.mindsim.petroapi.ftps;

import org.apache.commons.net.ftp.FTPSClient;

import java.io.IOException;
import java.net.InetAddress;

public class Connection {
    public static void connect(){
        Client client = new Client("ftps://ftps.petrobras.com.br/Mindsim", 990, "fmurad.mindsim", "04pBQaWvtV", new FTPSClient());
        try {
            client.getFtpsClient().connect(InetAddress.getByName(client.getServer()));
            client.getFtpsClient().login(client.getUser(), client.getPassword());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
