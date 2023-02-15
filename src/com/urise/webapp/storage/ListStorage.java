package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> list = new ArrayList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        list.set((Integer) index, r);
    }

    @Override
    protected void doSave(Resume r, Object index) {
        list.add(r);
    }

    @Override
    protected boolean isExist(Object index) {
        return index != null;
    }

    @Override
    public Resume doGet(Object index) {
        return list.get((Integer) index);
    }

    @Override
    public void doDelete(Object index) {
        list.remove(((Integer) index).intValue());
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[0]);
    }

    protected Integer getIndex(String uuid) {
        for (int index = 0; index < list.size(); index++) {
            if (list.get(index).getUuid().equals(uuid)) {
                return index;
            }
        }
        return null;
    }
}
