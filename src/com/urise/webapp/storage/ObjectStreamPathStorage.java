package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    protected ObjectStreamPathStorage(String dir) {
        super(dir);
    }

    @Override
    protected void doWrite(Resume r, OutputStream os) throws IOException {
        final Path storage = new File(os.toString()).toPath();

        try (ObjectOutputStream out =
                     new ObjectOutputStream(Files.newOutputStream(storage))) {
            out.writeObject(r);
        }
    }

    @Override
    protected Resume doRead(InputStream is) throws IOException {
        Path storage = new File(is.toString()).toPath();
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
