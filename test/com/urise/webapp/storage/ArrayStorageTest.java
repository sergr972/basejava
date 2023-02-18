package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArrayStorageTest extends AbstractStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Test
    public void saveOverflow() {
        Assertions.assertThrows(StorageException.class, () -> {
            try {
                storage.clear();
                for (int i = 0; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                    storage.save(new Resume());
                }
            } catch (StorageException e) {
                Assertions.fail("переполнение произошло раньше времени");
            }
            storage.save(new Resume());
        });
    }

}
