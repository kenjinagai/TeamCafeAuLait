package app.util;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.service.CardReadService;

@Service
public class UtilValidator {
    Validator validator;
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilValidator.class);

    @Autowired
    CardReadService service;

    @PostConstruct
    public void init() {
        // Validator を取得
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public <T> boolean hasConstraintViolations(final T object, final Class<?>... groups) {
        final Set<ConstraintViolation<T>> constraintViolations = validator.validate(object, groups);
        this.logConstraintViolation(constraintViolations);
        return !constraintViolations.isEmpty();
    }

    private <T> void
            logConstraintViolation(final Set<ConstraintViolation<T>> constraintViolations) {
        if (!constraintViolations.isEmpty()) {
            for (final ConstraintViolation<T> constraintViolation : constraintViolations) {
                LOGGER.error("Constraint Violation occurred.");
                LOGGER.error("RootBeanClass: {}", constraintViolation.getRootBeanClass());
                LOGGER.error("PropertyPath: {}", constraintViolation.getPropertyPath());
                LOGGER.error("InvalidValue: {}", constraintViolation.getInvalidValue());
                LOGGER.error("Message: {}", constraintViolation.getMessage());
            }
        }
    }
}
