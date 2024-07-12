package code.qrcodegenerator.qrcodegenerator.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class QRCodeGeneratorService {

    @Value("${qrcode.output.directory}")
    private String outputLocation;
    @Value("${qrcode.message}")
    private String qrCodeMessage;

    private static final String CHARSET = "UTF-8";
    private static final String strDateFormat = "yyyyMMddhhmmss";

    public void generateQRCode(String message) throws IOException, WriterException {
        String finalMessage = (StringUtils.isNotBlank(message)) ? qrCodeMessage : message;
        processQRCode(finalMessage, prepareOutputFileName());
    }

    private void processQRCode(String data, String path) throws WriterException,IOException {
        BitMatrix matrix = new MultiFormatWriter()
                .encode(new String(data.getBytes(QRCodeGeneratorService.CHARSET),
                        QRCodeGeneratorService.CHARSET),
                        BarcodeFormat.QR_CODE, 400, 400);
        MatrixToImageWriter.writeToFile(matrix,
                path.substring(path.lastIndexOf('.') + 1),new File(path));
    }

    private String prepareOutputFileName() {
        Date date = new Date();

        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);

        return outputLocation + "\\" + formattedDate + ".png";
    }
}
