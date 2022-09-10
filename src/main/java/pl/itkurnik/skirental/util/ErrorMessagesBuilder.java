package pl.itkurnik.skirental.util;

import java.util.Collection;

public class ErrorMessagesBuilder {

    public static String ofMessages(Collection<String> messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) { // TODO KM maybe change to iterator?
            sb.append(String.format("%s. ", message));
        }

        return sb.toString();
    }
}
