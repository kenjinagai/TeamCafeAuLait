package app.service;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import app.exception.CardReadException;
import app.model.CardReadModel;

public class CardReadServiceTest {
    //    @Rule
    //    public OutputCapture capture;

    @InjectMocks
    private CardReadService testTarget;
    //    private final CardReadService testTarget = spy(new CardReadService());

    @Mock
    private ProcessBuilder pb;
    @Mock
    private Process sucProc;
    @Mock
    private Process failProc;
    //    @Mock
    private IOUtils utilMock;
    //
    private String sucIdm;
    private String sucStdOut;
    private String failErrOut;
    private CardReadModel sucModel;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
        sucIdm = "SucIDm";
        sucStdOut = sucIdm + System.getProperty("line.separator");
        failErrOut = "Exception";
        //        capture = new OutputCapture();

        pb = mock(ProcessBuilder.class);

        when(pb.start()).thenReturn(sucProc);
        when(sucProc.waitFor(anyLong(), eq(TimeUnit.MILLISECONDS))).thenReturn(true);
        when(failProc.waitFor(anyLong(), eq(TimeUnit.MILLISECONDS))).thenReturn(false);
        when(sucProc.getInputStream())
                .thenReturn(IOUtils.toInputStream(sucStdOut, Charset.defaultCharset()));
        when(failProc.getErrorStream())
                .thenReturn(IOUtils.toInputStream(failErrOut, Charset.defaultCharset()));
        doNothing().when(failProc).destroy();

        ReflectionTestUtils.setField(testTarget, "processBuilder", pb);

        sucModel = new CardReadModel(sucIdm);
    }

    @Test
    public void 成功() throws CardReadException, IOException {
        CardReadModel actModel = testTarget.getCardInfo();
        assertThat(actModel, is(sucModel));
    }

    @Test
    public void PythonScriptの戻り値改行コードなしの場合() throws CardReadException, IOException {
        when(sucProc.getInputStream())
                .thenReturn(IOUtils.toInputStream(sucIdm, Charset.defaultCharset()));
        CardReadModel actModel = testTarget.getCardInfo();
        assertThat(actModel, is(sucModel));
    }

    @Test
    public void PythonScriptの戻り値が空文字の場合() throws CardReadException, IOException {
        CardReadModel actModel = testTarget.getCardInfo();
        assertThat(actModel, is(sucModel));
    }

}
