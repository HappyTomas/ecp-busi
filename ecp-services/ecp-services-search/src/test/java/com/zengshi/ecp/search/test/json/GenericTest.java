package com.zengshi.ecp.search.test.json;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class GenericTest {
    /* 使用反射来获取泛型信息 */
    private Map<String, Integer> score;

    public static void main(String[] args) throws SecurityException, NoSuchFieldException {
        // Class clazz = GenericTest.class;
        Class<GenericTest> clazz = GenericTest.class;
        // System.out.println(clazz);
        Field f = clazz.getDeclaredField("score");

        // 直接使用getType只对普通类型有效，无法取到泛型参数
        Class<?> a = f.getType();
        System.out.println("score的类型是：" + a);

        // 获得Field实例f的泛型类型
        Type gType = f.getGenericType();
        // 如果gType类型是ParameterizedType的对象
        if (gType instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) gType;
            // 获取原始类型
            Type rType = pType.getRawType();
            System.out.println("原始类型是：" + rType);
            // 取得泛型类型参数
            Type[] tArgs = pType.getActualTypeArguments();
            System.out.println("泛型类型是：");
            for (int i = 0; i < tArgs.length; i++) {
                System.out.println("第" + i + "个泛型类型是：" + tArgs[i]);
            }
        } else {
            System.out.println("获取泛型类型出错！");
        }
    }
}
