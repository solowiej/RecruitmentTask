import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;


class ScanerContentLoaderTest {
    private ScanerContentLoader scanerContentLoader;

    @BeforeEach
    void setUp() {
        scanerContentLoader = ScanerContentLoader.INSTANCE;
    }

    @Test
    void scanner() {
        //given
        String data = "Hello";
        InputStream stdin = System.in;
        String nextLine;

        //when
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            nextLine = scanerContentLoader.scanner().nextLine();
        } finally {
            System.setIn(stdin);
        }

        //then
        assertEquals("Hello", nextLine);
    }
}