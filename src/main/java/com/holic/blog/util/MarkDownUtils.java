package com.holic.blog.util;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.*;

/**
 * @author：HOLiC
 * @date：2020/7/5  22:13
 */
public class MarkDownUtils {

    /* *
     * @Description: 将文本格式的markdown转换为HTML
     * @Author:HOLiC
     * @Date:2020/7/5
     * @Param:[markdown]
     * @return:java.lang.String
     * */
    public static String markdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        Node node = parser.parse(markdown);
        HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();
        return htmlRenderer.render(node);
    }

    /* *
     * @Description: 标题锚点，表格生成
     * @Author:HOLiC
     * @Date:2020/7/5
     * @Param:[markdown]
     * @return:java.lang.String
     * */
    public static String markdownToHtmlAdvice(String markdown) {
        //h标签添加id属性
        Set<Extension> headingAnchorExtension = Collections.singleton(HeadingAnchorExtension.create());
        //table处理
        List<Extension> tablesExtension = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(tablesExtension).build();
        Node node = parser.parse(markdown);
        HtmlRenderer htmlRenderer = HtmlRenderer.builder().extensions(headingAnchorExtension).extensions(tablesExtension)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    @Override
                    public AttributeProvider create(AttributeProviderContext attributeProviderContext) {
                        return new CustomAttributeProvider();
                    }
                }).build();
        return htmlRenderer.render(node);
    }

    /* *
     * @Description: 处理标签的属性
     * @Author:HOLiC
     * @Date:2020/7/5
     * */
    static class CustomAttributeProvider implements AttributeProvider{

        @Override
        public void setAttributes(Node node, String s, Map<String, String> attributes) {
            // Link 表示a标签
            if (node instanceof Link) {
                attributes.put("target", "_blank"); //在新页面打开
            }
            if (node instanceof TableBlock) {
                attributes.put("class", "ui celled table"); //为表格添加Semantic UI的属性
            }
        }
    }
}
