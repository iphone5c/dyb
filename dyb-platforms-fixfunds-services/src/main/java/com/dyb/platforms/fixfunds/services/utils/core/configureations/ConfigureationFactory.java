package com.dyb.platforms.fixfunds.services.utils.core.configureations;

import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
public final class ConfigureationFactory {

    public static List<SettingConfigureation> getSettingConfigureation(String locationPattern)throws ParserConfigurationException, IOException, SAXException {
        if (DybUtils.isEmptyOrNull(locationPattern)) {
            throw new IllegalArgumentException("locationPattern参数不能为null或empty");
        }
        List result = new ArrayList();

        Resource[] resources = getResources(locationPattern);
        if (resources != null) {
            for (Resource resource : resources) {
                SettingConfigureation cig = new SettingConfigureation();
                cig.setSettings(new ArrayList());

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = null;

                InputStream stream = null;
                try {
                    stream = resource.getInputStream();
                    doc = builder.parse(stream);
                } finally {
                    if (stream != null) {
                        stream.close();
                    }
                }
                if (doc.getDocumentElement().hasAttribute("moduleName")) {
                    cig.setModuleName(doc.getDocumentElement().getAttribute("moduleName"));
                }
                if (doc.getDocumentElement().hasChildNodes()) {
                    NodeList nodes = doc.getDocumentElement().getChildNodes();
                    for (int i = 0; i < nodes.getLength(); i++) {
                        if ((nodes.item(i) instanceof Element)) {
                            Element e = (Element)nodes.item(i);
                            if (e.getTagName().equals("setting")) {
                                cig.getSettings().add(readSettingConfigureationItem(e));
                            }
                        }
                    }
                }
                result.add(cig);
            }
        }
        return result;
    }

    private static Resource[] getResources(String locationPattern) throws IOException {
        if (DybUtils.isEmptyOrNull(locationPattern))
            throw new IllegalArgumentException("locationPattern参数不能为null或empty");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] reses = resolver.getResources(locationPattern);
        return reses;
    }

    private static SettingConfigureationItem readSettingConfigureationItem(Element e) {
        String key = "";
        if (e.hasAttribute("key"))
            key = e.getAttribute("key");
        if (DybUtils.isEmptyOrNull(key)) {
            return null;
        }
        SettingConfigureationItem item = new SettingConfigureationItem();
        item.setKey(key);
        item.setChilds(new ArrayList());

        if (e.hasAttribute("attrValue")) {
            item.setAttrValue(e.getAttribute("attrValue"));
        }
        if (e.hasChildNodes()) {
            for (int i = 0; i < e.getChildNodes().getLength(); i++) {
                if ((e.getChildNodes().item(i) instanceof Element)) {
                    Element childElement = (Element)e.getChildNodes().item(i);
                    if (childElement.getTagName().equals("nodeValue")) {
                        item.setNodeValue(childElement.getTextContent());
                    }
                    if (childElement.getTagName().equals("setting")) {
                        item.getChilds().add(readSettingConfigureationItem(childElement));
                    }
                }
            }
        }
        return item;
    }
}
