package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    protected ObjectStreamPathStorage(String dir) {
        super(dir);
    }

    @Override
    protected void doWrite(Resume r, OutputStream os) throws IOException {
        Path storage = Paths.get(os.toString());
        try (ObjectOutputStream out =
                     new ObjectOutputStream(Files.newOutputStream(storage))) {
            out.writeObject(r);
        }
    }

    @Override
    protected Resume doRead(InputStream is) throws IOException {
        Path storage =  Paths.get(is.toString());
        try (ObjectInputStream in =
                     new ObjectInputStream(Files.newInputStream(storage))) {
            try {
                return (Resume) in.readObject();
            } catch (ClassNotFoundException e) {
                throw new StorageException("Error read resume", null, e);
            }
        }
    }
}
