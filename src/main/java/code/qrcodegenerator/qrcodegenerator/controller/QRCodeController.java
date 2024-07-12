package code.qrcodegenerator.qrcodegenerator.controller;

import code.qrcodegenerator.qrcodegenerator.service.QRCodeGeneratorService;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@RestController
@Slf4j
public class QRCodeController {

    @Autowired
    private QRCodeGeneratorService qrCodeGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<?> add(@RequestBody String message) throws IOException, WriterException {
        qrCodeGeneratorService.generateQRCode(message);
        log.info("Input Message is {} ", message);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
