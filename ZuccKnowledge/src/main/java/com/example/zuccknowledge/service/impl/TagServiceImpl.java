//package com.example.zuccknowledge.service.impl;
//
//import com.example.zuccknowledge.entity.TagEntity;
//import com.example.zuccknowledge.formbean.Tag;
//import com.example.zuccknowledge.repository.TagRepository;
//import com.example.zuccknowledge.service.TagService;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TagServiceImpl implements TagService {
//
//    @Autowired
//    private TagRepository tagRepository;
//
//    @Override
//    public Page<TagEntity> ListTag(Pageable pageable) {
//        return tagRepository.findAll(pageable);
//    }
//
//    @Override
//    public TagEntity saveTag(TagEntity tag) {
//        return tagRepository.save(tag);
//    }
//
//    @Override
//    public void deleteTag(Long id) {
//        tagRepository.deleteById(id);
//    }
//
//    @Override
//    public TagEntity getTagByName(String name) {
//        return tagRepository.findByName(name);
//    }
//
//    @Override
//    public TagEntity getTag(Long id) {
//        return (TagEntity) tagRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public TagEntity updateTag(Long id, TagEntity tag) {
//        TagEntity tag1 = (TagEntity) tagRepository.findById(id).orElse(null);
//        if(tag1==null){
//            System.out.println("未获得更新对象");
//            return null;
//        }else{
//            BeanUtils.copyProperties(tag,tag1);
//            return tagRepository.save(tag1);
//        }
//
//    }
//}
//
//
