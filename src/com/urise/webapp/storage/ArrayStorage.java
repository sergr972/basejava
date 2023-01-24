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
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Резюме " + resume.getUuid() + " нет!");
        } else {
            storage[index] = resume;
            System.out.println("Резюме " + resume.getUuid() + " обновлено.");
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("База заполнена!!!");
        } else if (index >= 0) {
            System.out.println("Резюме " + resume.getUuid() + " уже есть!!!");
        } else {
            storage[size] = resume;
            size++;
            System.out.println("Резюме " + resume.getUuid() + " добавлено!");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме " + uuid + " нет!");
            return null;
        } else {
            return storage[index];
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме " + uuid + " нет!");
        } else {
            storage[index] = storage[size - 1];
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
