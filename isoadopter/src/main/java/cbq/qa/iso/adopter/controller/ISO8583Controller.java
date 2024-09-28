package cbq.qa.iso.adopter.controller;

import java.io.IOException;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cbq.qa.iso.adopter.service.ISO8583MessageService;

@RestController
public class ISO8583Controller {

    @Autowired
    private ISO8583MessageService messageService;

    @PostMapping("/send")
    public void sendMessage(@RequestParam String messageType, @RequestParam String amount, @RequestParam String dateTime) throws IOException, ISOException  {
        messageService.send("343", "34", "34");
    }

    @GetMapping("/receive")
    public ISOMsg receiveMessage() throws IOException, ISOException {
        return messageService.receive();
    }
}