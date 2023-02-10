package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final Resume R_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume R_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume R_3 = new Resume(UUID_3);
    private static final String UUID_4 = "uuid4";
    private static final Resume R_4 = new Resume(UUID_4);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(R_1);
        storage.save(R_2);
        storage.save(R_3);
    }

    @Test
    public void testSize() {
        testAssertionsSize(3);
    }

    @Test
    public void testClear() {
        storage.clear();
        testAssertionsSize(0);
    }

    @Test
    public void testUpdate() {
        Resume upResume = new Resume(UUID_2);
        storage.update(upResume);
        Assertions.assertEquals(upResume, storage.get(UUID_2));
    }

    @Test
    public void testUpdateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    public void testGetAll() {
        Resume[] newArr = storage.getAll();
        Assertions.assertEquals(3, newArr.length);
    }

    @Test
    public void testSave() {
        storage.save(R_4);
        testAssertionsSize(4);
        Assertions.assertEquals(R_4, storage.get(R_4.getUuid()));
    }

    @Test
    public void testSaveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(R_2));
    }

    @Test
    public void testSaveOverflow() {
        Assertions.assertThrows(StorageException.class, () -> {
            try {
                for (int i = 3; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                    storage.save(new Resume());
                }
            } catch (StorageException e) {
                Assertions.fail();
            }
            storage.save(new Resume());
        });
    }

    @Test
    public void testDelete() {
        storage.delete(UUID_2);
        testAssertionsSize(2);
    }

    @Test
    public void testDeleteNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    public void testGet() {
        testAssertionsGet(R_1);
        testAssertionsGet(R_2);
        testAssertionsGet(R_3);
    }

    @Test
    void testGetNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    private void testAssertionsSize(int size) {
        Assertions.assertEquals(size, storage.size());
    }

    private void testAssertionsGet(Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }
}