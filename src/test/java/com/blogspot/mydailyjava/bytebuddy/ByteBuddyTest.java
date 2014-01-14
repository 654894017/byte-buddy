package com.blogspot.mydailyjava.bytebuddy;

import com.blogspot.mydailyjava.bytebuddy.method.bytecode.SuperClassDelegation;
import com.blogspot.mydailyjava.bytebuddy.method.matcher.MethodMatchers;
import org.junit.Test;

import java.util.Arrays;

public class ByteBuddyTest {

    public static class Foo {

        @Override
        public String toString() {
            return "Foo";
        }
    }

    public static class Bar extends Foo {

        @Override
        public String toString() {
            return "Bar";
        }
    }

    @Test
    public void testSubclass() throws Exception{
        Object object = ByteBuddy.make()
                .subclass(Bar.class)
                .name("my.Test")
                .intercept(MethodMatchers.returns(String.class), SuperClassDelegation.INSTANCE)
                .make()
                .load(getClass().getClassLoader())
                .newInstance();
        System.out.println(Arrays.asList(object.getClass().getDeclaredMethods()));
        System.out.println(object.getClass());
        System.out.println(object.getClass().getSuperclass());
        System.out.println(object.toString());
    }
}
