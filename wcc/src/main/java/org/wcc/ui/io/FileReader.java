package org.wcc.ui.io;

import org.wcc.ui.exception.GraphFileReadException;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {

    public static String read(String path) throws GraphFileReadException {

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(path))) {

            StringBuilder sb = new StringBuilder();

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
            }

            return sb.toString();

        } catch (IOException e) {
            throw new GraphFileReadException();
        }
    }

}
