package com.github.gielr.model;


import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class PasswordSafeTest {
    private PasswordSafe passwordSafe = new PasswordSafe();
    private PasswordEntry passwordEntry1 = new PasswordEntry(1L,"wp","123","123".toCharArray());

    @Test
    public void add() {
        passwordSafe.add(passwordEntry1);

        Assert.assertThat(passwordSafe.getMap().get(1L).getServiceName(),is("wp"));
    }

    @Test
    public void remove() {
        passwordSafe.add(passwordEntry1);
        passwordSafe.remove(1L);

        boolean test = passwordSafe.getMap().isEmpty();

        Assert.assertTrue("Nie skasowalo",test);
    }
}
