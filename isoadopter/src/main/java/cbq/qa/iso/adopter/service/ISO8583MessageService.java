package cbq.qa.iso.adopter.service;

import java.io.IOException;

import org.jpos.iso.*;
import org.springframework.stereotype.Service;

import cbq.qa.iso.adopter.config.TCPIPConnection;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ISO8583MessageService {

    private final TCPIPConnection connection;
    private final ISO8583MessageGenerator messageGenerator;

    public void send(String messageType, String amount, String dateTime) throws IOException, ISOException {
    	ISOMsg msg = messageGenerator.generateMessage(messageType, amount, dateTime);
        byte[] data = msg.pack();
        connection.send(data);
    }

    public ISOMsg receive() throws ISOException, IOException {
        byte[] data = connection.receive();
        ISOMsg msg = new ISOMsg();
        msg.unpack(data);
        return msg;
    }
}
