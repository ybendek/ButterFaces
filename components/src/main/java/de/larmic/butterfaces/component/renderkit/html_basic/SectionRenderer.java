package de.larmic.butterfaces.component.renderkit.html_basic;

import de.larmic.butterfaces.component.html.HtmlSection;
import de.larmic.butterfaces.component.partrenderer.StringUtils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import java.io.IOException;

/**
 * Created by larmic on 31.07.14.
 */
@FacesRenderer(componentFamily = HtmlSection.COMPONENT_FAMILY, rendererType = HtmlSection.RENDERER_TYPE)
public class SectionRenderer extends HtmlBasicRenderer {

    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        rendererParamsNotNull(context, component);

        if (!shouldEncode(component)) {
            return;
        }

        final ResponseWriter writer = context.getResponseWriter();
        final HtmlSection fieldSet = (HtmlSection) component;

        final String style = fieldSet.getStyle();
        final String styleClass = fieldSet.getStyleClass();

        writer.startElement(ELEMENT_SECTION, component);

        this.writeIdAttribute(context, writer, component);

        if (null != style) {
            writer.writeAttribute(ATTRIBUTE_STYLE, style, "style");
        }
        if (null != styleClass) {
            writer.writeAttribute(ATTRIBUTE_CLASS, "butter-component-section " + styleClass, "styleClass");
        } else {
            writer.writeAttribute(ATTRIBUTE_CLASS, "butter-component-section", "styleClass");
        }

        this.encodeHeader(component, writer, fieldSet);

        writer.startElement(ELEMENT_DIV, component);
        writer.writeAttribute(ATTRIBUTE_CLASS, "butter-component-section-content", null);
    }

    private void encodeHeader(UIComponent component, ResponseWriter writer, HtmlSection section) throws IOException {
        final UIComponent additionalHeader = this.getFacet(component, "additional-header");
        final boolean labelNotEmpty = StringUtils.isNotEmpty(section.getLabel());

        if (labelNotEmpty || additionalHeader != null) {
            writer.startElement(ELEMENT_DIV, component);
            writer.writeAttribute(ATTRIBUTE_CLASS, "butter-component-section-title", null);

            if (labelNotEmpty) {
                writer.writeText(section.getLabel(), component, "label");
            }

            if (StringUtils.isNotEmpty(section.getBadgeText())) {
                writer.startElement(ELEMENT_SPAN, component);
                writer.writeAttribute(ATTRIBUTE_CLASS, "badge", null);
                writer.writeText(section.getBadgeText(), component, "badgeText");
                writer.endElement(ELEMENT_SPAN);
            }

            if (additionalHeader != null) {
                writer.startElement(ELEMENT_SPAN, component);
                writer.writeAttribute(ATTRIBUTE_CLASS, "butter-component-section-additional-header", null);
                additionalHeader.encodeAll(FacesContext.getCurrentInstance());
                writer.endElement(ELEMENT_SPAN);
            }

            writer.endElement(ELEMENT_DIV);
        }
    }

    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        rendererParamsNotNull(context, component);

        if (!shouldEncode(component)) {
            return;
        }

        final ResponseWriter writer = context.getResponseWriter();

        writer.endElement(ELEMENT_SECTION); // inner content
        writer.endElement(ELEMENT_SECTION); // component
    }

}
