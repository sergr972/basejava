package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[0]);
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
    protected Resume doGet(Object index) {
        return list.get((Integer) index);
    }

    @Override
    protected void doDelete(Object index) {
        int i = (Integer) index;
        list.remove(i);
    }

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object index) {
        return index != null;
    }
}
