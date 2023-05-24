package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static com.urise.webapp.ResumeTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    protected final Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        List<Resume> list = storage.getAllSorted();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void update() {
        Resume upResume = new Resume(UUID_1, "New Name");
        R1.setContact(ContactType.MAIL, "mail@gmail.com");
        R1.setContact(ContactType.SKYPE, "NewSkype");
        R1.setContact(ContactType.PHONE, "+7 921 123-45-67");
        storage.update(upResume);
        assertEquals(upResume, storage.get(UUID_1));
    }

    @Test
    public void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_NOT_EXIST));
    }

    @Test
    public void getAllSorted() {
        Resume[] expected = new Resume[]{R1, R2, R3};
        List<Resume> actual = storage.getAllSorted();
        Assertions.assertArrayEquals(expected, actual.toArray());
        assertSize(actual.size());
    }

    @Test
    public void save() {
        storage.save(R4);
        assertGet(R4);
        assertSize(4);
    }

    @Test
    public void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(R2));
    }

    @Test
    public void delete() {
        storage.delete(UUID_2);
        assertSize(2);
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_2));
    }

    @Test
    public void deleteNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_NOT_EXIST));
    }

    @Test
    public void get() {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
    }

    @Test
    void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_NOT_EXIST));
    }

    private void assertSize(int size) {
        Assertions.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }
}