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
import java.util.Optional;
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
        Assert.isNull(tagRepository.findByName(name), "该标签已存在");
        return tagRepository.save(modifyTag(new Tag(), name));
    }

    @Override
    public Set<Tag> addTagSet(Set<String> tagNameSet) {
        return Optional.ofNullable(tagNameSet)
                .map(set -> set
                        .stream()
                        .map(tagName -> Optional.ofNullable(tagRepository.findByName(tagName)).orElseGet(() -> tagRepository.save(modifyTag(new Tag(), tagName))))
                        .collect(Collectors.toSet()))
                .orElse(null);
    }

    @Override
    public void deleteTag(long id) {
        tagRepository.delete(id);
    }

    @Override
    public Tag editTag(long id, String name) {
        Tag tag = tagRepository.findByName(name);
        Assert.state(tag == null || tag.getId() == id, "该标签已存在");
        return tagRepository.save(modifyTag(tagRepository.findOne(id), name));
    }

    @Override
    public Collection<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    private Tag modifyTag(Tag tag, String name) {
        Assert.notNull(tag, "该标签不存在");
        Assert.hasText(name, "标签名不能为空或全空白字符");
        tag.setName(name);
        return tag;
    }

}
