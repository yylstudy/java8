package com.test.sourcecode;

import org.junit.Test;

/**
 * @Description: 当需要元素有序的Map时，考虑使用LinkedHashMap
 * @Author: yang.yonglian
 * @CreateDate: 2019/12/3 19:31
 * @Version: 1.0
 */
public class MyTest1 {
    //所谓的accessOrder 为true  按照元素的访问顺序，
    //即使出现相同的元素，也会在最后节点添加
    //所以  aa3是头结点，aa是尾节点
    //造成此影响是 LinkedHashMap 中的afterNodeAccess方法
    @Test
    public void test1() {
        LinkedHashMap<String,String> map = new LinkedHashMap<String,String>(16,0.75f,true);
        //这里的put是使用HashMap的put方法，但是Entry的before和after在哪里关联呢，因为HashMap的Node方法
        //并没有before和after属性，这里使用了模板模式，HashMap的put方法中的newNode实际上是new LinkedHashMap的entry
        //所以在LinkedHashMap的newNode的linkNodeLast对LinkedHashMap的head、tail进行赋值
        map.put("aa", "aa");
        map.put("aa3", "aa3");
        map.put("aa2", "aa2");
        map.put("aa", "aa1");
        LinkedHashMap.Entry<String,String> entry = map.head;
        LinkedHashMap.Entry<String,String> next = null;
//		System.out.println(map.get("aa2"));   若设置accessOrder为true，那么get操作也会把当前节点放置在最后面
        System.out.println(entry);
        while((next = entry.after)!=null) {
            System.out.println(next.key+":"+next.value);
            entry = next;
        }
    }
    //默认 accessOrder 为fasle，按照元素的插入顺序，所以，aa还是头节点
    @Test
    public void test2() {
        LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
        map.put("aa", "aa");
        map.put("aa3", "aa3");
        map.put("aa2", "aa2");
        map.put("aa", "aa1");
        LinkedHashMap.Entry<String,String> entry = map.head;
        LinkedHashMap.Entry<String,String> next = null;
        System.out.println(entry);
        while((next = entry.after)!=null) {
            System.out.println(next.key+":"+next.value);
            entry = next;
        }
    }
}
