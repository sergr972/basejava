//package com.urise.webapp;
//
//import com.urise.webapp.model.Resume;
//import com.urise.webapp.storage.SortedArrayStorage;
//import com.urise.webapp.storage.Storage;
//
///**
// * Test for your ArrayStorage implementation
// */
//public class MainTestSortedArrayStorage {
//    private static final Storage ARRAY_STORAGE = new SortedArrayStorage();
//
//    public static void main(String[] args) {
//        final Resume r1 = new Resume();
//        r1.setUuid("uuid1");
//        final Resume r2 = new Resume();
//        r2.setUuid("uuid2");
//        final Resume r3 = new Resume();
//        r3.setUuid("uuid3");
//
//        System.out.println(r1 == r2);
//
//        System.out.println("\nСохранить резюме r3");
//        ARRAY_STORAGE.save(r3);
//        System.out.println("\nСохранить резюме r2");
//        ARRAY_STORAGE.save(r2);
//        System.out.println("\nСохранить резюме r1");
//        ARRAY_STORAGE.save(r1);
//
//        System.out.println("\nВернуть резюме r2");
//        System.out.println("Get r2: " + ARRAY_STORAGE.get(r2.getUuid()));
//        System.out.println("Size: " + ARRAY_STORAGE.size());
//        System.out.println();
//
//        System.out.println("\nВернуть резюме dummy");
//        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
//
//        System.out.println("\nОбновить резюме r1");
//        ARRAY_STORAGE.update(r1);
//
//        printAll();
//
//        System.out.println("\nУдалить резюме r2");
//        ARRAY_STORAGE.delete(r2.getUuid());
//
//        printAll();
//        ARRAY_STORAGE.clear();
//        printAll();
//
//        System.out.println("Size: " + ARRAY_STORAGE.size());
//    }
//
//    static void printAll() {
//        System.out.println("\nGet All");
//        for (Resume r : ARRAY_STORAGE.getAll()) {
//            System.out.println(r);
//        }
//    }
//}
