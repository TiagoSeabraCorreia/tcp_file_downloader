package com.correia.tiago.data;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class TCPFileDownloader {
    String fileName;
    Socket socket;
    OutputStream fos;
    InputStream fis;
    Integer nChunks;
    Integer chunk;
    String ip;
    Integer port;
    FileInputStream fileInputStream;
    public TCPFileDownloader(String ip, int port, String fileName, Integer chunk) throws IOException {
        this.socket = new Socket(ip, port);
        fis = socket.getInputStream();
        fos = socket.getOutputStream();
        this.chunk = chunk;
        this.fileName = fileName;
    }

    public void sendFileName() throws IOException {
        this.fos.write(fileName.getBytes());
        System.out.println("Sent the file name");
    }

    public void sendChunkSize() throws IOException {
        this.fos.write(String.valueOf(chunk).getBytes());
        System.out.println("Sent the file name");
    }
    public void waitForResponse() throws IOException {
        byte[] data = new byte[256];
        this.fis.read(data);
        String nChunks = new String(data).trim();
        System.out.println("Received number of chunks: " + nChunks);
        this.nChunks = Integer.parseInt(nChunks);
    }

    public void downloadFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("./files/" + fileName);
        for (int i = 0; i < nChunks; i++) {
            byte[] data = new byte[chunk];
            fis.read(data);
            fileOutputStream.write(data);
        }
    }
}
