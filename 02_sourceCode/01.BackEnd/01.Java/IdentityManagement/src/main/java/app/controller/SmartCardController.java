package app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.exception.CardReadException;
import app.model.CardReadModel;
import app.service.CardReadService;

/**
 * SmartCardController
 *
 * @author Kenji Nagai
 *
 */
@RestController
public class SmartCardController {

    @Autowired
    CardReadService cardReadService;

    @RequestMapping(value = "/card/id", method = RequestMethod.GET)
    public ResponseEntity<CardReadModel> getCardId() {
        try {
            final CardReadModel resModel = cardReadService.getCardInfo();
            return new ResponseEntity<CardReadModel>(resModel, HttpStatus.OK);
        } catch (CardReadException | IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
