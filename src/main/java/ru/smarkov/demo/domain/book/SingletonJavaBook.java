package ru.smarkov.demo.domain.book;

public class SingletonJavaBook {
    private static volatile SingletonJavaBook book;

    private SingletonJavaBook() {
    }

    public static SingletonJavaBook getInstance() {
        if (book == null) {
            synchronized (SingletonJavaBook.class) {
                if (book == null) {
                    book = new SingletonJavaBook();
                }
            }
        }
        return book;
    }
}
