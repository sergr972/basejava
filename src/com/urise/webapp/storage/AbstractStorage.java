package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public abstract Resume doGet(Object index);

    protected abstract Object getIndex(String uuid);

    protected abstract void doUpdate(Resume r, Object index);

    protected abstract void doSave(Resume r, Object index);

    protected abstract boolean isExist(Object ob);

    protected abstract void doDelete(Object index);

    public void update(Resume r) {
        Object index = getExistedIndex(r.getUuid());
        doUpdate(r, index);
    }

    public Resume get(String uuid) {
        Object index = getExistedIndex(uuid);
        return doGet(index);
    }

    @Override
    public void delete(String uuid) {
        Object index = getExistedIndex(uuid);
        doDelete(index);
    }

    public void save(Resume r) {
        Object index = getNotExistedIndex(r.getUuid());
        doSave(r, index);
    }

    private Object getExistedIndex(String uuid) {
        Object index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    private Object getNotExistedIndex(String uuid) {
        Object index = getIndex(uuid);
        if (isExist(index)) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }
}
