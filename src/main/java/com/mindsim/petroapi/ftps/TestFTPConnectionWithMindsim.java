package com.mindsim.petroapi.ftps;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestFTPConnectionWithMindsim {
    public static void main(String[] args){
        try{
            InetAddress mindsim = InetAddress.getLocalHost();
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(mindsim, 21);
            ftpClient.login("ftp-user", "mindsim");
            String[] arquivosNomes = ftpClient.listNames();
            System.out.println("Ok");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
