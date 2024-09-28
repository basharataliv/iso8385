package cbq.qa.iso.adopter.config;

import java.io.*;
import java.net.*;

import org.springframework.stereotype.Service;
@Service
public class TCPIPConnection {

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public void connect(String host, int port) throws IOException {
        socket = new Socket("localhost", 5051);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    public void send(byte[] data) throws IOException {
        outputStream.write(data);
        outputStream.flush();
    }

    public byte[] receive() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int bytesRead;
        byte[] buffer = new byte[1024];
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toByteArray();
    }

    public void close() throws IOException {
        socket.close();
    }
}
