package dev.chijiokeibekwe.questionone;

import dev.chijiokeibekwe.questionone.util.JwtUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JwtTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errorStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOutput = System.out;
    private final PrintStream standardError = System.err;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setErr(new PrintStream(errorStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOutput);
        System.setErr(standardError);
    }

    @Test
    public void testVerifyJwt_whenValidTokenIsProvided() {

        String validToken = "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImNraUBnbWFpbC5jb20iLCJ1c2VybmFtZSI6ImNraUBnbWFpbC5jb20" +
                "iLCJpYXQiOjE2OTY1NDE2MTAsInN1YiI6ImNraUBnbWFpbC5jb20ifQ.wwGy01-32BeND_cMVbaWPNjl3nwlKp1_yUobIv2p9io";

        JwtUtil.verifyJWT(validToken);

        assertEquals("\nVerification pass", outputStreamCaptor.toString());
    }

    @Test
    public void testVerifyJwt_whenInvalidTokenIsProvided() throws Exception {

        String invalidToken = "eyJlbWFpbCI6ImNraUBnbWFpbC5jb20iLCJ1c2VybmFtZSI6ImNraUBnbWFpbC5jb20";

        int statusCode = catchSystemExit(() -> {
            JwtUtil.verifyJWT(invalidToken);
        });

        assertEquals(1, statusCode);
        assertEquals("\nVerification fails", errorStreamCaptor.toString());
    }
}
