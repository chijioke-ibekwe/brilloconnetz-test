package dev.chijiokeibekwe.questionone.util;

import dev.chijiokeibekwe.questionone.data.UserData;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class InputUtil {

    private InputUtil(){}

    private static final Scanner scanner = new Scanner(System.in);

    public static UserData getUserDataFromConsole() {
        System.out.print("Enter username: ");
        String username = scanner.next();

        System.out.print("Enter email: ");
        String email = scanner.next();

        System.out.print("Enter password: ");
        String password = scanner.next();

        System.out.print("Enter date of birth (e.g 01-01-2000): ");
        String dateOfBirth = scanner.next();

        return new UserData(username, email, password, dateOfBirth);
    }

    public static String getJWTFromConsole() {
        System.out.print("Enter token: ");

        return scanner.next();
    }

    public static void close() {
        scanner.close();
    }

//    public static void validateUserData(UserData userData) {
//        Validator validator = Validation.byDefaultProvider()
//                .configure()
//                .messageInterpolator(new ParameterMessageInterpolator())
//                .buildValidatorFactory()
//                .getValidator();
//
//        Set<ConstraintViolation<UserData>> violations = validator.validate(userData);
//
//        if(violations.isEmpty()) {
//            return;
//        }
//
//        Map<String, String> violationMap = new HashMap<>();
//
//        violations.stream()
//                .collect(Collectors.groupingBy(ConstraintViolation::getPropertyPath)).values()
//                .forEach(gv -> {
//                    List<String> messages = new ArrayList<>();
//                    gv.forEach(v -> messages.add(v.getMessage()));
//                    violationMap.put(gv.get(0).getPropertyPath().toString(), String.join("; ", messages));
//                });
//
//        violationMap.forEach((key, value) -> System.err.println(StringUtils.capitalize(key) + ": " + value));
//
//        System.exit(1);
//    }

    public static void validateUserData(UserData userData) {
        Validator validator = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory()
                .getValidator();

        //validate user data properties concurrently
        Set<ConstraintViolation<UserData>> violations = Stream.of(userData.getClass().getDeclaredFields())
                .parallel()
                .map(f -> validator.validateProperty(userData, f.getName()))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        if(violations.isEmpty()) {
            return;
        }

        Map<String, String> violationMap = violations.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getPropertyPath().toString(),
                        Collectors.mapping(ConstraintViolation::getMessage, Collectors.joining("; "))
                ));

        violationMap.forEach((key, value) -> System.err.println(StringUtils.capitalize(key) + ": " + value));

        System.exit(1);
    }
}