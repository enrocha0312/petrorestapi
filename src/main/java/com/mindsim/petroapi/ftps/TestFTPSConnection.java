package com.mindsim.petroapi.ftps;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;


import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class TestFTPSConnection {
    public static void main(String[] args){
        InetAddress petrobras= null;
        try {
            petrobras = InetAddress.getByName("ftps.petrobras.com.br");
            System.out.println("Adress " + petrobras.getHostAddress());
            System.out.println("Host name " + petrobras.getHostName());
            Socket socket = new Socket(petrobras, 990);
            System.out.println("Socket funcionando");
            FTPSClient ftpsClient = new FTPSClient("TLS", true);
            ftpsClient.setBufferSize(1024*1024);
            ftpsClient.setConnectTimeout(100000);
            ftpsClient.connect(InetAddress.getByName(petrobras.getHostAddress()));
            ftpsClient.setSoTimeout(100000);
            if(!ftpsClient.isConnected()){
                System.out.println("Server not connected");
                return;
            }
            if(!ftpsClient.login("fmurad.mindsim", "04pBQaWvtV")){
                System.out.println("invalid user");
            }else{
                ftpsClient.login("fmurad.mindsim", "04pBQaWvtV");
                System.out.println("User accepted");
            }

            String[] enabledProtocols = new String[] {"TLS","TLSv1.2", "TLSv1.3", "TLSv1.1", "TLSv1", "SSLv3", "SSLv3"};
            ftpsClient.setEnabledProtocols(enabledProtocols);
            ftpsClient.setAuthValue("TLS");
            try {
                ftpsClient.enterLocalPassiveMode();
                ftpsClient.setKeepAlive(true);
                Integer porta1 = ftpsClient.getPassivePort();
                ftpsClient.changeWorkingDirectory("\\Mindsim");
                FTPFile[] diretorios = ftpsClient.listDirectories();
                ftpsClient.retrieveFile("texte.txt", new FileOutputStream("C:\\Users\\Eduardo\\novatentativa.txt"));
                Long numerosDeDiretorios = Arrays.stream(diretorios).count();
                FTPFile[] listaDeArquivos = ftpsClient.listFiles();
                String[] listaDeNomes = ftpsClient.listNames("\\Mindsim");
            }catch (Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            System.out.println("ok");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
