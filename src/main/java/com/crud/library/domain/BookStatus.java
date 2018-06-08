package com.crud.library.domain;

public enum BookStatus {
    AVAILABLE("available"),
    RENTED("rented"),
    DESTROYED("destroyed"),
    LOST("lost");

    private final String status;

    BookStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return status;
    }
}
