package com.javarush.island.rantsev.util;

public class IdGenerator {
    private static int id = 1;
    public static int get() {
        return id++;
    }
}
