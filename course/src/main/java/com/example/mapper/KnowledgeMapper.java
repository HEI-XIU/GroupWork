package com.example.mapper;

import com.example.entity.Knowledge;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface KnowledgeMapper {

    @Select("select * from knowledge")
    List<Knowledge> getAllKnowledge();

    @Select("SELECT * FROM knowledge WHERE knowledgeName LIKE '%${name}%'")
    List<Knowledge> getKnowledgeByName(String name);

    @Insert("INSERT INTO\n" +
            "knowledge(knowledgeName, description, emphasis, preKnowledges, labels)\n" +
            "VALUES (#{knowledgeName}, #{description}, #{emphasis}, #{preKnowledges}, #{labels}")
    int insertKnowledge(Knowledge knowledge);

    @Update("UPDATE knowledge\n" +
            "SET knowledgeName = #{knowledgeName}" +
            "description = #{description},\n" +
            "emphasis = #{emphasis},\n" +
            "preKnowledge = #{preKnowledges},\n" +
            "labels = #{labels}\n" +
            "WHERE knowledgeId = #{knowledgeId}")
    int updateKnowledge(Knowledge knowledge);

    @Delete("delete from knowledge where knowledgeName = #{name}")
    int deleteKnowledge(String name);
}
