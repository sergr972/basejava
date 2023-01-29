package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

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
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("Резюме " + r.getUuid() + " уже есть!!!");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("База заполнена!!!");
        } else {
            storage[size] = r;
            size++;
            System.out.println("Резюме " + r.getUuid() + " добавлено!");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме " + uuid + " нет!");
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

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
