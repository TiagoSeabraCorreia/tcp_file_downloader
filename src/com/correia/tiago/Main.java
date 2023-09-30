package com.correia.tiago;

import com.correia.tiago.data.TCPFileDownloader;

import java.io.IOException;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) throws IOException {
        TCPFileDownloader downloader = new TCPFileDownloader("localhost", 6000, "A.jpeg", 200);
        downloader.sendFileName();
        downloader.sendChunkSize();
        downloader.waitForResponse();
        downloader.downloadFile();
    }
}