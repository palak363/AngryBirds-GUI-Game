package com.bitbrawlers.angrybirds.JUnit;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        level1Test.class,
        level2Test.class,
        Bird.class
})
public class TestSuite {}
