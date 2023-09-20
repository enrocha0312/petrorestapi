package com.mindsim.petroapi.ftps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.net.ftp.FTPSClient;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {
    private String server;
    private int port;
    private String user;
    private String password;
    private FTPSClient ftpsClient;
}
