package com.holic.blog.web.admin;

import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.Tag;
import com.holic.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author：HOLiC_y
 * @date：2020/6/14
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(Model model, Integer pageNum) {

        pageNum = pageNum == null ? 1 : pageNum;

        PageInfo<Tag> newPage = tagService.listTag(pageNum, 10); //分页大小写死
        model.addAttribute("page", newPage);
        return "admin/tags";
    }

    @GetMapping("/tags/add")
    public String add(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    @PostMapping("/tags/addTag")
    public String addTag(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {

        List<Tag> tagList = tagService.getTagByName(tag.getName());
        if (tagList.size() > 0) {
            result.rejectValue("name", "nameRepeat", "不能添加已存在的标签！");
        }

        if (result.hasErrors()) {
            return "admin/tags-input";
        }

        int i = tagService.saveType(tag);
        if (i == 0) {
            attributes.addFlashAttribute("failMessage","添加标签失败！");
        } else {
            attributes.addFlashAttribute("succMessage","添加标签成功！");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }

    @PostMapping("/tags/edit/{id}")
    public String editTag(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {

        List<Tag> tagList = tagService.getTagByName(tag.getName());
        if (tagList.size() > 0) {
            result.rejectValue("name", "nameRepeat", "修改后的标签名称已存在，请重试！");
        }

        if (result.hasErrors()) {
            return "admin/tags-input";
        }

        int i = tagService.updateTag(tag);
        if (i == 0) {
            attributes.addFlashAttribute("failMessage","修改标签失败！");
        } else {
            attributes.addFlashAttribute("succMessage","修改标签成功！");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String deleteTag(@PathVariable Long id, RedirectAttributes attributes) {
        int i = tagService.deleteTag(id);
        if (i == 0) {
            attributes.addFlashAttribute("failMessage","删除标签失败！");
        } else {
            attributes.addFlashAttribute("succMessage","删除标签成功！");
        }
        return "redirect:/admin/tags";
    }
}
