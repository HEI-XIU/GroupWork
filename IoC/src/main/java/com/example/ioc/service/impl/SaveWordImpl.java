package com.example.ioc.service.impl;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class SaveWordImpl implements ISaveWord {
    @Override
    public void save(List<String> words) {
        File file = new File(SaveWordImpl.class.getResource("/words.json").getPath());
        try {
            String content = FileUtils.readFileToString(file, "UTF-8");
            System.out.println("contentBefore:" + content);

            JSONObject data = new JSONObject();

            if (!"{}".equals(content)) {
                data = new JSONObject(content);
                for (String word : words) {
                    data.put(word, data.has(word) ? data.getInt(word) + 1 : 1);
                }
            } else {
                data = new JSONObject();
                for (String word : words) {
                    data.put(word, 1);
                }
            }

            file.delete();
            file.createNewFile();
            System.out.println(file.getPath());

            content = data.toString();
            System.out.println("contentAfter：" + content);

            FileOutputStream fos = new FileOutputStream("E:/work/spring/GroupWork/IoC/src/main/resources/words.json");
            OutputStreamWriter os = new OutputStreamWriter(fos);
            BufferedWriter w = new BufferedWriter(os);
            w.write(content);
            w.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
