package br.com.soft.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    private IndexController subject;

    @Before
    public void before() {
        subject = new IndexController();
    }

    @Test
    public void index() {
        subject.index();
    }
}