package app.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import app.exception.CardReadException;
import app.model.CardReadModel;

@Service
public class CardReadService {
    private static Logger log = LoggerFactory.getLogger(CardReadService.class);
    private static final String CMD_PYTHON = "python2";
    private static final String FILE_PYTHON_CARD_READ = "/opt/get-card-id.py";
    private static final int MILLISECOUND_WAIT_CMD = 3000;

    public CardReadModel getIdm() throws CardReadException, IOException {
        try {
            // Register Python command
            ProcessBuilder processBuilder = new ProcessBuilder(CMD_PYTHON, FILE_PYTHON_CARD_READ);

            Process process = processBuilder.start();
            process.waitFor(MILLISECOUND_WAIT_CMD, TimeUnit.MILLISECONDS);

            // Read std output
            String idm = IOUtils.toString(process.getInputStream(), Charset.defaultCharset());

            //Success
            if (idm != null && idm.length() > 0) {
                log.info("Get Id successfully.");
                if (idm.endsWith(System.getProperty("line.separator"))) {
                    idm = idm.replace(System.getProperty("line.separator"), "");
                }
                return new CardReadModel(idm);
                //Error
            } else {
                // Read std error
                String errorMessage = IOUtils.toString(process.getErrorStream(),
                        Charset.defaultCharset());
                log.error("Error Stream: ", errorMessage);
                throw new CardReadException("Card read error");
            }
        } catch (InterruptedException e2) {
            throw new CardReadException(e2.getMessage());
        }
    }
}
