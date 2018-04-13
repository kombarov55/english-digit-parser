package com.company;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class EngDigitParserTest {

    private EngDigitParser parser = new EngDigitParser();

    @Test()
    public void parse() throws Exception {
        assertThat(parser.parse("one hundred twenty one"), is(equalTo(121)));
        assertThat(parser.parse("one thousand"), is(equalTo(1000)));
        assertThat(parser.parse("three"), is(equalTo(3)));
        assertThat(parser.parse("nine thousand nine hundred ninety nine"), is(equalTo(9999)));
        assertThat(parser.parse("nineteen"), is(equalTo(19)));
    }

    @Test(expected = InvalidEngNumberException.class)
    public void fails1() throws Exception {
        parser.parse("");
    }

    @Test(expected = InvalidEngNumberException.class)
    public void fails2() throws Exception {
        parser.parse("one hunnndred");
    }

    @Test(expected = InvalidEngNumberException.class)
    public void fails3() throws Exception {
        parser.parse("one hundred two thousand five");
    }

    @Test(expected = InvalidEngNumberException.class)
    public void fails4() throws Exception {
        parser.parse("one hundred sixteen");
    }

}