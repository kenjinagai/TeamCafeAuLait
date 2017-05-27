package app.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import app.constant.Constants;
import app.exception.CardReadException;
import app.model.CardReadModel;

/**
 * Smart card service.
 *
 * @author Kenji Nagai
 *
 */
@Service
public class CardReadService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CardReadService.class);

    /**
     * Get Samart card infomation.
     *
     * @return CardReadModel
     * @throws CardReadException Card read failure
     * @throws IOException IOException
     */
    public CardReadModel getCardInfo() throws CardReadException, IOException {
        try {
            final Process process = callPythonScript();
            // Read std output
            final String stdOut = IOUtils.toString(process.getInputStream(),
                    Charset.defaultCharset());

            // Standard output is IDm
            if (isIdm(stdOut)) {
                LOGGER.info("Get Id successfully.");
                return new CardReadModel(parseIdm(stdOut));
                //Error
            } else {
                // Read std error
                final String errorMessage = IOUtils.toString(process.getErrorStream(),
                        Charset.defaultCharset());
                LOGGER.error("Error Stream: ", errorMessage);
                throw new CardReadException("Card read error");
            }
        } catch (final InterruptedException e2) {
            throw new CardReadException(e2.getMessage());
        }
    }

    /**
     * Call Python script of NFC.
     *
     * @return Stand Output of PythonScript.
     * @throws IOException
     * @throws InterruptedException
     */
    private Process callPythonScript() throws InterruptedException, IOException {
        // Register Python command
        final ProcessBuilder processBuilder = new ProcessBuilder(Constants.CMD_PYTHON,
                Constants.FILE_PYTHON_CARD_READ);

        final Process process = processBuilder.start();
        process.waitFor(Constants.MILLISECOUND_WAIT_CMD, TimeUnit.MILLISECONDS);
        return process;
    }

    /**
     * Whether stand out is IDm.
     *
     * @param stdOut Stand out of Python script.
     * @return Whether stand out is IDm.
     */
    private Boolean isIdm(final String stdOut) {
        return (stdOut != null) && (stdOut.length() > 0);
    }

    /**
     * Parse Stdout to IDm of smart card.
     *
     * @param stdOut Stand out of Python script.
     * @return IDm of smart card.
     */
    private String parseIdm(final String stdOut) {
        if (stdOut.endsWith(System.getProperty("line.separator"))) {
            return stdOut.replace(System.getProperty("line.separator"), "");
        } else {
            return stdOut;
        }
    }
}
