package com.example.mapper;

import com.example.entity.Cases;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CasesMapper {

    @Select("select * from cases")
    List<Cases> getAllCases();

    @Select("SELECT * FROM cases WHERE knowledgeId = #{knowledgeId}")
    List<Cases> getCasesByKId(int knowledgeId);

    @Insert("INSERT INTO\n" +
            "cases(knowledgeId, caseInfo, isCode, type)\n" +
            "VALUES (#{knowledgeId}, #{caseInfo}, #{isCode}, #{type})")
    int insertCases(Cases cases);

    @Update("UPDATE cases\n" +
            "SET knowledgeId = #{knowledgeId}" +
            "caseInfo = #{caseInfo},\n" +
            "isCode = #{isCode},\n" +
            "type = #{type},\n" +
            "WHERE casesId = #{casesId}")
    int updateCases(Cases cases);

    @Delete("delete from cases where casesId = #{casesId}")
    int deleteCases(int casesId);
}
