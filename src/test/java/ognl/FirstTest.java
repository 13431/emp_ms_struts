package ognl;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class FirstTest {

    @Test public void testFirst() throws OgnlException {
        Person aaa = new Person("aaa");
        Location l = new Location("珠海");
        Job it = new Job("it");
        it.setLocation(l);
        aaa.setJob(it);

        System.out.println(Ognl.getValue("job.location.address", aaa));
    }

    @Test public void testSecond () throws OgnlException {
        Person aaa = new Person("aaa", new Job("it"));
        Person bbb = new Person("bbb", new Job("保安"));

        System.out.println(Ognl.getValue("job.type", aaa));
        System.out.println(Ognl.getValue("job.type", bbb));
    }

    @Test public void testThird () throws OgnlException {
        Map<String, Object> context = new HashMap<>();

        context.put("a", new Person("aaa", new Job("it")));
        context.put("b", new Person("bbb", new Job("保安")));

        System.out.println(Ognl.getValue("job.type", context, context.get("a")));
        System.out.println(Ognl.getValue("job.type", context, context.get("b")));
    }

    @Test public void testFourth () throws OgnlException {
        Map<String, Object> context = new HashMap<>();

        context.put("a", new Person("aaa", new Job("it")));
        context.put("b", new Person("bbb", new Job("保安")));

        // 第一个参数，指的是我们的表达式字符串
        // 第二个参数，指的是我们的 Context Map
        // 第三个参数，指的是默认的对象 root
        Ognl.setRoot(context, context.get("a"));
        System.out.println(Ognl.getValue("job.type", context, context.get("a")));
        System.out.println(Ognl.getValue("#a.job.type", context, context.get("b")));
    }

    OgnlContext context;

    @Test public void test5th () throws OgnlException {
        context = new OgnlContext();

        context.put("a", new Person("aaa", new Job("it")));
        context.put("session", new Person("用户会话", new Job("绑定用户访问数据")));
        context.put("request", new Person("请求对象", new Job("表达客户端请求，保存着客户端的请求信息")));
        context.put("valuestack", new Person("值栈", new Job("为 struts 保存传递数据")));

        context.setRoot(context.get("valuestack"));

        System.out.println(Ognl.getValue("#request.job.type", context, context.getRoot()));
        System.out.println(Ognl.getValue("#session.job.type", context, context.getRoot()));
        System.out.println(Ognl.getValue("job.type", context, context.getRoot()));
    }

    public void printFromOgnl(String expression) {
        try {
            System.out.println(Ognl.getValue(expression, context, context.getRoot()));
        } catch (OgnlException e) {
            System.err.println("有错误产生");
        }
    }

    @Test public void test6th () throws OgnlException {
        context = new OgnlContext();

        context.put("a", new Person("aaa", new Job("it")));
        context.put("session", new Person("用户会话", new Job("绑定用户访问数据")));
        context.put("request", new Person("请求对象", new Job("表达客户端请求，保存着客户端的请求信息")));
        context.put("valuestack", new Person("值栈", new Job("为 struts 保存传递数据")));

        context.setRoot(context.get("valuestack"));

        printFromOgnl("#session.job.type");
        printFromOgnl("#request.job.type");
        printFromOgnl("job.type");

        printFromOgnl("#session.job.typeabc");
    }
}
