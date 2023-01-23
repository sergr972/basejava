package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[1000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
    }

    public void update(Resume resume) {
        int i = check(resume.getUuid());
        if (i < size) {
            storage[i] = resume;
            System.out.println("Резюме " + resume.getUuid() + " обновлено.");
        } else {
            System.out.println("Резюме " + resume.getUuid() + " нет!");
        }
    }

    public void save(Resume resume) {
        if (size >= storage.length) {
            System.out.println("База заполнена!!!");
        } else {
            int i = check(resume.getUuid());
            if (i < size) {
                System.out.println("Резюме " + resume.getUuid() + " уже есть!!!");
            } else {
                storage[size] = resume;
                size++;
                System.out.println("Резюме " + resume.getUuid() + " добавлено!");
            }
        }
    }

    public Resume get(String uuid) {
        int i = check(uuid);
        if (i < size) {
            return storage[i];
        } else {
            System.out.println("Резюме " + uuid + " нет!");
        }
        return null;
    }

    public void delete(String uuid) {
        int i = check(uuid);
        if (i < size) {
            storage[i] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("Резюме " + uuid + " успешно удалено!");
        } else {
            System.out.println("Резюме " + uuid + " нет!");
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

    private int check(String uuid) {
        int i = 0;
        for (; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                break;
            }
        }
        return i;
    }
}
