package com.marintek.isis.wicket.fancybox.applib;

import com.marintek.isis.wicket.popupbox.applib.PopupWicketBox;
import com.marintek.isis.wicket.popupbox.applib.PopupWicketBoxSemanticsProvider;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.apache.isis.applib.adapters.EncoderDecoder;
import org.junit.Before;
import org.junit.Test;


public class FancyWicketBoxSemanticsProviderEncoderDecoderTest {

    private EncoderDecoder<PopupWicketBox> encoderDecoder;

    @Before
    public void setUp() throws Exception {
        encoderDecoder = new PopupWicketBoxSemanticsProvider().getEncoderDecoder();
    }


    @Test
    public void roundTrip() throws Exception {
        final PopupWicketBox boxBefore = new PopupWicketBox("http://google.com", "title", 300, 300, 100, 100);
        final String encodedString = encoderDecoder.toEncodedString(boxBefore);
        final PopupWicketBox boxAfter = encoderDecoder.fromEncodedString(encodedString);
        
       
        assert(boxBefore.getUrl().equals(boxAfter.getUrl()));
        assert(boxBefore.getHeight() == boxAfter.getHeight());
        assert(boxBefore.getWidth() == boxAfter.getWidth());
    }
}
