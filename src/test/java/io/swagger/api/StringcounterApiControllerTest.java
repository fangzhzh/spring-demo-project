package io.swagger.api;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * @author zhangzhenfang
 * @since 4/10/17 10:10 PM
 */
@RunWith(Arquillian.class)
public class StringcounterApiControllerTest {
    StringcounterApiController controller;
    @Before
    public void setUp() throws Exception {
        controller = new StringcounterApiController();
    }

    @org.junit.Test
    public void processTest() throws Exception {
        // invalid string
        assertEquals(controller.process(null), StringcounterApiController.INVALID_REQUEST_STRING);
        assertEquals(controller.process(""), StringcounterApiController.INVALID_REQUEST_STRING);
        assertEquals(controller.process("123"), StringcounterApiController.INVALID_REQUEST_STRING);
        assertEquals(controller.process("123456789"), StringcounterApiController.INVALID_REQUEST_STRING);
        assertEquals(controller.process("1"), StringcounterApiController.INVALID_REQUEST_STRING);
        assertEquals(controller.process("!"), StringcounterApiController.INVALID_REQUEST_STRING);
        assertEquals(controller.process("!@#$%^&*()"), StringcounterApiController.INVALID_REQUEST_STRING);
        assertEquals(controller.process("!@#$%^&*("), StringcounterApiController.INVALID_REQUEST_STRING);

        // valid string
        assertEquals(controller.process("a"), String.format("The letters are: ‘%1$s’. The most frequent letter is ‘%2$s’, and the frequency is %3$d", "a", 'a', 1));
        assertEquals(controller.process("as-dA@e32ar4As9"), String.format("The letters are: ‘%1$s’. The most frequent letter is ‘%2$s’, and the frequency is %3$d", "asdAearAs", 'a', 4));
        assertEquals(controller.process("abcdefghijklmnopqrstuvwxyz"), String.format("The letters are: ‘%1$s’. The most frequent letter is ‘%2$s’, and the frequency is %3$d", "abcdefghijklmnopqrstuvwxyz", 'a', 1));
        assertEquals(controller.process("111abcdefghijklmnopqrstuvwxyz111"), String.format("The letters are: ‘%1$s’. The most frequent letter is ‘%2$s’, and the frequency is %3$d", "abcdefghijklmnopqrstuvwxyz", 'a', 1));
        assertEquals(controller.process("!@#@#@$@#$@#$@#$abcdefghijklmnopqrstuvwxyz111"), String.format("The letters are: ‘%1$s’. The most frequent letter is ‘%2$s’, and the frequency is %3$d", "abcdefghijklmnopqrstuvwxyz", 'a', 1));
        assertEquals(controller.process("!@#@#@$@#$@#$@#$abcdefghijklmnopqrstuvwxyz!@!@#!@#!@#!@#!@#"), String.format("The letters are: ‘%1$s’. The most frequent letter is ‘%2$s’, and the frequency is %3$d", "abcdefghijklmnopqrstuvwxyz", 'a', 1));
        assertEquals(controller.process("!@#@#32343434@$@#$@#$@#$abcdefghijklmnopqrstuvwxyz!@!@#!@234234234#!@#!@#!@#"), String.format("The letters are: ‘%1$s’. The most frequent letter is ‘%2$s’, and the frequency is %3$d", "abcdefghijklmnopqrstuvwxyz", 'a', 1));
        assertEquals(controller.process("!@#@#32343434@$@#$@#$@#$aaaaaaabcdefghijklmnopqrstuvwxyz!@!@#!@234234234#!@#!@#!@#"), String.format("The letters are: ‘%1$s’. The most frequent letter is ‘%2$s’, and the frequency is %3$d", "aaaaaaabcdefghijklmnopqrstuvwxyz", 'a', 7));
        assertEquals(controller.process("!@#@#32343434@$@#$@#$@#$abcdefghaaaaaaijklmnopqrstuvwxyz!@!@#!@234234234#!@#!@#!@#"), String.format("The letters are: ‘%1$s’. The most frequent letter is ‘%2$s’, and the frequency is %3$d", "abcdefghaaaaaaijklmnopqrstuvwxyz", 'a', 7));
        assertEquals(controller.process("!@#@#32343434@$@#$@#$@#$abcdefghijklmnopqrstuvwxyzaaaaaa!@!@#!@234234234#!@#!@#!@#"), String.format("The letters are: ‘%1$s’. The most frequent letter is ‘%2$s’, and the frequency is %3$d", "abcdefghijklmnopqrstuvwxyzaaaaaa", 'a', 7));
        assertEquals(controller.process("!@#@#32343434@$@#$@#$@#$abcdefghijklmnopqrstuvwxyz!@!@#!@234234234#!@#!@#!@#aaaaaa"), String.format("The letters are: ‘%1$s’. The most frequent letter is ‘%2$s’, and the frequency is %3$d", "abcdefghijklmnopqrstuvwxyzaaaaaa", 'a', 7));
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(StringcounterApiController.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
