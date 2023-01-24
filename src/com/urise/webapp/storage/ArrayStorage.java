package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size;
    private static final int STORAGE_LIMIT = 1000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
    }

    public void update(Resume resume) {
        int i = getIndex(resume.getUuid());
        if (i == -1) {
            System.out.println("Резюме " + resume.getUuid() + " нет!");
        } else {
            storage[i] = resume;
            System.out.println("Резюме " + resume.getUuid() + " обновлено.");
        }
    }

    public void save(Resume resume) {
        int i = getIndex(resume.getUuid());
        if (size >= storage.length) {
            System.out.println("База заполнена!!!");
        } else if (i == -1) {
            storage[size] = resume;
            size++;
            System.out.println("Резюме " + resume.getUuid() + " добавлено!");
        } else {
            System.out.println("Резюме " + resume.getUuid() + " уже есть!!!");
        }
    }

    public Resume get(String uuid) {
        int i = getIndex(uuid);
        if (i == -1) {
            System.out.println("Резюме " + uuid + " нет!");
            return null;
        } else {
            return storage[i];
        }
    }

    public void delete(String uuid) {
        int i = getIndex(uuid);
        if (i == -1) {
            System.out.println("Резюме " + uuid + " нет!");
        } else {
            storage[i] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("Резюме " + uuid + " успешно удалено!");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
