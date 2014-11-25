package de.larmic.butterfaces.component.showcase.table;

import de.larmic.butterfaces.component.showcase.AbstractShowcaseComponent;
import de.larmic.butterfaces.component.showcase.tree.SelectionAjaxType;
import de.larmic.butterfaces.event.TableSingleSelectionListener;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by larmic on 11.09.14.
 */
@Named
@ViewScoped
public class TableShowcaseComponent extends AbstractShowcaseComponent implements Serializable, TableSingleSelectionListener<StringPair> {

    private final List<StringPair> stringPairs = new ArrayList<>();
    private StringPair selectedValue = null;
    private SelectionAjaxType selectionAjaxType = SelectionAjaxType.AJAX;
    private TwoColumnWidthType twoColumnWidthType = TwoColumnWidthType.NONE;

    private boolean tableCondensed;
    private boolean tableBordered;
    private boolean tableStriped = true;
    private String colWidthColumn1;
    private String colWidthColumn2;

    public List<StringPair> getStringRows() {
        if (stringPairs.isEmpty()) {
            stringPairs.add(new StringPair("r1c1", "r1c2"));
            stringPairs.add(new StringPair("r2c1", "r2c2"));
            stringPairs.add(new StringPair("r3c1", "r3c2"));
            stringPairs.add(new StringPair("r4c1", "r4c2"));
            stringPairs.add(new StringPair("r5c1", "r5c2"));
            stringPairs.add(new StringPair("r6c1", "r6c2"));
            stringPairs.add(new StringPair("r7c1", "r7c2"));
        }
        return stringPairs;
    }

    @Override
    public void processValueChange(final StringPair data) {
        this.selectedValue = data;
    }

    public List<SelectItem> getAjaxSelectionTypes() {
        final List<SelectItem> items = new ArrayList<>();

        for (final SelectionAjaxType type : SelectionAjaxType.values()) {
            items.add(new SelectItem(type, type.label));
        }
        return items;
    }

    public List<SelectItem> getTwoColumnWidthTypes() {
        final List<SelectItem> items = new ArrayList<>();

        for (final TwoColumnWidthType type : TwoColumnWidthType.values()) {
            items.add(new SelectItem(type, type.label));
        }
        return items;
    }

    @Override
    protected void addJavaCode(final StringBuilder sb) {
        sb.append("package de.larmic.table.demo;\n\n");

        if (this.selectionAjaxType == SelectionAjaxType.AJAX) {
            sb.append("import de.larmic.butterfaces.event.TableSingleSelectionListener;\n");
        }
        sb.append("import javax.faces.view.ViewScoped;\n");
        sb.append("import javax.inject.Named;\n\n");

        sb.append("@ViewScoped\n");
        sb.append("@Named\n");
        if (this.selectionAjaxType == SelectionAjaxType.AJAX) {
            sb.append("public class MyBean implements Serializable, TableSingleSelectionListener {\n\n");
        } else {
            sb.append("public class MyBean implements Serializable {\n\n");
        }

        sb.append("    public List<StringPair> getValue() {\n");
        sb.append("        final List<StringPair> pairs = new ArrayList<StringPair>();\n");
        sb.append("        pairs.add(new StringPair(\"r1c1\", \"r1c2\"));\n");
        sb.append("        pairs.add(new StringPair(\"r2c1\", \"r2c2\"));\n");
        sb.append("        pairs.add(new StringPair(\"r3c1\", \"r3c2\"));\n");
        sb.append("        pairs.add(new StringPair(\"r4c1\", \"r4c2\"));\n");
        sb.append("        pairs.add(new StringPair(\"r5c1\", \"r5c2\"));\n");
        sb.append("        pairs.add(new StringPair(\"r6c1\", \"r6c2\"));\n");
        sb.append("        pairs.add(new StringPair(\"r7c1\", \"r7c2\"));\n");
        sb.append("        return pairs;\n");
        sb.append("    }\n\n");

        if (selectionAjaxType != SelectionAjaxType.NONE) {
            sb.append("    private StringPair selectedRow;\n\n");
            sb.append("    @Override\n");
            sb.append("    public void processValueChange(final StringPair data) {\n");
            sb.append("        this.selectedRow = data;\n");
            sb.append("    }\n\n");
            sb.append("    public StringPair getSelectedRow() {\n");
            sb.append("        return selectedRow;\n");
            sb.append("    }\n\n");
        }

        sb.append("}\n\n");

        sb.append("public class StringPair {\n\n");
        sb.append("    private final String a;\n");
        sb.append("    private final String b;\n\n");
        sb.append("    public StringPair(final String a, final String b) {\n");
        sb.append("        this.a = a;\n");
        sb.append("        this.b = b;\n");
        sb.append("    }\n\n");
        sb.append("    // getter\n\n");
        sb.append("}\n\n");
    }

