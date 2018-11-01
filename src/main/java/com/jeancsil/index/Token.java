package com.jeancsil.index;

final class Token {
    private final String token;

    Token(final String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return token;
    }
}
