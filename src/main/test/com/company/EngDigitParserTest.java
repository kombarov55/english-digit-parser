package com.company;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class EngDigitParserTest {

    private EngDigitParser parser = new EngDigitParser();

    @Test()
    public void parse() throws Exception {
        assertThat(parser.parse("one"), is(equalTo(1)));
        assertThat(parser.parse("twenty"), is(equalTo(20)));
        assertThat(parser.parse("fifty six"), is(equalTo(56)));
        assertThat(parser.parse("one hundred"), is(equalTo(100)));
        assertThat(parser.parse("two hundred fifteen"), is(equalTo(215)));
        assertThat(parser.parse("nine thousand nine hundred ninety four"), is(equalTo(9994)));

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
    public void fails6() throws Exception {
        parser.parse("one one one one one");
    }

    @Test(expected = InvalidEngNumberException.class)
    public void fails7() throws Exception {
        parser.parse("hundred");
    }

    @Test(expected = InvalidEngNumberException.class)
    public void fails8() throws Exception {
        parser.parse("thousand five");
    }

}