package com.urise.webapp.storage;

import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runners.Suite;

//@RunWith(org.junit.platform.runner.JUnitPlatform.class)
@SuiteDisplayName("AllTest")
//@SelectPackages(com.urise.webapp.storage)
//@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapResumeStorageTest.class
})

public class AllTest {
}