    @Override
    public String getXHtml() {
        final StringBuilder sb = new StringBuilder();

        this.addXhtmlStart(sb);

        sb.append("        <b:table id=\"input\"\n");

        this.appendString("var", "rowItem", sb);
        this.appendString("value", "#{myBean.value}", sb);
        this.appendBoolean("tableBordered", this.tableBordered, sb);
        this.appendBoolean("tableCondensed", this.tableCondensed, sb);
        this.appendBoolean("tableStriped", this.tableStriped, sb);
        this.appendBoolean("rendered", this.isRendered(), sb, true);

        if (selectionAjaxType == SelectionAjaxType.AJAX) {
            sb.append("            <f:ajax render=\"selectedRow\"/>\n");
        } else if (selectionAjaxType == SelectionAjaxType.AJAX_DISABLED) {
            sb.append("            <f:ajax render=\"selectedRow\" disabled=\"true\"/>\n");
        }

        sb.append("            <column id=\"column1\"\n");
        if (twoColumnWidthType == TwoColumnWidthType.PERCENT) {
            sb.append("                    colWidth=\"10%\"\n");
        } else if (twoColumnWidthType == TwoColumnWidthType.PX) {
            sb.append("                    colWidth=\"50px\"\n");
        } else if (twoColumnWidthType == TwoColumnWidthType.RELATIVE) {
            sb.append("                    colWidth=\"5*\"\n");
        }
        sb.append("                    label=\"C1\">\n");
        sb.append("            </column>\n");

        sb.append("            <column id=\"column2\"\n");
        if (twoColumnWidthType == TwoColumnWidthType.PERCENT) {
            sb.append("                    colWidth=\"90%\"\n");
        } else if (twoColumnWidthType == TwoColumnWidthType.PX) {
            sb.append("                    colWidth=\"50px\"\n");
        } else if (twoColumnWidthType == TwoColumnWidthType.RELATIVE) {
            sb.append("                    colWidth=\"1*\"\n");
        }
        sb.append("                    label=\"C2\">\n");
        sb.append("            </column>\n");

        sb.append("        </b:table>");

        if (selectionAjaxType != SelectionAjaxType.NONE) {
            sb.append("\n\n        <h:panelGroup id=\"selectedRow\">\n");
            sb.append("            <h:output value=\"#{myBean.selectedRow.a}\"\n");
            sb.append("                      rendered=\"#{not empty myBean.selectedRow}\"/>\n");
            sb.append("        <h:panelGroup/>");
        }

        this.addXhtmlEnd(sb);

        return sb.toString();
    }

    @Override
    protected String getEmptyDistanceString() {
        return "                 ";
    }

    public SelectionAjaxType getSelectionAjaxType() {
        return selectionAjaxType;
    }

    public void setSelectionAjaxType(final SelectionAjaxType selectionAjaxType) {
        this.selectionAjaxType = selectionAjaxType;
    }

    public StringPair getSelectedValue() {
        return this.selectedValue;
    }

    public boolean isTableCondensed() {
        return tableCondensed;
    }

    public void setTableCondensed(boolean tableCondensed) {
        this.tableCondensed = tableCondensed;
    }

    public boolean isTableBordered() {
        return tableBordered;
    }

    public void setTableBordered(boolean tableBordered) {
        this.tableBordered = tableBordered;
    }

    public boolean isTableStriped() {
        return tableStriped;
    }

    public void setTableStriped(boolean tableStriped) {
        this.tableStriped = tableStriped;
    }

    public TwoColumnWidthType getTwoColumnWidthType() {
        return twoColumnWidthType;
    }

    public void setTwoColumnWidthType(TwoColumnWidthType twoColumnWidthType) {
        this.twoColumnWidthType = twoColumnWidthType;

        switch (this.twoColumnWidthType) {
            case NONE:
                colWidthColumn1 = null;
                colWidthColumn2 = null;
                break;
            case PERCENT:
                colWidthColumn1 = "10%";
                colWidthColumn2 = "90%";
                break;
            case PX:
                colWidthColumn1 = "50px";
                colWidthColumn2 = "50px";
                break;
            case RELATIVE:
                colWidthColumn1 = "5*";
                colWidthColumn2 = "1*";
                break;
        }
    }

    public String getColWidthColumn1() {
        return colWidthColumn1;
    }

    public String getColWidthColumn2() {
        return colWidthColumn2;
    }
}
