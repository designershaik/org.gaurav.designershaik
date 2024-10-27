package com.gaurav.dsi.einvoicing.event;

import java.util.Base64;

public class GetBase64Utils {

	public static String encodeToBase64(String input) {
        // Get the encoder
        Base64.Encoder encoder = Base64.getEncoder();

        // Encode the input string and return the encoded string
        return encoder.encodeToString(input.getBytes());
    }
}
