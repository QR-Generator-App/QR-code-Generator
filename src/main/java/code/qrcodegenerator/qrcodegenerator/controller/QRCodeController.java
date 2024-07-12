package code.qrcodegenerator.qrcodegenerator.controller;

import code.qrcodegenerator.qrcodegenerator.service.QRCodeGeneratorService;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@RestController
@Slf4j
@CrossOrigin("http://localhost:4200")
public class QRCodeController {

    @Autowired
    private QRCodeGeneratorService qrCodeGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<String > add(@RequestBody String message) throws IOException, WriterException {
        String qrCodeBase64 = qrCodeGeneratorService.generateQRCode(message);
        log.info("Input Message is {} ", message);
        return ResponseEntity.ok(qrCodeBase64);
    }
}
