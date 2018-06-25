package com.example.blog.service.blog.impl;

import com.example.blog.entity.blog.Tag;
import com.example.blog.repository.blog.TagRepository;
import com.example.blog.service.blog.TagService;
import com.example.blog.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 17:28
 */
@Service
@Transactional
public class TagServiceImpl extends BaseServiceImpl<Tag> implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag addTag(String name) {
        Tag tag = tagRepository.findByName(name);
        Assert.isNull(tag, "该标签已存在");
        tag = generateTag(name);
        return tagRepository.save(tag);
    }

    @Override
    public Set<Tag> addTagSet(Set<String> tagNameSet) {
        return tagNameSet.stream().map(tagName -> {
            Tag tag = tagRepository.findByName(tagName);
            if (tag != null) {
                return tag;
            } else {
                return tagRepository.save(generateTag(tagName));
            }
        }).collect(Collectors.toSet());
    }

    @Override
    public void deleteTag(long id) {
        Tag tag = tagRepository.findOne(id);
        Assert.notNull(tag, "该标签不存在");
        tagRepository.delete(tag);
    }

    @Override
    public Tag editTag(long id, String name) {
        Tag tag = tagRepository.findOne(id);
        Assert.notNull(tag, "该标签不存在");
        modifyTag(tag, name);
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTag(String name) {
        Tag tag = tagRepository.findByName(name);
        Assert.notNull(tag, "该标签不存在");
        return tag;
    }

    @Override
    public Collection<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    private Tag generateTag(String name) {
        Tag tag = new Tag();
        modifyTag(tag, name);
        return tag;
    }

    private void modifyTag(Tag tag, String name) {
        Assert.notNull(tag, "标签不能为空");
        Assert.hasText(name, "标签名不能为空或全空白字符");
        tag.setName(name);
    }

}
