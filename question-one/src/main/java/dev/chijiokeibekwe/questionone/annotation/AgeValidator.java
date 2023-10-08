package dev.chijiokeibekwe.questionone.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AgeValidator implements ConstraintValidator<Age, String> {

    private Age age;

    @Override
    public void initialize(Age age) {
        this.age = age;
    }

    @Override
    public boolean isValid(String dateString, ConstraintValidatorContext cxt) {
        try {
            LocalDate dateOfBirth = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return dateOfBirth.isBefore(LocalDate.now().minusYears(age.min()));
        } catch (DateTimeParseException ex) {
            cxt.disableDefaultConstraintViolation();
            cxt.buildConstraintViolationWithTemplate("Date should be in the format - dd-MM-yyyy").addConstraintViolation();
            return false;
        }
    }
}
