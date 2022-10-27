package ru.otus.spring.service;

import ru.otus.spring.exception.MyTestingException;

import java.io.*;

public class IOServiceImpl implements IOService {

    private final PrintStream ps;
    private final BufferedReader bf;

    public IOServiceImpl(PrintStream printStream, InputStream inputStream) {
        this.ps = printStream;
        this.bf = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public void outputString(String line) {
        ps.println(line);
    }

    @Override
    public String inputString() {
        try {
            return bf.readLine();
        } catch (IOException e) {
            throw new MyTestingException("Can't read input stream", e);
        }
    }
}
