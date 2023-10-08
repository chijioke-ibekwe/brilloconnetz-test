package dev.chijiokeibekwe.questionone;

import dev.chijiokeibekwe.questionone.data.UserData;
import dev.chijiokeibekwe.questionone.util.JwtUtil;
import dev.chijiokeibekwe.questionone.util.InputUtil;

public class QuestionOneApp
{
    public static void main( String[] args )
    {
        UserData userData = InputUtil.getUserDataFromConsole();

        InputUtil.validateUserData(userData);

        JwtUtil.generateSignedJWT(userData);

        String token = InputUtil.getJWTFromConsole();

        JwtUtil.verifyJWT(token);

        InputUtil.close();
    }
}