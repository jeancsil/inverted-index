package com.jeancsil.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvertedIndex {

    private final Map<Token, List<Long>> postingsList = new HashMap<>();

    void addToken(final Token token, final long documentId) {
        postingsList.computeIfAbsent(token, k -> new ArrayList<>());

        postingsList
            .get(token)
            .add(documentId);
    }

    @Override
    public String toString() {

        final String header = String.format("|Token%s|DocumentIds\t\t|", generateSpaces("Token"));

        StringBuilder builder = new StringBuilder();

        postingsList.forEach((token, documentId) -> {
            builder.append("|");
            builder.append(token);
            builder.append(generateSpaces(token.toString()));
//            builder.append("|\t\t");
            builder.append(documentId);
//            builder.append("|\t\t\n");
            builder.append("\n");
        });


        return header + "\n" + builder.toString();
    }

    private String generateSpaces(final String text) {
        int numberOfSpaces = 70 - text.length();
        return new String(new char[numberOfSpaces]).replace("\0", " ");
    }
}
