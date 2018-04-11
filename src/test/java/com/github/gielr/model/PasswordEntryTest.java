package com.github.gielr.model;


import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class PasswordEntryTest {
    private PasswordEntry passwordEntry1 = new PasswordEntry(1L,"wp","123","123".toCharArray());

    @Test
    public void getId() {
        Assert.assertThat(passwordEntry1.getId(),is(1L));
    }

    @Test
    public void getServiceName() {
        Assert.assertThat(passwordEntry1.getServiceName(),is("wp"));
    }

    @Test
    public void getLogin() {
        Assert.assertThat(passwordEntry1.getLogin(),is("123"));
    }

    @Test
    public void getPassword() {
        Assert.assertThat(passwordEntry1.getPassword(),is("123".toCharArray()));
    }
}
