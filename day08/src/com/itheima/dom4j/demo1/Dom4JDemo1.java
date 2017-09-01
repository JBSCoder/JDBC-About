package com.itheima.dom4j.demo1;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class Dom4JDemo1 {

	@Test
	/**
	 * 获得元素的内容:查询的操作.
	 */
	public void demo1() throws Exception{
		// 创建解析器
		SAXReader reader = new SAXReader();
		// 解析XML的文档
		Document document = reader.read("xml/demo1.xml");
		// 获得跟节点
		Element root = document.getRootElement();
		System.out.println(root.getName());
		// 查找跟节点下的子节点. element() elements();
		Element pElement = root.element("person"); // 查找的是第一个person元素
		// root.elements("person").get(1); // 查找的是第二个person元素
		Element nElement = pElement.element("name");
		Element aElement = pElement.element("age");
		Element sElement = pElement.element("sex");
		System.out.println(nElement.getText());
		System.out.println(aElement.getText());
		System.out.println(sElement.getText());
	}
	
	@Test
	/**
	 * DOM4J的XPath的写法:
	 */
	public void demo2() throws Exception{
		// 创建解析器:
		SAXReader reader = new SAXReader();
		// 解析XML返回Document对象.
		Document document = reader.read("xml/demo1.xml");
		/*List<Node> list = document.selectNodes("//name");
		for (Node node : list) {
			Element element = (Element) node;
			System.out.println(element.getText());
		}*/
		
		List<Node> list = document.selectNodes("//person['@id']");
		for (Node node : list) {
			Element element = (Element) node;
			System.out.println(element.attributeValue("id"));
		}
	}
}
