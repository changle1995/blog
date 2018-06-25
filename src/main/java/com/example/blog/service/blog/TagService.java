package com.example.blog.service.blog;

import com.example.blog.entity.blog.Tag;
import com.example.blog.service.BaseService;

import java.util.Collection;
import java.util.Set;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 17:25
 */
public interface TagService extends BaseService<Tag> {

    /**
     * 新增标签方法
     *
     * @param name 标签名称
     * @return 返回新增的标签
     */
    Tag addTag(String name);

    /**
     * 根据多个标签名称生成多个标签方法
     * 若标签名称已存在则直接获取已存在的标签
     *
     * @param tagNameSet 标签名称集合
     * @return 返回新增的标签集合
     */
    Set<Tag> addTagSet(Set<String> tagNameSet);

    /**
     * 删除标签方法
     *
     * @param id 标签ID
     */
    void deleteTag(long id);

    /**
     * 修改标签方法
     *
     * @param id   标签ID
     * @param name 标签名称
     * @return 返回修改后的标签
     */
    Tag editTag(long id, String name);

    /**
     * 根据标签名称查找标签方法
     *
     * @param name 标签名称
     * @return 返回获取的标签
     */
    Tag getTag(String name);

    /**
     * 查找所有标签
     *
     * @return 返回所有标签
     */
    Collection<Tag> getAllTags();

}
