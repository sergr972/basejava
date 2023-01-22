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
        String uuid = resume.getUuid();
        int i = 0;
        for (; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                break;
            }
        }
        if (i >= size) {
            System.out.println("Такого резюме нет!");
            return;
        }
        for (int j = i; j < size; j++) {
            if (uuid.equals(storage[j].getUuid())) {
                storage[j] = resume;
            }
        }
        System.out.println("Резюме " + resume + " обновлено.");
    }

    public void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("База заполнена!!!");
        } else {
            String uuid = resume.getUuid();
            int i = 0;
            for (; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    System.out.println("Такое резюме уже есть!!!");
                    break;
                }
            }
            if (i >= size) {
                storage[size] = resume;
                size++;
                System.out.println("Резюме добавлено!");
            }
        }
    }

    public Resume get(String uuid) {
        int i = 0;
        for (; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                break;
            }
        }
        if (i >= size) {
            System.out.println("Такого резюме нет!");

        }
        for (int j = i; j < size; j++) {
            if (uuid.equals(storage[j].getUuid())) {
                return storage[j];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        int i = 0;
        for (; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                break;
            }
        }
        if (i >= size) {
            System.out.println("Такого резюме нет!");
            return;
        }
        storage[i] = storage[size - 1];
        storage[size - 1] = null;
        size--;
        System.out.println("Резюме " + uuid + " успешно удалено!");
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
}
