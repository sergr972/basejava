package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class SortedArrayStorageTest extends AbstractStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Test
    public void saveOverflow() {
        Assertions.assertThrows(StorageException.class, () -> {
            try {
                storage.clear();
                for (int i = 0; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                    storage.save(new Resume(UUID.randomUUID().toString(), "name"));
                }
            } catch (StorageException e) {
                Assertions.fail("переполнение произошло раньше времени");
            }
            storage.save(new Resume(UUID.randomUUID().toString(), "name"));
        });
    }
}
