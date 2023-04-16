package com.example.ioc;

import com.example.ioc.service.impl.ISaveWord;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ServiceDispatcher {
    public static void createService(String serveType, List<String> words) {
        File file = new File(ServiceDispatcher.class.getResource("/ioc.properties").getPath());
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
            String classPath = String.valueOf(properties.get(serveType));
            Class clazz = Class.forName(classPath);
            Object service = clazz.newInstance();

            List<String> implNames = getClassFQName("com.example.ioc.service.impl", ISaveWord.class);
            String implName = implNames.get(1);
            System.out.println("实现接口方法的类：" + implNames);

            PropertyDescriptor pd = new PropertyDescriptor("saveWord", clazz);
            System.out.println(pd);
            Method set = pd.getWriteMethod();
            System.out.println(set);
            set.invoke(service, Class.forName(implName).newInstance());

            System.out.println();
            Method[] methods = clazz.getMethods();
            for (int i = 0; i < methods.length; i++) {
                System.out.println("方法【" + i + "】：" + methods[i].getName());
                if (methods[i].getName().equals("setSaveWordImpl")) {
                    System.out.println(service);
                    methods[i].invoke(service, words);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * * 获取包下面所有实现了clz类的全限定名
     *
     * @param packgeName 包名
     * @param clz        父类名
     * @return
     */
    public static List<String> getClassFQName(String packgeName, Class clz) {
        final String BASE_PACKAGE = packgeName;
        final String RESOURCE_PATTERN = "/**/*.class";
        ArrayList<String> list = new ArrayList<>();
        if (packgeName == null || clz == null) {
            return list;
        }
        //spring工具类，可以获取指定路径下的全部类
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    ClassUtils.convertClassNameToResourcePath(BASE_PACKAGE) + RESOURCE_PATTERN;
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            //MetadataReader 的工厂类
            MetadataReaderFactory readerfactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            for (Resource resource : resources) {
                //用于读取类信息
                MetadataReader reader = readerfactory.getMetadataReader(resource);
                //扫描到的class
                String classname = reader.getClassMetadata().getClassName();
                Class<?> clazz = Class.forName(classname);

                // 判断是否实现指定类型
                //isAssignableFrom()方法是判断是否是某个类的父类
                if (clz.isAssignableFrom(clazz)) {
                    list.add(clazz.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
