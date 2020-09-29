package com.kure.test.collection;

import java.util.HashSet;
import java.util.Set;

/**
 * 覆盖hashcode equals
 * 散列集合
 * 覆盖如果覆盖了equals 需要同时覆盖hashcode
 * 散列集合优先通过hash值来判断 再根据equals来比较对象是否相等
 * 如果不覆盖就使用了object的hashcode 和 equals
 */
public class OverrideHash {

    static {
        System.out.println("static init"+ OverrideHash.a);
    }
    {
        System.out.println("{} init");
    }
    public OverrideHash(String name, String sex) {
        this.name=name;
        this.sex=sex;
    }

    public OverrideHash() {
    }
    private static final int a = 1;
    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 覆盖 equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OverrideHash cat = (OverrideHash) o;

        return cat.name.equals(this.name) &&
                cat.sex.equals(this.sex);
    }

    /**
     * 覆盖 hashCode
     * @param
     * @return
     */
    @Override
    public int hashCode() {
        return name.hashCode()+sex.hashCode();
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Class.forName("Cat").newInstance();
        Set<OverrideHash> s = new HashSet<>();
        OverrideHash c1 = new OverrideHash("1","1");
        OverrideHash c2 = new OverrideHash("1","1");
        System.out.println(c1.equals(c2));
        System.out.println(c1.hashCode() == c2.hashCode());
        s.add(c1);
        s.add(c2);
        s.forEach(System.out::println);

        String value = "value";
        System.out.println(value.codePointCount(1, 2));

    }
}
