package com.google.common.base;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.google.common.testing.NullPointerTester;
import junit.framework.TestCase;

import javax.annotation.Nullable;

/**
 * @author: bxguo
 * @time: 2018/11/29 14:43
 */
public class OrderingTest extends TestCase {
    public void testCreatOrdering() {

        //排序其创建
        Ordering.natural();
        Ordering.usingToString();
        new Ordering<String>() {
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };

        //排序规则 TODO 获取迭代器
        Ordering<Iterable<Comparable>> lexicographical = Ordering.natural().lexicographical();

        //获取两个里面最大值
        Function<Foo, String> function = new Function<Foo, String>() {
            public String apply(Foo foo) {
                return foo.sortedBy;
            }
        };
        Ordering<Foo> ordering = Ordering.natural().nullsLast().nullsFirst().onResultOf(function);
        Foo max = ordering.max(new Foo(null, 10), new Foo("9", 9));
        assertEquals(max.toString(), "Foo{sortedBy='10', notSortedBy=10}");
    }


    class Foo {
        @Nullable
        String sortedBy;
        int notSortedBy;

        public Foo(@Nullable String sortedBy, int notSortedBy) {
            this.sortedBy = sortedBy;
            this.notSortedBy = notSortedBy;
        }

        @Override
        public String toString() {
            return "Foo{" +
                    "sortedBy='" + sortedBy + '\'' +
                    ", notSortedBy=" + notSortedBy +
                    '}';
        }
    }
}
