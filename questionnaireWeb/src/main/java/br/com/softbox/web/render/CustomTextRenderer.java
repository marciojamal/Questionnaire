package br.com.softbox.web.render;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.context.ResponseWriterWrapper;

import com.sun.faces.renderkit.html_basic.TextRenderer;

public class CustomTextRenderer extends TextRenderer {
	 
    private static final String[] attributes = new String[]{
        "placeholder",
    };
 
    @Override
    protected void getEndTextToRender(FacesContext context, UIComponent component, String currentValue) throws IOException {
        final ResponseWriter originalResponseWriter = context.getResponseWriter();
        context.setResponseWriter(new ResponseWriterWrapper() {
 
            @Override
            public ResponseWriter getWrapped() {
                return originalResponseWriter;
            }
 
            @Override
            public void startElement(String name, UIComponent component) throws IOException {
                super.startElement(name, component);
                if ("input".equalsIgnoreCase(name)) {
                    for (String attr : attributes) {
                        final String value = (String) component.getAttributes().get(attr);
                        if (value != null) {
                            super.writeAttribute(attr, value, attr);
                        }
                    }
                }
            }
        });
 
        super.getEndTextToRender(context, component, currentValue);
        context.setResponseWriter(originalResponseWriter);
    }
}
