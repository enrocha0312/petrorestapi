package com.mindsim.petroapi.ftps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.net.ftp.FTPSClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {
    private String server;
    private int port;
    private String user;
    private String password;
    private FTPSClient ftpsClient;

    public void connectWithFtpsPetrobrasServer(){
            try {
                InetAddress petrobras = InetAddress.getByName("ftps.petrobras.com.br");

            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
    }

    public void connectWithLocalFTPServer(){

    }
}
