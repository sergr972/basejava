package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("Резюме " + r.getUuid() + " нет!");
        } else {
            storage[index] = r;
            System.out.println("Резюме " + r.getUuid() + " обновлено.");
        }
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        if (getIndex(uuid) != -1) {
            System.out.println("Резюме " + r.getUuid() + " уже есть!!!");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("База заполнена!!!");
        } else {
            storage[size] = r;
            size++;
            System.out.println("Резюме " + uuid + " добавлено!");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
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

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);
}

