package de.larmic.butterfaces.component.html;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;
import java.util.Arrays;
import java.util.List;

@ResourceDependencies({
        @ResourceDependency(library = "butterfaces-css", name = "butterfaces-default.css", target = "head"),
		@ResourceDependency(library = "butterfaces-js", name = "butterfaces-default.js", target = "head"),
        @ResourceDependency(library = "butterfaces-configurable", name = "jquery.min.js", target = "head"),
        @ResourceDependency(library = "butterfaces-configurable", name = "bootstrap.min.css", target = "head"),
        @ResourceDependency(library = "butterfaces-configurable", name = "bootstrap.min.js", target = "head"),
        @ResourceDependency(library = "butterfaces-external", name = "01-moment-with-locales.min.js", target = "head"),
        @ResourceDependency(library = "butterfaces-external", name = "bootstrap-datetimepicker.min.css", target = "head"),
        @ResourceDependency(library = "butterfaces-external", name = "bootstrap-datetimepicker.min.js", target = "head"),
        @ResourceDependency(library = "butterfaces-js", name = "butterfaces-tooltip.jquery.js", target = "head")
})
@FacesComponent(HtmlCalendar.COMPONENT_TYPE)
public class HtmlCalendar extends HtmlInputText implements HtmlInputComponent {

	public static final String COMPONENT_TYPE = "de.larmic.butterfaces.component.calendar";
	public static final String COMPONENT_FAMILY = "de.larmic.butterfaces.component.family";
	public static final String RENDERER_TYPE = "de.larmic.butterfaces.component.renderkit.html_basic.CalendarRenderer";

	protected static final String PROPERTY_GLYPHICON_TIME = "glyphiconTime";
	protected static final String PROPERTY_GLYPHICON_DATE = "glyphiconDate";
	protected static final String PROPERTY_GLYPHICON_UP = "glyphiconUp";
	protected static final String PROPERTY_GLYPHICON_DOWN = "glyphiconDown";
	protected static final String PROPERTY_TOOLTIP = "tooltip";
    protected static final String PROPERTY_HIDE_LABEL = "hideLabel";
    protected static final String PROPERTY_INPUT_STYLE_CLASS = "inputStyleClass";
	protected static final String PROPERTY_LABEL_STYLE_CLASS = "labelStyleClass";
	protected static final String PROPERTY_HTML5_PLACEHOLDER = "placeholder";
	protected static final String PROPERTY_HTML5_AUTO_FOCUS = "autoFocus";
	protected static final String PROPERTY_PICK_DATE = "pickDate";
	protected static final String PROPERTY_PICK_TIME = "pickTime";
	protected static final String PROPERTY_LANGUAGE = "language";

	public HtmlCalendar() {
		super();
		this.setRendererType(RENDERER_TYPE);
	}

	@Override
	public List<InputComponentFacet> getSupportedFacets() {
		return Arrays.asList(InputComponentFacet.CALENDAR);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	@Override
	public String getTooltip() {
		return (String) this.getStateHelper().eval(PROPERTY_TOOLTIP);
	}

	public void setTooltip(final String tooltip) {
		this.updateStateHelper(PROPERTY_TOOLTIP, tooltip);
	}

    @Override
    public boolean isHideLabel() {
		final Object eval = this.getStateHelper().eval(PROPERTY_HIDE_LABEL);
		return eval == null ? false : (Boolean) eval;
    }

    public void setHideLabel(final boolean hideLabel) {
        this.updateStateHelper(PROPERTY_HIDE_LABEL, hideLabel);
    }

	public String getPlaceholder() {
		return (String) this.getStateHelper().eval(PROPERTY_HTML5_PLACEHOLDER);
	}

	public void setPlaceholder(final String placeholder) {
		this.updateStateHelper(PROPERTY_HTML5_PLACEHOLDER, placeholder);
	}

    public boolean getAutoFocus() {
		final Object eval = this.getStateHelper().eval(PROPERTY_HTML5_AUTO_FOCUS);
		return eval == null ? false : (Boolean) eval;
    }

    public void setAutoFocus(final boolean autoFocus) {
        this.updateStateHelper(PROPERTY_HTML5_AUTO_FOCUS, autoFocus);
    }

	public boolean isPickDate() {
		return Boolean.valueOf(getStateHelper().eval(PROPERTY_PICK_DATE, Boolean.TRUE).toString());
	}

	public void setPickDate(boolean pickDate) {
		getStateHelper().put(PROPERTY_PICK_DATE, pickDate);;
	}

	public boolean isPickTime() {
		return Boolean.valueOf(getStateHelper().eval(PROPERTY_PICK_TIME, Boolean.TRUE).toString());
	}

	public void setPickTime(boolean pickTime) {
		getStateHelper().put(PROPERTY_PICK_TIME, pickTime);;
	}

	public String getLanguage() {
		return (String) this.getStateHelper().eval(PROPERTY_LANGUAGE);
	}

	public void setLanguage(String language) {
		this.updateStateHelper(PROPERTY_LANGUAGE, language);;
	}

	@Override
    public String getInputStyleClass() {
        return (String) this.getStateHelper().eval(PROPERTY_INPUT_STYLE_CLASS);
    }

    public void setInputStyleClass(final String inputStyleClass) {
        this.updateStateHelper(PROPERTY_INPUT_STYLE_CLASS, inputStyleClass);
    }

    @Override
    public String getLabelStyleClass() {
        return (String) this.getStateHelper().eval(PROPERTY_LABEL_STYLE_CLASS);
    }

    public void setLabelStyleClass(final String labelStyleClass) {
        this.updateStateHelper(PROPERTY_LABEL_STYLE_CLASS, labelStyleClass);
    }

	public String getGlyphiconTime() {
		return (String) this.getStateHelper().eval(PROPERTY_GLYPHICON_TIME);
	}

	public void setGlyphiconTime(final String glyphicon) {
		this.updateStateHelper(PROPERTY_GLYPHICON_TIME, glyphicon);
	}

	public String getGlyphiconDate() {
		return (String) this.getStateHelper().eval(PROPERTY_GLYPHICON_DATE);
	}

	public void setGlyphiconDate(final String glyphicon) {
		this.updateStateHelper(PROPERTY_GLYPHICON_DATE, glyphicon);
	}

	public String getGlyphiconUp() {
		return (String) this.getStateHelper().eval(PROPERTY_GLYPHICON_UP);
	}

	public void setGlyphiconUp(final String glyphicon) {
		this.updateStateHelper(PROPERTY_GLYPHICON_UP, glyphicon);
	}

	public String getGlyphiconDown() {
		return (String) this.getStateHelper().eval(PROPERTY_GLYPHICON_DOWN);
	}

	public void setGlyphiconDown(final String glyphicon) {
		this.updateStateHelper(PROPERTY_GLYPHICON_DOWN, glyphicon);
	}

	private void updateStateHelper(final String propertyName, final Object value) {
		this.getStateHelper().put(propertyName, value);

		final ValueExpression ve = this.getValueExpression(propertyName);

		if (ve != null) {
			ve.setValue(this.getFacesContext().getELContext(), value);
		}
	}
}